# Arquitetura de DTOs - GISA API REST

## 📦 Estrutura de Pacotes Criada

```
com.fatec.gisa/
├── dtos/                              # Data Transfer Objects
│   ├── EspecialidadeSummaryDTO.java
│   ├── EnderecoDTO.java
│   ├── CIDSummaryDTO.java
│   ├── ProfissionalSummaryDTO.java     ← Para listagens de profissionais
│   ├── ProfissionalCadastroDTO.java    ← Para POST/PUT (entrada)
│   ├── ProfissionalDetailDTO.java      ← Para GET by ID (saída completa)
│   ├── PessoaSummaryDTO.java           ← Para listagens gerais de pessoas
│   ├── PessoaDetailDTO.java            ← Para GET by ID (polimórfico)
│   ├── PacienteSummaryDTO.java         ← Para listagens de pacientes
│   └── PacienteDetailDTO.java          ← Para GET by ID (carga completa)
│
├── mappers/                           # Conversores Entidade ↔ DTO
│   ├── ProfissionalMapper.java         ← Especialista → DTOs (saída)
│   ├── ProfissionalDTOMapper.java      ← DTOs → Especialista (entrada)
│   └── PessoaMapper.java               ← Pessoa/Paciente → DTOs (saída)
│
├── controllers/                       # REST Controllers (exemplo)
│   └── ProfissionalControllerExample.java
│
├── services/                          # Serviços (lógica de negócio)
│   ├── ProfissionalService.java        ← Usa Mappers + Repositories
│   └── PessoaService.java
│
├── repositories/                      # Acesso a dados (JPA)
│   ├── EspecialistaRepository.java
│   ├── PessoaRepository.java
│   └── ... outros
│
├── models/                            # Entidades JPA (Pessoa, Especialista, etc)
│   └── ... entidades existentes
│
└── examples/                          # Exemplos de implementação
    └── ProfissionalServiceExemploCompleto.java
```

---

## 🎯 DTOs por Funcionalidade

### Profissionais

| DTO | Uso | Campos | Tamanho |
|-----|-----|--------|--------|
| **ProfissionalSummaryDTO** | GET /api/profissionais | ID, Nome, Especialidades, Registro, Email, Status | Pequeno |
| **ProfissionalCadastroDTO** | POST /PUT /api/profissionais | Todos os campos do formulário | Grande |
| **ProfissionalDetailDTO** | GET /api/profissionais/{id} | Todos os campos + isPJ flag | Grande |

### Pessoa (Polimórfica)

| DTO | Uso | Campos | Tipo |
|-----|-----|--------|------|
| **PessoaSummaryDTO** | GET /api/pessoas | Campos base de Pessoa | Simples |
| **PessoaDetailDTO** | GET /api/pessoas/{id} | Dinâmico conforme tipo | Polimórfico |

### Pacientes

| DTO | Uso | Campos | Carga |
|-----|-----|--------|--------|
| **PacienteSummaryDTO** | GET /api/pacientes | ID, Nome, Status, Tipo | Leve |
| **PacienteDetailDTO** | GET /api/pacientes/{id} | Completo com CIDs, Prontuário, Escola | Completa |

---

## 🔄 Fluxos de Dados

### GET - Listar Profissionais
```
Client → GET /api/profissionais?page=0&size=20
         ↓
    Controller.listar(pageable)
         ↓
    Service.listarProfissionais(pageable)
         ↓
    Repository.findAll(pageable)  → Page<Especialista>
         ↓
    Mapper.toSummaryDTO(especialista) ×N
         ↓
    Service.retorna Page<ProfissionalSummaryDTO>
         ↓
    Controller retorna JSON
         ↓
Client recebe response com ProfissionalSummaryDTO[]
```

### GET - Buscar Profissional por ID
```
Client → GET /api/profissionais/1
         ↓
    Controller.buscarPorId(1)
         ↓
    Service.buscarPorId(1)
         ↓
    Repository.findById(1) → Especialista
         ↓
    Mapper.toDetailDTO(especialista)  → ProfissionalDetailDTO (completo, com isPJ)
         ↓
    Controller retorna JSON
         ↓
Client recebe ProfissionalDetailDTO com TODOS os campos (para preencher formulário)
```

