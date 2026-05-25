# API RESTful GISA - Documentação de Endpoints

## Base URL
```
http://localhost:8080/gisa-api
```

## Endpoints Disponíveis

### 1. PESSOAS
- **POST** `/api/pessoas` - Criar nova pessoa
- **GET** `/api/pessoas` - Listar todas as pessoas
- **GET** `/api/pessoas/{id}` - Buscar pessoa por ID
- **GET** `/api/pessoas/cpf/{cpf}` - Buscar pessoa por CPF
- **PUT** `/api/pessoas/{id}` - Atualizar pessoa
- **DELETE** `/api/pessoas/{id}` - Deletar pessoa

### 2. ENDEREÇOS
- **POST** `/api/enderecos` - Criar novo endereço
- **GET** `/api/enderecos` - Listar todos os endereços
- **GET** `/api/enderecos/{id}` - Buscar endereço por ID
- **PUT** `/api/enderecos/{id}` - Atualizar endereço
- **DELETE** `/api/enderecos/{id}` - Deletar endereço

### 3. PACIENTES
- **POST** `/api/pacientes` - Criar novo paciente
- **GET** `/api/pacientes` - Listar todos os pacientes
- **GET** `/api/pacientes/{id}` - Buscar paciente por ID
- **GET** `/api/pacientes/cpf/{cpf}` - Buscar paciente por CPF
- **PUT** `/api/pacientes/{id}` - Atualizar paciente
- **DELETE** `/api/pacientes/{id}` - Deletar paciente

### 4. PROFISSIONAIS
- **POST** `/api/profissionais` - Criar novo profissional
- **GET** `/api/profissionais` - Listar todos os profissionais
- **GET** `/api/profissionais/{id}` - Buscar profissional por ID
- **GET** `/api/profissionais/cpf/{cpf}` - Buscar profissional por CPF
- **PUT** `/api/profissionais/{id}` - Atualizar profissional
- **DELETE** `/api/profissionais/{id}` - Deletar profissional

### 5. ESPECIALISTAS
- **POST** `/api/especialistas` - Criar novo especialista
- **GET** `/api/especialistas` - Listar todos os especialistas
- **GET** `/api/especialistas/{id}` - Buscar especialista por ID
- **GET** `/api/especialistas/cpf/{cpf}` - Buscar especialista por CPF
- **PUT** `/api/especialistas/{id}` - Atualizar especialista
- **DELETE** `/api/especialistas/{id}` - Deletar especialista

### 6. ESPECIALISTAS PJ
- **POST** `/api/especialistas-pj` - Criar novo especialista PJ
- **GET** `/api/especialistas-pj` - Listar todos os especialistas PJ
- **GET** `/api/especialistas-pj/{id}` - Buscar especialista PJ por ID
- **GET** `/api/especialistas-pj/cnpj/{cnpj}` - Buscar especialista PJ por CNPJ
- **PUT** `/api/especialistas-pj/{id}` - Atualizar especialista PJ
- **DELETE** `/api/especialistas-pj/{id}` - Deletar especialista PJ

### 7. RESPONSÁVEIS
- **POST** `/api/responsaveis` - Criar novo responsável
- **GET** `/api/responsaveis` - Listar todos os responsáveis
- **GET** `/api/responsaveis/{id}` - Buscar responsável por ID
- **GET** `/api/responsaveis/cpf/{cpf}` - Buscar responsável por CPF
- **PUT** `/api/responsaveis/{id}` - Atualizar responsável
- **DELETE** `/api/responsaveis/{id}` - Deletar responsável

### 8. TERAPIAS
- **POST** `/api/terapias` - Criar nova terapia
- **GET** `/api/terapias` - Listar todas as terapias
- **GET** `/api/terapias/{id}` - Buscar terapia por ID
- **PUT** `/api/terapias/{id}` - Atualizar terapia
- **DELETE** `/api/terapias/{id}` - Deletar terapia

### 9. PRONTUÁRIOS
- **POST** `/api/prontuarios` - Criar novo prontuário
- **GET** `/api/prontuarios` - Listar todos os prontuários
- **GET** `/api/prontuarios/{id}` - Buscar prontuário por ID
- **PUT** `/api/prontuarios/{id}` - Atualizar prontuário
- **DELETE** `/api/prontuarios/{id}` - Deletar prontuário

### 10. ESPECIALIDADES
- **POST** `/api/especialidades` - Criar nova especialidade
- **GET** `/api/especialidades` - Listar todas as especialidades
- **GET** `/api/especialidades/{id}` - Buscar especialidade por ID
- **PUT** `/api/especialidades/{id}` - Atualizar especialidade
- **DELETE** `/api/especialidades/{id}` - Deletar especialidade

