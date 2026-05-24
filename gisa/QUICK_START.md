# 🚀 Quick Start Guide - GISA API

## ⏱️ 5 Minutos para Começar

### 1. Pré-requisitos (2 min)

```bash
# Verificar Java 21
java -version
# Esperado: openjdk version "21" ou superior

# Verificar Maven
mvn -version
# Esperado: Maven 3.8.1 ou superior
```

### 2. Banco de Dados (1 min)

Criar usuário Oracle:
```sql
CREATE USER GISA_USER IDENTIFIED BY GISA_PASSWORD;
GRANT CONNECT, RESOURCE TO GISA_USER;
GRANT UNLIMITED TABLESPACE TO GISA_USER;
```

### 3. Clonar/Verificar Projeto

```bash
cd c:\Users\vania\BackEnd_GISA\gisa
```

### 4. Compilar (1 min)

```bash
mvn clean install
```

### 5. Executar (1 min)

```bash
mvn spring-boot:run
```

**Resultado esperado:**
```
[INFO] Started GisaApplication in 5.123 seconds
[INFO] Tomcat started on port 8080 (http)
```

---

## 📡 Primeiro Teste

### Abrir Terminal/Postman e Testar

#### Listar Pacientes (vazio inicialmente)
```bash
curl -X GET http://localhost:8080/gisa-api/api/pacientes
```

**Resposta esperada:**
```json
[]
```

#### Criar um Paciente
```bash
curl -X POST http://localhost:8080/gisa-api/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "cpf": "12345678901",
    "dataNascimento": "2000-01-15",
    "sexo": "M",
    "celular": "11987654321",
    "estadoCivil": "SOLTEIRO",
    "statusCadastro": "ATIVO",
    "statusPaciente": "DESLIGADO",
    "tipoEntrada": "PRIMEIRA_VEZ",
    "dataCadastro": "2024-05-24",
    "convenio": false
  }'
```

**Resposta esperada:**
```json
{
  "idCadastro": 1,
  "nome": "João Silva",
  "cpf": "12345678901",
  ...
}
```

#### Listar Novamente
```bash
curl -X GET http://localhost:8080/gisa-api/api/pacientes
```

**Resposta esperada:**
```json
[
  {
    "idCadastro": 1,
    "nome": "João Silva",
    ...
  }
]
```

---

## 📚 Recursos Rápidos

| Recurso | Descrição | Endpoint Base |
|---------|-----------|---------------|
| Pacientes | Gerenciar pacientes | `/api/pacientes` |
| Profissionais | Gerenciar profissionais | `/api/profissionais` |
| Especialistas | Gerenciar especialistas | `/api/especialistas` |
| Terapias | Gerenciar terapias | `/api/terapias` |
| Usuários | Gerenciar usuários | `/api/usuarios` |

---

## 🔗 Padrão de Endpoints

Todos os recursos seguem este padrão:

```
GET    /api/{recurso}              → Listar todos
POST   /api/{recurso}              → Criar novo
GET    /api/{recurso}/{id}         → Buscar por ID
PUT    /api/{recurso}/{id}         → Atualizar
DELETE /api/{recurso}/{id}         → Deletar
```

---

## 🎯 Próximos Passos

1. Ler [API_DOCUMENTATION.md](API_DOCUMENTATION.md) para referência completa
2. Usar [EXEMPLOS_REQUISICOES.http](EXEMPLOS_REQUISICOES.http) para mais exemplos
3. Consultar [README_SETUP.md](README_SETUP.md) para detalhes de configuração
4. Verificar [TROUBLESHOOTING.md](TROUBLESHOOTING.md) se tiver problemas

---

## 💡 Dicas Importantes

✅ **Use Postman/Insomnia** para testar endpoints facilmente
✅ **Verifique o JSON** - certifique-se de estar bem formatado
✅ **Respeite os tipos** - campos booleanos como `true/false`, não strings
✅ **IDs são auto-gerados** - não envie IDs no POST, apenas no PUT/DELETE

---

## ❌ Erros Comuns

| Erro | Causa | Solução |
|------|-------|--------|
| 404 Not Found | URL incorreta | Verifique URL e use plural |
| 400 Bad Request | JSON inválido | Valide o JSON formato |
| Connection refused | Oracle offline | Inicie Oracle Database |
| 500 Internal Error | Erro no servidor | Verifique logs |

---

## 🐛 Debug Rápido

```bash
# Ver logs em tempo real
mvn spring-boot:run

# Em outro terminal, testar
curl http://localhost:8080/gisa-api/api/pacientes

# Observar resposta e logs do servidor
```

---

## 📖 Documentação Completa

- 📄 [API_DOCUMENTATION.md](API_DOCUMENTATION.md) - Referência de endpoints
- 📄 [README_SETUP.md](README_SETUP.md) - Guia de instalação detalhado
- 📄 [EXEMPLOS_REQUISICOES.http](EXEMPLOS_REQUISICOES.http) - Exemplos de testes
- 📄 [TROUBLESHOOTING.md](TROUBLESHOOTING.md) - Resolução de problemas
- 📄 [IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md) - Sumário técnico
- 📄 [VALIDATION_CHECKLIST.md](VALIDATION_CHECKLIST.md) - Checklist de validação

---

## 🎉 Você está Pronto!

Sua API RESTful está rodando em:

```
http://localhost:8080/gisa-api
```

Comece a explorar os endpoints e aproveite a API! 🚀

---

**Dúvidas?** Consulte a documentação completa nos arquivos `.md` do projeto.