### POST - Criar Profissional
```
Client → POST /api/profissionais
         Body: ProfissionalCadastroDTO (JSON)
         ↓
    Controller.criar(cadastroDTO)
         ↓
    Service.criarProfissional(cadastroDTO)
         ├─ DTOMapper.toEntity(cadastroDTO) → Nova Especialista
         ├─ Repository.save(especialista) → Especialista persistida
         ├─ Criar Usuario associado
         ├─ Repository.save(usuario)
         └─ Mapper.toDetailDTO(especialista) → ProfissionalDetailDTO
         ↓
    Controller retorna 201 Created + JSON
         ↓
Client recebe ProfissionalDetailDTO (com ID gerado)
         ↓
Location: /api/profissionais/{id}
```

### PUT - Atualizar Profissional
```
Client → PUT /api/profissionais/1
         Body: ProfissionalCadastroDTO (JSON)
         ↓
    Controller.atualizar(1, cadastroDTO)
         ↓
    Service.atualizarProfissional(1, cadastroDTO)
         ├─ Repository.findById(1) → Especialista existente
         ├─ DTOMapper.updateEntity(cadastroDTO, especialista) → Entidade atualizada
         ├─ Repository.save(especialista) → Persistida
         └─ Mapper.toDetailDTO(especialista) → ProfissionalDetailDTO
         ↓
    Controller retorna 200 OK + JSON
         ↓
Client recebe ProfissionalDetailDTO (atualizado)
```

### GET - Buscar Pessoa Polimórfica
```
Client → GET /api/pessoas/1
         ↓
    Repository.findById(1) → Pessoa (pode ser Paciente, Profissional, etc)
         ↓
    Mapper.toDetailDTO(pessoa)
    ├─ Detecta: pessoa instanceof Paciente?
    ├─ Se sim → tipo = "PACIENTE", popula statusPaciente, cids, etc
    ├─ Se não, detecta: instanceof EspecialistaPJ?
    ├─ Se sim → tipo = "ESPECIALISTA_PJ", popula cnpj, razaoSocial, etc
    └─ Retorna PessoaDetailDTO com tipo + campos dinâmicos
         ↓
    Controller retorna JSON
         ↓
Client lê campo "tipo" e exibe campos correspondentes
    if (pessoaDetail.tipo === "PACIENTE") → mostra CIDs
    if (pessoaDetail.tipo === "ESPECIALISTA_PJ") → mostra CNPJ
```

---

## 📋 Checklist de Uso

### Para GET (Leitura)

- [ ] Use **SummaryDTO** para listagens com paginação
  - Retorna apenas campos essenciais
  - Menor payload JSON
  - Melhor performance em listas grandes

- [ ] Use **DetailDTO** para GET by ID
  - Retorna todos os campos
  - Inclui relacionamentos
  - Pronto para preencher formulários

- [ ] Use **Mapper** para conversão Entidade → DTO
  ```java
  Especialista esp = repository.findById(id).orElse(null);
  return mapper.toDetailDTO(esp);  // ✅ Correto
  return esp;                       // ❌ Errado
  ```

### Para POST (Criação)

- [ ] Receba **CadastroDTO** do Controller
  ```java
  @PostMapping
  public ResponseEntity<?> criar(@RequestBody ProfissionalCadastroDTO dto) { ... }
  ```

- [ ] Use **DTOMapper** para converter DTO → Entidade
  ```java
  Especialista esp = dtoMapper.toEntity(cadastroDTO);
  Especialista salva = repository.save(esp);
  ```

- [ ] Crie Usuario associado (se necessário)
  ```java
  Usuario usuario = new Usuario();
  usuario.setPessoa(salva);
  usuario.setSenha(criptografar(cadastroDTO.senhaProvisoria()));
  usuarioRepository.save(usuario);
  ```

