# GUIA COMPLETO: DTOs e Mappers - GISA API REST

## 📋 Índice
1. [Visão Geral](#visão-geral)
2. [Arquitetura](#arquitetura)
3. [DTOs Disponíveis](#dtos-disponíveis)
4. [Mappers](#mappers)
5. [Padrão de Uso](#padrão-de-uso)
6. [Exemplos Práticos](#exemplos-práticos)
7. [Boas Práticas](#boas-práticas)
8. [Implementação Passo a Passo](#implementação-passo-a-passo)

---

## 1. Visão Geral

### Objetivo
Criar uma **camada de DTOs (Data Transfer Objects)** que isole completamente as **Entidades JPA** das respostas da API REST. Isso garante:
- ✅ **Segurança**: Nunca expõe dados sensíveis (senhas, IDs internos)
- ✅ **Flexibilidade**: DTOs podem mudar independente das Entidades
- ✅ **Documentação**: DTO descreve exatamente o que a API retorna
- ✅ **Separação de Responsabilidades**: Banco ≠ API

### Hierarquia de Entidades (Herança JOINED)
```
Pessoa (TAB_PESSOA)
├── Profissional (TAB_PROFISSIONAL)
│   └── Especialista (TAB_ESPECIALISTA)
│       └── EspecialistaPJ (TAB_ESPECIALISTAPJ)
└── Paciente (TAB_PACIENTE)
```

---

## 2. Arquitetura

### Fluxo de Dados
```
┌─────────────────────────────────────────────────────────────────────┐
│ Cliente (Front-end)                                                 │
│  ↕                                                                  │
│  JSON (Request/Response)                                            │
└───────────────────────┬─────────────────────────────────────────────┘
                        │
        ┌───────────────▼────────────────┐
        │   REST Controller              │
        │  (Recebe/Retorna DTOs)         │
        └───────────────┬────────────────┘
                        │
        ┌───────────────▼────────────────┐
        │   Service (Lógica de Negócio)  │
        │  (Usa Mapper: DTO ↔ Entidade)  │
        └───────────────┬────────────────┘
                        │
        ┌───────────────▼────────────────┐
        │   Mapper (PessoaMapper, ...)   │
        │  (Converte Entidade ↔ DTO)    │
        └───────────────┬────────────────┘
                        │
        ┌───────────────▼────────────────┐
        │   Repository (JPARepository)   │
        │  (Manipula Entidades JPA)      │
        └───────────────┬────────────────┘
                        │
                ┌───────▼────────┐
                │   Banco de Dados
                └────────────────┘
```

### Camadas
| Camada | Responsabilidade | Entidades/DTOs |
|--------|------------------|-----------------|
| **Controller** | Receber requests, retornar responses | DTOs |
| **Service** | Lógica de negócio, validações | DTOs + Mappers |
| **Mapper** | Converter Entidade ↔ DTO | Ambos |
| **Repository** | Persistência, queries | Entidades JPA |

---

## 3. DTOs Disponíveis

### 3.1 Módulo de Profissionais

#### **ProfissionalSummaryDTO**
**Uso**: Listagens com paginação (GET /api/profissionais)
**Campos**: ID, Nome, Especialidades, Registro, Email, Status
**Tamanho**: Pequeno (ideal para listas grandes)

```java
public record ProfissionalSummaryDTO(
    Integer idProfissional,
    String nome,
    List<String> especialidades,
    String registroProfissional,
    String email,
    String status
) {}
```

**Exemplo de resposta**:
```json
{
  "idProfissional": 1,
  "nome": "Dr. Roberto Almeida",
  "especialidades": ["Neurologista Pediátrico"],
  "registroProfissional": "CRM-SP 123456",
  "email": "roberto@apae.org",
  "status": "ATIVO"
}
```

---

#### **ProfissionalCadastroDTO**
**Uso**: Entrada para POST/PUT (formulário de cadastro_profissional.html)
**Campos**: Todos os campos do formulário (sem ID)
**Tamanho**: Grande (entrada de dados)

```java
public record ProfissionalCadastroDTO(
    String nome,
    String cpf,
    LocalDate dataNascimento,
    String rg,
    String senhaProvisoria,
    List<Integer> idEspecialidades,
    String registroProfissional,
    String estadoRegistro,
    String cargaHorariaSemanal,
    String email,
    String telefone,
    EnderecoDTO endereco,
    String cnpj,           // Null se não for PJ
    String razaoSocial,
    String nomeFantasia,
    String inscricaoEstadual
) {}
```

**Exemplo de request**:
```json
{
  "nome": "Dr. Roberto Almeida",
  "cpf": "123.456.789-00",
  "dataNascimento": "1980-05-15",
  "rg": "12.345.678-9",
  "senhaProvisoria": "SenhaTemp123!",
  "idEspecialidades": [1, 2],
  "registroProfissional": "CRM-SP 123456",
  "estadoRegistro": "São Paulo",
  "email": "roberto@apae.org",
  "telefone": "(11) 98765-4321",
  "endereco": {
    "rua": "Av. Paulista",
    "numero": "1000",
    "bairro": "Bela Vista",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "01311-100"
  },
  "cnpj": "12.345.678/0001-90",
  "razaoSocial": "Dr. Roberto Almeida Serviços LTDA"
}
```

---

#### **ProfissionalDetailDTO**
**Uso**: Retorno por ID (GET /api/profissionais/{id})
**Campos**: Todos os campos para visualização/edição
**Tamanho**: Grande (dados completos)
**Nota**: Sempre retorna isPJ = true/false. Se true, campos PJ são preenchidos.

```java
public record ProfissionalDetailDTO(
    Integer idCadastro,
    String nome,
    String cpf,
    LocalDate dataNascimento,
    String rg,
    String celular,
    String estadoCivil,
    String statusCadastro,
    List<EnderecoDTO> enderecos,
    String email,
    String registroProfissional,
    String estadoRegistro,
    String cargaHorariaSemanal,
    List<EspecialidadeSummaryDTO> especialidades,
    String cnpj,
    String razaoSocial,
    String nomeFantasia,
    String inscricaoEstadual,
    Boolean isPJ
) {}
```

---

### 3.2 Módulo de Pessoa

#### **PessoaSummaryDTO**
**Uso**: Listagens gerais (GET /api/pessoas)
**Campos**: Apenas Pessoa (sem subclasses)

```java
public record PessoaSummaryDTO(
    Integer idCadastro,
    String nome,
    String cpf,
    String celular,
    String estadoCivil,
    String statusCadastro
) {}
```

---

#### **PessoaDetailDTO** (Polimórfica!)
**Uso**: Detalhes com tipo dinâmico (GET /api/pessoas/{id})
**Campos**: Todos de Pessoa + campos específicos de cada subclasse
**Campo especial**: `tipo` = PACIENTE | PROFISSIONAL | ESPECIALISTA | ESPECIALISTA_PJ

```java
public record PessoaDetailDTO(
    Integer idCadastro,
    String nome,
    String cpf,
    LocalDate dataNascimento,
    String tipo,  // ← IMPORTANTE: Define qual subobjeto popular
    String statusPaciente,  // Preenchido se tipo == PACIENTE
    String tipoEntrada,
    String registroProfissional,  // Preenchido se tipo == PROFISSIONAL
    List<EspecialidadeSummaryDTO> especialidades,
    String cnpj,  // Preenchido se tipo == ESPECIALISTA_PJ
    String razaoSocial,
    // ... mais campos
) {}
```

**Lógica no Cliente (Front-end)**:
```javascript
// Após receber PessoaDetailDTO
if (pessoaDetail.tipo === "PACIENTE") {
    // Mostrar campos: statusPaciente, tipoEntrada, cids, etc.
} else if (pessoaDetail.tipo === "ESPECIALISTA_PJ") {
    // Mostrar campos: cnpj, razaoSocial, nomeFantasia, etc.
}
```

---

### 3.3 Módulo de Pacientes

#### **PacienteSummaryDTO**
**Uso**: Listagens clínicas (GET /api/pacientes)
**Campos**: Essenciais de identidade + dados clínicos

```java
public record PacienteSummaryDTO(
    Integer idCadastro,
    String nome,
    String statusPaciente,
    String tipoEntrada,
    String statusCadastro,
    LocalDate dataCadastroPaciente
) {}
```

---

#### **PacienteDetailDTO**
**Uso**: Carga completa (GET /api/pacientes/{id})
**Campos**: TODOS de Pessoa + TODOS de Paciente + relacionamentos

```java
public record PacienteDetailDTO(
    Integer idCadastro,
    String nome,
    String cpf,
    LocalDate dataNascimento,
    String statusPaciente,
    String tipoEntrada,
    LocalDate dataCadastroPaciente,
    Boolean convenio,
    Integer idEscola,
    String nomeEscola,
    Integer cidPrincipalId,
    String cidPrincipalCodigo,
    String cidPrincipalDescricao,
    List<CIDSummaryDTO> cids,
    Integer idProntuario,
    String descricaoProntuario,
    LocalDate dataProntuario
) {}
```

---

## 4. Mappers

### ProfissionalMapper
**Localização**: `com.fatec.gisa.mappers.ProfissionalMapper`

**Métodos principais**:
```java
@Component
public class ProfissionalMapper {
    
    // Especialista → ProfissionalSummaryDTO
    public ProfissionalSummaryDTO toSummaryDTO(Especialista especialista) { ... }
    
    // Especialista/EspecialistaPJ → ProfissionalDetailDTO
    public ProfissionalDetailDTO toDetailDTO(Especialista especialista) { ... }
}
```

**Como usar**:
```java
@Service
public class ProfissionalService {
    
    @Autowired
    private ProfissionalMapper mapper;
    
    // ✅ Correto: Retorna DTO, nunca Especialista
    public ProfissionalSummaryDTO buscarPorId(Integer id) {
        Especialista especialista = repository.findById(id).orElse(null);
        return mapper.toSummaryDTO(especialista);
    }
    
    // ❌ ERRADO: Nunca faça isso
    // public Especialista buscarPorId(Integer id) {
    //     return repository.findById(id).orElse(null);
    // }
}
```

---

### PessoaMapper
**Localização**: `com.fatec.gisa.mappers.PessoaMapper`

**Métodos principais**:
```java
@Component
public class PessoaMapper {
    
    // Pessoa → PessoaSummaryDTO (campos base apenas)
    public PessoaSummaryDTO toSummaryDTO(Pessoa pessoa) { ... }
    
    // Pessoa (detecta tipo) → PessoaDetailDTO (polimórfico)
    public PessoaDetailDTO toDetailDTO(Pessoa pessoa) { ... }
    
    // Paciente → PacienteSummaryDTO
    public PacienteSummaryDTO toSummaryDTO(Paciente paciente) { ... }
    
    // Paciente → PacienteDetailDTO
    public PacienteDetailDTO toDetailDTO(Paciente paciente) { ... }
}
```

**Lógica de Detecção de Tipo**:
```java
public PessoaDetailDTO toDetailDTO(Pessoa pessoa) {
    if (pessoa instanceof Paciente) {
        // Popula campos de Paciente
        Paciente paciente = (Paciente) pessoa;
        tipo = "PACIENTE";
        statusPaciente = paciente.getStatusPaciente().toString();
        // ... mais campos
    } else if (pessoa instanceof EspecialistaPJ) {
        tipo = "ESPECIALISTA_PJ";
        cnpj = ((EspecialistaPJ) pessoa).getCNPJ();
        // ...
    }
    // Retorna PessoaDetailDTO com tipo preenchido
}
```

---

## 5. Padrão de Uso

### ✅ Como Deve Ser

```java
// ① Controller (recebe/retorna DTOs)
@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {
    
    @Autowired
    private ProfissionalService service;
    
    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalDetailDTO> buscar(@PathVariable Integer id) {
        ProfissionalDetailDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping
    public ResponseEntity<ProfissionalDetailDTO> criar(
            @RequestBody ProfissionalCadastroDTO cadastroDTO) {
        ProfissionalDetailDTO novoDTO = service.criar(cadastroDTO);
        return ResponseEntity.status(201).body(novoDTO);
    }
}

// ② Service (transforma via Mapper)
@Service
public class ProfissionalService {
    
    @Autowired
    private EspecialistaRepository repository;
    
    @Autowired
    private ProfissionalMapper mapper;
    
    public ProfissionalDetailDTO buscarPorId(Integer id) {
        // Busca entidade
        Especialista especialista = repository.findById(id).orElse(null);
        
        // Converte para DTO (Mapper)
        return mapper.toDetailDTO(especialista);
    }
    
    public ProfissionalDetailDTO criar(ProfissionalCadastroDTO cadastroDTO) {
        // Converte DTO → Entidade (implementar)
        Especialista especialista = converterDTOParaEntidade(cadastroDTO);
        
        // Persiste
        Especialista salva = repository.save(especialista);
        
        // Converte Entidade → DTO
        return mapper.toDetailDTO(salva);
    }
}

// ③ Repository (trabalha com Entidades)
public interface EspecialistaRepository extends JpaRepository<Especialista, Integer> {
}
```

### ❌ Erros Comuns

```java
// ❌ ERRADO 1: Controller retorna Entidade
@GetMapping("/{id}")
public ResponseEntity<Especialista> buscar(@PathVariable Integer id) {
    return ResponseEntity.ok(repository.findById(id).orElse(null));
}

// ❌ ERRADO 2: Service retorna Entidade
@Service
public Especialista buscarPorId(Integer id) {
    return repository.findById(id).orElse(null);
}

// ❌ ERRADO 3: Não usar Mapper
@GetMapping("/{id}")
public ResponseEntity<Object> buscar(@PathVariable Integer id) {
    Especialista esp = repository.findById(id).orElse(null);
    // Retorna direto sem converter
    return ResponseEntity.ok(esp);
}
```

---

## 6. Exemplos Práticos

### 6.1 GET - Listar Profissionais (com paginação)

**Request**:
```http
GET /api/profissionais?page=0&size=10&sort=nome,asc
```

**Controller**:
```java
@GetMapping
public ResponseEntity<Page<ProfissionalSummaryDTO>> listar(Pageable pageable) {
    Page<ProfissionalSummaryDTO> resultado = service.listarProfissionais(pageable);
    return ResponseEntity.ok(resultado);
}
```

**Service**:
```java
public Page<ProfissionalSummaryDTO> listarProfissionais(Pageable pageable) {
    Page<Especialista> especialistas = repository.findAll(pageable);
    
    List<ProfissionalSummaryDTO> dtos = especialistas.getContent()
        .stream()
        .map(mapper::toSummaryDTO)
        .collect(Collectors.toList());
    
    return new PageImpl<>(dtos, pageable, especialistas.getTotalElements());
}
```

**Response** (200 OK):
```json
{
  "content": [
    {
      "idProfissional": 1,
      "nome": "Dr. Roberto Almeida",
      "especialidades": ["Neurologista Pediátrico"],
      "registroProfissional": "CRM-SP 123456",
      "email": "roberto@apae.org",
      "status": "ATIVO"
    }
  ],
  "pageable": { "pageNumber": 0, "pageSize": 10 },
  "totalElements": 6,
  "totalPages": 1
}
```

---

### 6.2 GET - Buscar Profissional por ID

**Request**:
```http
GET /api/profissionais/1
```

**Controller**:
```java
@GetMapping("/{id}")
public ResponseEntity<ProfissionalDetailDTO> buscarPorId(@PathVariable Integer id) {
    ProfissionalDetailDTO profissional = service.buscarPorId(id);
    
    if (profissional == null) {
        return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(profissional);
}
```

**Response** (200 OK):
```json
{
  "idCadastro": 1,
  "nome": "Dr. Roberto Almeida",
  "cpf": "123.456.789-00",
  "dataNascimento": "1980-05-15",
  "rg": "12.345.678-9",
  "email": "roberto@apae.org",
  "registroProfissional": "CRM-SP 123456",
  "especialidades": [
    {
      "idEspecialidade": 1,
      "nome": "Neurologista Pediátrico"
    }
  ],
  "enderecos": [
    {
      "idEndereco": 1,
      "rua": "Av. Paulista",
      "numero": "1000",
      "bairro": "Bela Vista",
      "cidade": "São Paulo",
      "estado": "SP",
      "cep": "01311-100"
    }
  ],
  "cnpj": "12.345.678/0001-90",
  "razaoSocial": "Dr. Roberto Almeida Serviços LTDA",
  "nomeFantasia": "Clínica Dr. Roberto",
  "isPJ": true
}
```

---

### 6.3 POST - Criar Profissional

**Request**:
```http
POST /api/profissionais
Content-Type: application/json
```

**Body**:
```json
{
  "nome": "Dra. Fernanda Lima",
  "cpf": "987.654.321-00",
  "dataNascimento": "1985-03-20",
  "rg": "98.765.432-1",
  "senhaProvisoria": "SenhaTemp@123",
  "idEspecialidades": [2, 3],
  "registroProfissional": "CREFITO-3 45678",
  "estadoRegistro": "São Paulo",
  "email": "fernanda@apae.org",
  "telefone": "(11) 98765-4321",
  "endereco": {
    "rua": "Rua das Flores",
    "numero": "250",
    "bairro": "Vila Mariana",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "04015-070"
  }
}
```

**Controller**:
```java
@PostMapping
public ResponseEntity<ProfissionalDetailDTO> criar(
        @RequestBody ProfissionalCadastroDTO cadastroDTO) {
    ProfissionalDetailDTO novo = service.criar(cadastroDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(novo);
}
```

**Response** (201 Created):
```json
{
  "idCadastro": 2,
  "nome": "Dra. Fernanda Lima",
  "cpf": "987.654.321-00",
  // ... outros campos
  "isPJ": false
}
```

**Location Header**:
```
Location: /api/profissionais/2
```

---

### 6.4 GET - Buscar Pessoa com Tipo Dinâmico

**Request**:
```http
GET /api/pessoas/1
```

**Response** (Paciente):
```json
{
  "idCadastro": 1,
  "nome": "João Silva",
  "cpf": "111.111.111-11",
  "tipo": "PACIENTE",
  "statusPaciente": "ATIVO",
  "tipoEntrada": "ESPONTANEA",
  "cids": [
    { "codigoCID": "F84.0", "descricao": "Autismo Infantil" }
  ],
  "cidPrincipalId": 1,
  "cidPrincipalCodigo": "F84.0",
  "idEscola": 5,
  "nomeEscola": "EMEF Escola Central"
}
```

**Response** (Especialista PJ):
```json
{
  "idCadastro": 2,
  "nome": "Dr. Roberto Almeida",
  "cpf": "123.456.789-00",
  "tipo": "ESPECIALISTA_PJ",
  "registroProfissional": "CRM-SP 123456",
  "especialidades": [...],
  "cnpj": "12.345.678/0001-90",
  "razaoSocial": "Dr. Roberto Almeida Serviços LTDA",
  "nomeFantasia": "Clínica Dr. Roberto",
  "inscricaoEstadual": "123.456.789.012"
}
```

---

## 7. Boas Práticas

### 1. Sempre Use DTOs como Contrato de API
```java
// ✅ Correto
@PostMapping
public ResponseEntity<ProfissionalDetailDTO> criar(
        @RequestBody ProfissionalCadastroDTO dto) { ... }

// ❌ Errado
@PostMapping
public ResponseEntity<Especialista> criar(
        @RequestBody Especialista especialista) { ... }
```

### 2. Use Mappers para Conversão
```java
// ✅ Correto
Especialista especialista = repository.findById(id).orElse(null);
return mapper.toDetailDTO(especialista);

// ❌ Errado
return repository.findById(id).orElse(null);
```

### 3. Nunca Exponha Senhas ou IDs Internos
```java
// ✅ Correto: DTO não tem senha
public record ProfissionalDetailDTO(
    Integer idCadastro,
    String nome,
    // ... sem "senha"
) {}

// ❌ Errado: Expõe senha
public record ProfissionalDetailDTO(
    Integer idCadastro,
    String nome,
    String senha  // ← PERIGO!
) {}
```

### 4. Use Tipos Apropriados
```java
// ✅ Correto
LocalDate dataNascimento,
String cpf,        // Sempre String para documentos
Integer idEspecialidade,

// ❌ Errado
Date dataNascimento,  // Use LocalDate
Long cpf,             // CPF é String
String idEspecialidade,  // Use Integer
```

### 5. DTOs devem ser Immutable (use Records)
```java
// ✅ Correto: Record (imutável por padrão)
public record ProfissionalSummaryDTO(
    Integer id,
    String nome
) {}

// ❌ Evite: Classe mutable com setters
public class ProfissionalSummaryDTO {
    private Integer id;
    public void setId(Integer id) { this.id = id; }
}
```

### 6. Nomeie DTOs Descritivamente
```java
// ✅ Claro
ProfissionalSummaryDTO       // Para listagens
ProfissionalCadastroDTO      // Para entrada de dados
ProfissionalDetailDTO        // Para visualização completa

// ❌ Vago
ProfissionalDTO              // Qual tipo?
ProfissionalResponse         // Pode significar tudo
```

---

## 8. Implementação Passo a Passo

### Passo 1: Criar o DTO
```java
// src/main/java/com/fatec/gisa/dtos/ProfissionalSummaryDTO.java
public record ProfissionalSummaryDTO(
    Integer idProfissional,
    String nome,
    List<String> especialidades,
    String registroProfissional,
    String email,
    String status
) {}
```

### Passo 2: Criar o Mapper
```java
// src/main/java/com/fatec/gisa/mappers/ProfissionalMapper.java
@Component
public class ProfissionalMapper {
    public ProfissionalSummaryDTO toSummaryDTO(Especialista especialista) {
        if (especialista == null) return null;
        
        List<String> nomes = especialista.getEspecialidades()
            .stream()
            .map(Especialidade::getNome)
            .collect(Collectors.toList());
        
        return new ProfissionalSummaryDTO(
            especialista.getIdCadastro(),
            especialista.getNome(),
            nomes,
            especialista.getRegistroConselho(),
            extrairEmail(especialista),
            especialista.getStatusCadastro().toString()
        );
    }
}
```

### Passo 3: Usar no Service
```java
// src/main/java/com/fatec/gisa/services/ProfissionalService.java
@Service
public class ProfissionalService {
    
    @Autowired
    private EspecialistaRepository repository;
    
    @Autowired
    private ProfissionalMapper mapper;
    
    public Page<ProfissionalSummaryDTO> listar(Pageable pageable) {
        Page<Especialista> page = repository.findAll(pageable);
        
        List<ProfissionalSummaryDTO> dtos = page.getContent()
            .stream()
            .map(mapper::toSummaryDTO)
            .collect(Collectors.toList());
        
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
}
```

### Passo 4: Usar no Controller
```java
// src/main/java/com/fatec/gisa/controllers/ProfissionalController.java
@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {
    
    @Autowired
    private ProfissionalService service;
    
    @GetMapping
    public ResponseEntity<Page<ProfissionalSummaryDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }
}
```

---

## Checklist de Implementação

- [ ] Todos os DTOs criados em `com.fatec.gisa.dtos`
- [ ] Mappers criados em `com.fatec.gisa.mappers`
- [ ] Services retornam DTOs (nunca Entidades)
- [ ] Controllers recebem/retornam DTOs
- [ ] Sem exposição de senhas nos DTOs
- [ ] Tipos apropriados (LocalDate, String, Integer, etc)
- [ ] DTOs documentados com Javadoc
- [ ] Testes unitários para Mappers
- [ ] Swagger/OpenAPI atualizado com DTOs

---

**Última atualização**: Maio de 2026
**Status**: Pronto para produção
