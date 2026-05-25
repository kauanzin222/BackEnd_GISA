# Guia de Solução de Problemas - GISA API

## 🔧 Problemas Comuns e Soluções

### 1. Erro na Inicialização da Aplicação

#### Problema: "Cannot Connect to Database"
```
Error: java.sql.SQLException: IO Error: The Network Adapter could not establish the connection
```

**Solução:**
1. Verifique se o Oracle Database está em execução
   ```bash
   # Windows
   services.msc → procure por "OracleServiceXE"
   
   # Linux
   systemctl status oracle-database
   ```

2. Confirme as credenciais em `application.properties`
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. Teste a conexão manualmente com SQL Developer ou SQLPlus
   ```sql
   sqlplus seu_usuario/sua_senha@XE
   ```

---

#### Problema: "No compatible version"
```
Error: The version of the driver does not match the version of the database
```

**Solução:**
1. Atualize o driver OJDBC para a versão compatível
2. Verifique a versão do Oracle instalado
3. Atualize no pom.xml:
   ```xml
   <!-- Para Oracle 11g/12c -->
   <artifactId>ojdbc11</artifactId>
   
   <!-- Para Oracle 21c -->
   <artifactId>ojdbc11</artifactId>
   ```

---

### 2. Erros de Compilação

#### Problema: "Cannot resolve symbol 'jakarta.persistence'"
```
Error: package jakarta.persistence does not exist
```

**Solução:**
- Spring Boot 4.0+ usa Jakarta EE. Certifique-se de importar:
  ```java
  import jakarta.persistence.*;  // ✓ Correto (Spring Boot 4.0+)
  // import javax.persistence.*; // ✗ Errado (Spring Boot 3.0 e anterior)
  ```

---

#### Problema: "Build Failure: org.springframework.web not found"
```
Error: Cannot find symbol: class RestController
```

**Solução:**
1. Adicione `spring-boot-starter-web` ao pom.xml:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   ```

2. Execute `mvn clean install`

3. Recarregue a IDE (Alt+F5 no Eclipse)

---

### 3. Erros em Runtime

#### Problema: "Table does not exist"
```
Error: ORA-00942: table or view does not exist
```

**Solução:**
1. Verifique se as tabelas foram criadas:
   ```sql
   SELECT table_name FROM user_tables;
   ```

2. Force a criação automática em `application.properties`:
   ```properties
   spring.jpa.hibernate.ddl-auto=create  -- recria todas as tabelas
   spring.jpa.hibernate.ddl-auto=update  -- atualiza schema existente
   ```

3. Se necessário, limpe o banco:
   ```sql
   DROP TABLE paciente;
   DROP TABLE pessoa CASCADE CONSTRAINTS;
   DROP SEQUENCE seq_pessoa;
   ```

4. Reinicie a aplicação para recriar as tabelas

---

#### Problema: "No sequence found"
```
Error: ORA-02289: sequence does not exist
```

**Solução:**
1. Verifique as sequências criadas:
   ```sql
   SELECT sequence_name FROM user_sequences;
   ```

2. Crie manualmente as sequências necessárias:
   ```sql
   CREATE SEQUENCE seq_pessoa START WITH 1 INCREMENT BY 1;
   CREATE SEQUENCE seq_paciente START WITH 1 INCREMENT BY 1;
   -- etc...
   ```

3. O Hibernate deve criar automaticamente com `ddl-auto=update`

---

#### Problema: "Constraint Violation"
```
Error: ORA-01400: cannot insert NULL into ("GISA_USER"."PESSOA"."NOME")
```

**Solução:**
1. Verifique o JSON enviado - certifique-se de incluir campos obrigatórios:
   ```json
   {
     "nome": "João Silva",        // Obrigatório
     "cpf": "12345678901",        // Obrigatório
     "statusCadastro": "ATIVO"    // Pode ser necessário
   }
   ```

2. Valide os dados antes de enviar

---

### 4. Erros de API REST

#### Problema: "404 Not Found"
```
POST http://localhost:8080/gisa-api/api/paciente
Result: 404 Not Found
```

**Solução:**
1. Verifique a URL (pluralize o recurso):
   ```
   ✗ /api/paciente     (singular)
   ✓ /api/pacientes    (plural)
   ```

2. Confirme a porta: deve ser `8080`

3. Confirme o context path: `/gisa-api`

4. Verifique se a aplicação está rodando:
   ```bash
   curl http://localhost:8080/gisa-api/api/pacientes
   ```

---

#### Problema: "400 Bad Request"
```
Error: JSON parse error: Unrecognized field "idCadastro"
```

**Solução:**
1. Verifique a capitalização dos campos JSON
2. Use camelCase para o JSON:
   ```json
   {
     "idCadastro": 1,      // ✓ Correto
     "id_cadastro": 1,     // ✗ Errado
     "statusPaciente": "ATIVO"
   }
   ```

3. Envie `Content-Type: application/json` no header

---

#### Problema: "405 Method Not Allowed"
```
POST /api/pacientes/1
Result: 405 Method Not Allowed
```

**Solução:**
- Use PUT para atualizar recursos:
  ```
  ✗ POST /api/pacientes/1    (400)
  ✓ PUT /api/pacientes/1     (correto)
  ```

---

### 5. Problemas de Performance

#### Problema: "Application starts very slowly"

**Solução:**
1. Verifique logs para operações lentas:
   ```properties
   logging.level.org.hibernate.SQL=DEBUG
   logging.level.org.springframework.web=WARN
   ```

2. Reduza batch size se necessário:
   ```properties
   spring.jpa.properties.hibernate.jdbc.batch_size=10
   ```

3. Considere desabilitar reflexão desnecessária:
   ```properties
   spring.jpa.show-sql=false
   ```

---

#### Problema: "Out of Memory - Heap Space"
```
Error: java.lang.OutOfMemoryError: Java heap space
```

**Solução:**
1. Aumente a memória JVM:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xmx1024m"
   ```

