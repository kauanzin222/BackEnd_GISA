# GISA - Sistema de Gestão Integrada de Saúde e Aprendizado

API RESTful desenvolvida em **Java 21** com **Spring Boot 4.0.6** para integração com banco de dados **Oracle SQL**.

## 📋 Descrição do Projeto

Sistema completo para gerenciamento de:
- **Pessoas**: Cadastro de pessoas com dados básicos
- **Pacientes**: Pacientes registrados no sistema com histórico de terapias
- **Profissionais**: Profissionais e especialistas
- **Especialistas**: Profissionais com especialidades
- **Terapias**: Registro de sessões terapêuticas
- **Prontuários**: Informações médicas detalhadas
- **Usuários**: Sistema de login com perfis e permissões

## 🏗️ Arquitetura

```
GISA/
├── src/main/java/com/fatec/gisa/
│   ├── controllers/        # REST Endpoints
│   ├── services/           # Lógica de negócio
│   ├── repositories/       # Acesso aos dados (JPA)
│   ├── models/             # Entidades JPA
│   ├── enums/              # Enumerações
│   └── GisaApplication.java
├── src/main/resources/
│   └── application.properties
├── pom.xml                 # Dependências Maven
└── API_DOCUMENTATION.md    # Documentação dos endpoints
```

## 🚀 Configuração e Instalação

### Pré-requisitos

- Java 21+
- Maven 3.8.1+
- Oracle Database 11g ou superior
- Git

### Passos de Instalação

1. **Clone o repositório**
   ```bash
   cd gisa
   ```

2. **Configure o banco de dados Oracle**
   - Crie um usuário: `GISA_USER`
   - Senha: `GISA_PASSWORD`
   - Configure a conexão em `application.properties`

3. **Atualize as propriedades de conexão**
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@SEU_HOST:1521:SEU_SID
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

4. **Instale as dependências**
   ```bash
   mvn clean install
   ```

5. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

A API estará disponível em: `http://localhost:8080/gisa-api`

## 📚 Estrutura de Camadas

### 1. **Controller Layer** (controllers/)
Responsável por:
- Expor endpoints REST
- Mapear requisições HTTP
- Retornar respostas formatadas
- Validações básicas de entrada

**Exemplo:**
```java
@PostMapping
public ResponseEntity<Paciente> criar(@RequestBody Paciente paciente)
```

### 2. **Service Layer** (services/)
Responsável por:
- Lógica de negócio
- Validações complexas
- Orquestração de operações
- Tratamento de exceções

**Exemplo:**
```java
public Paciente criar(Paciente paciente) {
    // Validações
    // Lógica de negócio
    return pacienteRepository.save(paciente);
}
```

### 3. **Repository Layer** (repositories/)
Responsável por:
- Acesso aos dados
- Consultas ao banco
- Operações CRUD
- Abstração do banco de dados

**Exemplo:**
```java
public interface PacienteRepository extends JpaRepository<Paciente, Integer>
```

### 4. **Model Layer** (models/)
Define as entidades JPA:
- Mapeamento O/R (Object-Relational)
- Relacionamentos entre tabelas
- Validações de integridade

## 🔗 Relacionamentos Entre Entidades

```
Pessoa (superclasse)
  ├── Paciente
  ├── Profissional
  │   ├── Especialista
  │   │   └── EspecialistaPJ
  └── Responsavel

Usuario (1-1) ← Pessoa
     ↓ (M-1)
   Perfil (M-M) → Permissao

Paciente (M-M) → CID
Paciente (1-1) → Prontuario
Paciente (M-1) → Escola

Profissional (M-1) → Cargo
Cargo (M-1) → CBO

Especialista (M-M) → Especialidade

Terapia (M-1) → Modalidade
Terapia (M-M) → Paciente
Terapia (M-M) → Especialista
```

## 💾 Banco de Dados

### Tabelas Principais