- [ ] Retorne **DetailDTO** (novo recurso criado)
  ```java
  return mapper.toDetailDTO(salva);
  ```

### Para PUT (Atualização)

- [ ] Receba **CadastroDTO** + ID
  ```java
  @PutMapping("/{id}")
  public ResponseEntity<?> atualizar(@PathVariable Integer id,
                                     @RequestBody ProfissionalCadastroDTO dto) { ... }
  ```

- [ ] Use **DTOMapper.updateEntity()** para merge
  ```java
  Especialista existente = repository.findById(id).orElse(null);
  Especialista atualizada = dtoMapper.updateEntity(cadastroDTO, existente);
  repository.save(atualizada);
  ```

- [ ] Retorne **DetailDTO** (recurso atualizado)

### Para DELETE (Deleção)

- [ ] Preferir deleção **lógica** (marcar inativo)
  ```java
  especialista.setStatusCadastro(StatusCadastro.INATIVO);
  repository.save(especialista);
  ```

- [ ] Se deleção física, validar referências
  ```java
  usuarioRepository.deleteById(id);      // Deletar Usuario primeiro
  especialistaRepository.deleteById(id); // Depois Especialista
  ```

---

## 🛡️ Segurança

### Nunca Exponha em DTOs

```java
// ❌ ERRADO - DTOs nunca devem ter:
public record ProfissionalDetailDTO(
    String senha,                    // ← PERIGO!
    String tokenJWT,                 // ← PERIGO!
    List<Permissao> permissoes,      // ← Pode expor lógica
    @Version Long version            // ← Metadados internos
) {}

// ✅ CORRETO - DTOs apenas retornam:
public record ProfissionalDetailDTO(
    Integer idCadastro,              // ✅ ID público
    String nome,                     // ✅ Nome
    String email,                    // ✅ Email
    String registroProfissional,     // ✅ Dados públicos
    Boolean isPJ                     // ✅ Flag público
) {}
```

### Validações

```java
// No DTO/Service, valide entrada:
public ProfissionalDetailDTO criar(ProfissionalCadastroDTO dto) {
    if (dto.nome() == null || dto.nome().isBlank()) {
        throw new IllegalArgumentException("Nome obrigatório");
    }
    if (!isCPFValido(dto.cpf())) {
        throw new IllegalArgumentException("CPF inválido");
    }
    // ...
}
```

---

## 📚 Referências de Implementação

1. **[DTO_E_MAPPERS_GUIA_COMPLETO.md](DTO_E_MAPPERS_GUIA_COMPLETO.md)**
   - Guia completo com exemplos detalhados
   - Padrões de uso
   - Boas práticas

2. **ProfissionalControllerExample.java**
   - Exemplo de Controller usando DTOs
   - Endpoints GET, POST, PUT, DELETE

3. **ProfissionalServiceExemploCompleto.java**
   - Implementação completa de Service
   - Fluxo POST, PUT, GET com validações
   - Tratamento de erros

4. **ProfissionalMapper.java** e **ProfissionalDTOMapper.java**
   - Mappers prontos para usar
   - Conversão bidirecional
   - Tratamento de relacionamentos

---

## 🚀 Próximos Passos

1. **Implementar Validações**
   - Use `@Valid` e `@Validated` (Spring Validation)
   - Adicione `@NotNull`, `@Size`, `@Email`, etc nos DTOs

2. **Adicionar Swagger/OpenAPI**
   - Use `@ApiResponse`, `@ApiModel` nos DTOs
   - Documenta automaticamente a API

3. **Implementar Testes Unitários**
   - Teste Mappers (Entidade ↔ DTO)
   - Teste Services (lógica de negócio)
   - Teste Controllers (endpoints)

4. **Configurar Exception Handling**
   - `@ControllerAdvice` para erros globais
   - Retorne DTOs de erro consistentes

5. **Implementar Criptografia de Senha**
   - Use Spring Security `BCryptPasswordEncoder`
   - Remova placeholder em `criptografarSenha()`

---

**Última atualização**: Maio de 2026  
**Status**: Pronto para usar  
**Versão**: 1.0