2. Ou configure em IDE (Eclipse/IntelliJ)

---

### 6. Problemas de Relacionamentos

#### Problema: "Cascading not working"
```
Error: Cannot delete parent record - child records exist
```

**Solução:**
1. Verifique a configuração de cascata:
   ```java
   @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
   private List<Endereco> enderecos;  // ✓ Correto
   
   @OneToMany(mappedBy = "pessoa")    // ✗ Sem cascade
   private List<Endereco> enderecos;
   ```

2. Use `DELETE CASCADE` no banco se necessário

---

#### Problema: "LazyInitializationException"
```
Error: org.hibernate.LazyInitializationException: 
could not initialize proxy - no Session
```

**Solução:**
1. Use `FetchType.EAGER` para relacionamentos usados frequentemente:
   ```java
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "ID_CARGO")
   private Cargo cargo;
   ```

2. Ou carregue os dados explicitamente em uma transação

---

### 7. Problemas com Banco de Dados

#### Problema: "Tablespace is full"
```
Error: ORA-01691: tablespace is full
```

**Solução:**
1. Verifique espaço em disco
2. Limpe dados antigos
3. Estenda o tablespace:
   ```sql
   ALTER TABLESPACE tablespace_name ADD DATAFILE '/path/file.dbf' SIZE 100M;
   ```

---

#### Problema: "Too many connections"
```
Error: ORA-12516: TNS:listener could not find matching endpoint
```

**Solução:**
1. Reduza pool de conexões:
   ```properties
   spring.datasource.hikari.maximum-pool-size=5
   spring.datasource.hikari.minimum-idle=2
   ```

2. Aumente limite no Oracle:
   ```sql
   ALTER SYSTEM SET PROCESSES=500 SCOPE=BOTH;
   ```

---

## 📊 Checklist de Troubleshooting

- [ ] Oracle Database está rodando?
- [ ] Credenciais de banco estão corretas?
- [ ] Aplicação compila sem erros?
- [ ] Aplicação inicia sem erros?
- [ ] Endpoints respondendo?
- [ ] Dados sendo persistidos?
- [ ] Relacionamentos funcionando?
- [ ] Performance aceitável?

---

## 🆘 Obter Logs Detalhados

### Ativar Debug Completo
```properties
# application.properties
logging.level.root=DEBUG
logging.level.com.fatec.gisa=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.data=DEBUG
logging.level.org.hibernate=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql=TRACE
```

### Ver Logs em Arquivo
```properties
logging.file.name=logs/gisa.log
logging.file.max-size=10MB
logging.file.max-history=10
```

---

## 🔗 Recursos Úteis

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Jakarta EE Documentation](https://jakarta.ee/)
- [Hibernate Documentation](https://hibernate.org/)
- [Oracle JDBC Documentation](https://docs.oracle.com/en/database/oracle/oracle-database/21/jajdb/)
- [Maven Documentation](https://maven.apache.org/guides/)

---

## 💬 Contato de Suporte

Se o problema persistir:
1. Verifique todos os logs disponíveis
2. Consulte a documentação oficial
3. Conte com equipe de desenvolvimento

---

**Última atualização:** Maio 2024