### 11. CIDs (Classificação de Doenças)
- **POST** `/api/cids` - Criar novo CID
- **GET** `/api/cids` - Listar todos os CIDs
- **GET** `/api/cids/{id}` - Buscar CID por ID
- **PUT** `/api/cids/{id}` - Atualizar CID
- **DELETE** `/api/cids/{id}` - Deletar CID

### 12. CARGOS
- **POST** `/api/cargos` - Criar novo cargo
- **GET** `/api/cargos` - Listar todos os cargos
- **GET** `/api/cargos/{id}` - Buscar cargo por ID
- **PUT** `/api/cargos/{id}` - Atualizar cargo
- **DELETE** `/api/cargos/{id}` - Deletar cargo

### 13. PERFIS
- **POST** `/api/perfis` - Criar novo perfil
- **GET** `/api/perfis` - Listar todos os perfis
- **GET** `/api/perfis/{id}` - Buscar perfil por ID
- **GET** `/api/perfis/nome/{nome}` - Buscar perfil por nome
- **PUT** `/api/perfis/{id}` - Atualizar perfil
- **DELETE** `/api/perfis/{id}` - Deletar perfil

### 14. USUÁRIOS
- **POST** `/api/usuarios` - Criar novo usuário
- **GET** `/api/usuarios` - Listar todos os usuários
- **GET** `/api/usuarios/{id}` - Buscar usuário por ID
- **GET** `/api/usuarios/cpf/{cpf}` - Buscar usuário por CPF da pessoa
- **PUT** `/api/usuarios/{id}` - Atualizar usuário
- **DELETE** `/api/usuarios/{id}` - Deletar usuário

### 15. ESCOLAS
- **POST** `/api/escolas` - Criar nova escola
- **GET** `/api/escolas` - Listar todas as escolas
- **GET** `/api/escolas/{id}` - Buscar escola por ID
- **PUT** `/api/escolas/{id}` - Atualizar escola
- **DELETE** `/api/escolas/{id}` - Deletar escola

### 17. CBOs (Classificação Brasileira de Ocupações)
- **POST** `/api/cbos` - Criar novo CBO
- **GET** `/api/cbos` - Listar todos os CBOs
- **GET** `/api/cbos/{id}` - Buscar CBO por ID
- **PUT** `/api/cbos/{id}` - Atualizar CBO
- **DELETE** `/api/cbos/{id}` - Deletar CBO

### 18. PERMISSÕES
- **POST** `/api/permissoes` - Criar nova permissão
- **GET** `/api/permissoes` - Listar todas as permissões
- **GET** `/api/permissoes/{id}` - Buscar permissão por ID
- **GET** `/api/permissoes/nome/{nome}` - Buscar permissão por nome
- **PUT** `/api/permissoes/{id}` - Atualizar permissão
- **DELETE** `/api/permissoes/{id}` - Deletar permissão

## Exemplos de Requisições

### Criar uma Pessoa (POST)
```json
{
  "nome": "João Silva",
  "cpf": "12345678901",
  "dataNascimento": "1990-05-15",
  "sexo": "M",
  "celular": "11987654321",
  "estadoCivil": "SOLTEIRO",
  "statusCadastro": "ATIVO"
}
```

### Buscar Pessoa (GET)
```
GET http://localhost:8080/gisa-api/api/pessoas/1
```

### Atualizar Pessoa (PUT)
```json
{
  "nome": "João Silva Santos",
  "celular": "11999999999"
}
```

### Deletar Pessoa (DELETE)
```
DELETE http://localhost:8080/gisa-api/api/pessoas/1
```

## Configurações Necessárias

### application.properties
Certifique-se de que o arquivo `application.properties` contém:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Dependências (pom.xml)
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- ojdbc11 (driver Oracle)

## Status Codes

- **200 OK**: Requisição bem-sucedida
- **201 Created**: Recurso criado com sucesso
- **204 No Content**: Deletado com sucesso
- **400 Bad Request**: Dados inválidos
- **404 Not Found**: Recurso não encontrado
- **500 Internal Server Error**: Erro no servidor

## Arquitetura

```
Controller Layer (REST Endpoints)
    ↓
Service Layer (Business Logic)
    ↓
Repository Layer (Data Access)
    ↓
Database (Oracle SQL)
```

A aplicação segue o padrão de arquitetura em 3 camadas:
- **Controllers**: Expõem os endpoints REST
- **Services**: Contêm a lógica de negócio e validações
- **Repositories**: Acesso aos dados através do Spring Data JPA

## Como Usar

1. Configure o banco de dados Oracle
2. Atualize as credenciais em `application.properties`
3. Execute `mvn clean install`
4. Execute `mvn spring-boot:run`
5. Acesse a API em `http://localhost:8080/gisa-api`

## Dicas de Desenvolvimento

- Use Postman ou Insomnia para testar os endpoints
- Os IDs são gerados automaticamente pelo banco de dados
- As atualizações são parciais (PATCH-like), enviando apenas os campos desejados
- Os relacionamentos entre entidades são mantidos através de Foreign Keys