| Tabela | Descrição |
|--------|-----------|
| PESSOA | Dados base de pessoas |
| PACIENTE | Pacientes do sistema |
| PROFISSIONAL | Profissionais |
| ESPECIALISTA | Especialistas |
| USUARIO | Usuários do sistema |
| TERAPIA | Sessões terapêuticas |
| PRONTUARIO | Registros médicos |
| ESPECIALIDADE | Tipos de especialidade |
| CID | Classificação de doenças |
| CARGO | Cargos profissionais |
| CBO | Classificação ocupacional |
| PERFIL | Perfis de acesso |
| PERMISSAO | Permissões do sistema |

### Sequências Oracle

O sistema utiliza sequências para auto-incremento:
```sql
SEQ_PESSOA, SEQ_ENDERECO, SEQ_PACIENTE, SEQ_PROFISSIONAL, 
SEQ_ESPECIALISTA, SEQ_TERAPIA, SEQ_PRONTUARIO, etc.
```

## 🔌 Endpoints REST

A API oferece **18 recursos** com operações CRUD completas:

```
GET    /api/recurso        - Listar todos
POST   /api/recurso        - Criar novo
GET    /api/recurso/{id}   - Buscar por ID
PUT    /api/recurso/{id}   - Atualizar
DELETE /api/recurso/{id}   - Deletar
```

Consulte [API_DOCUMENTATION.md](API_DOCUMENTATION.md) para a lista completa de endpoints.

## 🧪 Testando a API

### Usando cURL

```bash
# Listar pacientes
curl -X GET http://localhost:8080/gisa-api/api/pacientes

# Criar paciente
curl -X POST http://localhost:8080/gisa-api/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{"nome":"João Silva","cpf":"12345678901"}'

# Buscar por ID
curl -X GET http://localhost:8080/gisa-api/api/pacientes/1

# Atualizar
curl -X PUT http://localhost:8080/gisa-api/api/pacientes/1 \
  -H "Content-Type: application/json" \
  -d '{"nome":"João Silva Santos"}'

# Deletar
curl -X DELETE http://localhost:8080/gisa-api/api/pacientes/1
```

### Usando Postman

1. Importe a coleção de requests
2. Configure a variável `base_url` para `http://localhost:8080/gisa-api`
3. Teste cada endpoint

## 📦 Dependências Principais

```xml
- spring-boot-starter-web        (REST Framework)
- spring-boot-starter-data-jpa    (ORM)
- spring-boot-starter-validation  (Bean Validation)
- ojdbc11                          (Oracle Driver)
- spring-boot-starter-test        (Testes)
```

## ⚙️ Configuração Avançada

### application.properties

```properties
# Servidor
server.port=8080
server.servlet.context-path=/gisa-api

# Banco de Dados
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=GISA_USER
spring.datasource.password=GISA_PASSWORD

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.show-sql=false
```

## 🐛 Troubleshooting

### Erro: "Connection refused"
- Verifique se o Oracle está rodando
- Confirme as credenciais de banco de dados
- Teste a conexão manualmente

### Erro: "Table not found"
- Execute `mvn clean install` para criar as tabelas
- Verifique se as sequências foram criadas no Oracle

### Erro: "Invalid CSRF token"
- Desabilitar CSRF se necessário em futuras versões com Spring Security

## 📖 Documentação Adicional

- [API_DOCUMENTATION.md](API_DOCUMENTATION.md) - Referência completa de endpoints
- Spring Boot Docs: https://spring.io/projects/spring-boot
- Oracle JDBC: https://www.oracle.com/database/technologies/appdev/jdbc/

## 👨‍💻 Contribuindo

1. Crie uma branch para sua feature
2. Faça as mudanças necessárias
3. Abra um Pull Request
4. Descreva suas mudanças

## 📄 Licença

Este projeto é fornecido sem licença específica.

## 📞 Suporte

Para dúvidas ou problemas, entre em contato com a equipe de desenvolvimento.

---

**Desenvolvido com Java 21 e Spring Boot 4.0.6**
