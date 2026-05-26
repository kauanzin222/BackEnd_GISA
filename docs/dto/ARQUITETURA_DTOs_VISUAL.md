# 📊 Diagrama de Arquitetura DTOs - GISA

## Hierarquia de Entidades vs DTOs

```
┌─────────────────────────────────────────────────────────────────────────┐
│                         ENTIDADES JPA (Banco)                           │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│  Pessoa (TAB_PESSOA)                                                    │
│  ├─ idCadastro, nome, cpf, dataNascimento, sexo, celular              │
│  ├─ estadoCivil, statusCadastro, numCNS, enderecos                    │
│  │                                                                     │
│  ├─→ Profissional (TAB_PROFISSIONAL)                                  │
│  │   ├─ cargo                                                         │
│  │   │                                                                │
│  │   └─→ Especialista (TAB_ESPECIALISTA)                              │
│  │       ├─ registroConselho                                          │
│  │       ├─ especialidades (ManyToMany)                               │
│  │       │                                                            │
│  │       └─→ EspecialistaPJ (TAB_ESPECIALISTAPJ)                      │
│  │           ├─ CNPJ, razaoSocial, nomeFantasia, inscricaoEstadual  │
│  │                                                                    │
│  └─→ Paciente (TAB_PACIENTE)                                          │
│      ├─ cids (ManyToMany), cidPrincipal, escola                      │
│      ├─ prontuario (OneToOne), statusPaciente, tipoEntrada           │
│      └─ dataCadastro, convenio                                        │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                         ┌──────────▼──────────┐
                         │  MAPPERS            │
                         │ (Conversores)       │
                         └──────────┬──────────┘
                                    │
┌─────────────────────────────────────────────────────────────────────────┐
│                        DTOs (API REST)                                   │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│  PROFISSIONAL (Summary)              PROFISSIONAL (Detail)             │
│  ├─ idProfissional                   ├─ idCadastro                     │
│  ├─ nome                             ├─ nome, cpf, dataNascimento    │
│  ├─ especialidades []                ├─ rg, celular, estadoCivil       │
│  ├─ registroProfissional             ├─ statusCadastro                 │
│  ├─ email                            ├─ email, registroProfissional    │
│  └─ status                           ├─ especialidades []              │
│                                      ├─ enderecos []                   │
│  PROFISSIONAL (Cadastro)             ├─ cnpj, razaoSocial             │
│  ├─ nome                             ├─ nomeFantasia, inscricaoEstadual│
│  ├─ cpf                              └─ isPJ (flag)                    │
│  ├─ dataNascimento                                                     │
│  ├─ rg                               PESSOA (Summary)                  │
│  ├─ senhaProvisoria                  ├─ idCadastro                     │
│  ├─ idEspecialidades []              ├─ nome                           │
│  ├─ registroProfissional             ├─ cpf, celular                   │
│  ├─ estadoRegistro                   ├─ estadoCivil                    │
│  ├─ cargaHorariaSemanal              └─ statusCadastro                 │
│  ├─ email, telefone                                                    │
│  ├─ endereco (DTO)                   PESSOA (Detail - Polimórfica)    │
│  ├─ cnpj, razaoSocial                ├─ Campos de Pessoa               │
│  ├─ nomeFantasia                     ├─ tipo: "PACIENTE"              │
│  └─ inscricaoEstadual                │        | "PROFISSIONAL"        │
│                                      │        | "ESPECIALISTA"        │
│  PACIENTE (Summary)                  │        | "ESPECIALISTA_PJ"     │
│  ├─ idCadastro                       ├─ Se PACIENTE:                  │
│  ├─ nome                             │  ├─ statusPaciente, tipoEntrada│
│  ├─ statusPaciente                   │  ├─ cids []                     │
│  ├─ tipoEntrada                      │  ├─ cidPrincipalId, escola      │
│  ├─ statusCadastro                   │  └─ prontuario                  │
│  └─ dataCadastroPaciente             ├─ Se ESPECIALISTA_PJ:           │
│                                      │  ├─ cnpj, razaoSocial           │
│  PACIENTE (Detail)                   │  ├─ especialidades []           │
│  ├─ Todos de Pessoa                  │  └─ inscricaoEstadual           │
│  ├─ Campos de Paciente               └─ ...mais campos                │
│  ├─ cids [], cidPrincipal                                             │
│  ├─ escola, prontuario               ENDERECO (DTO)                    │
│  └─ convenio, tipoEntrada            ├─ idEndereco                     │
│                                      ├─ rua, numero, complemento       │
│                                      ├─ bairro, cidade, estado, cep    │
│                                      └─ ...                            │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 🔄 Fluxos de Conversão

### GET - Leitura (Entidade → DTO)

```
┌──────────────────────┐
│  REST Client         │ ← GET /api/profissionais/1
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  Controller          │
│  buscarPorId(1)      │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  Service             │
│  repository.find(1)  │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  Repository          │
│  findById(1)         │
│  ↓                   │
│  Especialista        │◄─── ENTIDADE JPA
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  Mapper              │
│  toDetailDTO()       │◄─── CONVERSÃO
│  ↓                   │
│  DTO (Completo)      │◄─── DTO COM TODOS CAMPOS
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  Controller retorna  │
│  JSON                │◄─── JSON SERIALIZADO
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  REST Client         │
│  recebe DTO JSON     │
└──────────────────────┘
```

### POST - Criação (DTO → Entidade)

```
┌──────────────────────┐
│  REST Client         │ ← POST /api/profissionais
│  Body: CadastroDTO   │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  Controller          │
│  criar(CadastroDTO)  │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  Service             │
│  criar(dto)          │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  DTOMapper           │
│  toEntity(dto)       │◄─── CONVERSÃO INVERSA
│  ↓                   │
│  Especialista (nova) │◄─── ENTIDADE JPA NOVA
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  Repository          │
│  save(entidade)      │
│  ↓                   │
│  Especialista (com   │◄─── ENTIDADE PERSISTIDA
│  ID gerado)          │      (ID do BD)
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  Mapper              │
│  toDetailDTO()       │
│  ↓                   │
│  DetailDTO           │◄─── DTO COM ID + DADOS
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  Controller retorna  │
│  201 Created + JSON  │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│  REST Client         │
│  recebe DTO com ID   │
└──────────────────────┘
```

---

## 📋 Matriz de DTOs

```
┌──────────────────┬─────────────────┬──────────────┬─────────────────┬────────────┐
│ Módulo           │ DTO             │ Quando Usar  │ Campos          │ Tamanho    │
├──────────────────┼─────────────────┼──────────────┼─────────────────┼────────────┤
│ PROFISSIONAL     │                 │              │                 │            │
│                  │ Summary         │ GET list     │ ID,Nome,Espec   │ Pequeno    │
│                  │ Cadastro        │ POST/PUT     │ Todos form      │ Grande     │
│                  │ Detail          │ GET by ID    │ Completo+isPJ   │ Grande     │
├──────────────────┼─────────────────┼──────────────┼─────────────────┼────────────┤
│ PESSOA           │                 │              │                 │            │
│                  │ Summary         │ GET list     │ Base Pessoa     │ Pequeno    │
│                  │ Detail*         │ GET by ID    │ Dinâmico+tipo   │ Grande     │
│                  │ (*Polimórfica)  │              │                 │            │
├──────────────────┼─────────────────┼──────────────┼─────────────────┼────────────┤
│ PACIENTE         │                 │              │                 │            │
│                  │ Summary         │ GET list     │ ID+clínico      │ Pequeno    │
│                  │ Detail          │ GET by ID    │ Completo+CIDs   │ Grande     │
├──────────────────┼─────────────────┼──────────────┼─────────────────┼────────────┤
│ AUXILIAR         │                 │              │                 │            │
│                  │ Especialidade   │ Referencias  │ ID+Nome         │ Mínimo     │
│                  │ Endereco        │ Subobjs      │ Completo        │ Médio      │
│                  │ CID             │ References   │ Código+Desc     │ Mínimo     │
└──────────────────┴─────────────────┴──────────────┴─────────────────┴────────────┘
```

---

## 🎯 Estratégia de Uso por Endpoint

### Profissionais

```
┌────────────────────────────────────┬──────────────────────────────┐
│ Endpoint                           │ DTO Recomendado              │
├────────────────────────────────────┼──────────────────────────────┤
│ GET /api/profissionais             │ Page<SummaryDTO>             │
│ GET /api/profissionais?page=0      │ (paginado)                   │
│                                    │                              │
│ GET /api/profissionais/{id}        │ DetailDTO                    │
│ POST /api/profissionais            │ ← CadastroDTO (input)        │
│                                    │ → DetailDTO (output)         │
│ PUT /api/profissionais/{id}        │ ← CadastroDTO (input)        │
│                                    │ → DetailDTO (output)         │
│ DELETE /api/profissionais/{id}     │ void                         │
│                                    │                              │
│ GET /api/profissionais?nome=...    │ List<SummaryDTO>             │
│ GET /api/profissionais?espec=...   │ (filtrado)                   │
└────────────────────────────────────┴──────────────────────────────┘
```

### Pessoa (Polimórfica)

```
┌────────────────────────────────────┬──────────────────────────────┐
│ Endpoint                           │ DTO Recomendado              │
├────────────────────────────────────┼──────────────────────────────┤
│ GET /api/pessoas                   │ Page<SummaryDTO>             │
│ GET /api/pessoas?page=0            │ (base Pessoa só)             │
│                                    │                              │
│ GET /api/pessoas/{id}              │ DetailDTO*                   │
│ (*tipo detectado automaticamente)   │ {tipo: "PACIENTE" | ...}     │
│                                    │ Front escolhe campos         │
└────────────────────────────────────┴──────────────────────────────┘
```

### Pacientes

```
┌────────────────────────────────────┬──────────────────────────────┐
│ Endpoint                           │ DTO Recomendado              │
├────────────────────────────────────┼──────────────────────────────┤
│ GET /api/pacientes                 │ Page<SummaryDTO>             │
│ GET /api/pacientes?page=0          │ (clínico essencial)          │
│                                    │                              │
│ GET /api/pacientes/{id}            │ DetailDTO                    │
│ POST /api/pacientes                │ ← CadastroDTO?               │
│                                    │ → DetailDTO                  │
│ PUT /api/pacientes/{id}            │ ← CadastroDTO?               │
│                                    │ → DetailDTO                  │
│ DELETE /api/pacientes/{id}         │ void                         │
└────────────────────────────────────┴──────────────────────────────┘
```

---

## 📊 Resumo de Mappers Disponíveis

```
┌─────────────────────────────────────────────────────────────────┐
│                    MAPPERS (Conversores)                        │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│ ProfissionalMapper (Entidade → DTO)                             │
│ ├─ toSummaryDTO(Especialista) → ProfissionalSummaryDTO         │
│ ├─ toDetailDTO(Especialista) → ProfissionalDetailDTO           │
│ └─ Detecta EspecialistaPJ automaticamente                       │
│                                                                 │
│ ProfissionalDTOMapper (DTO → Entidade)                         │
│ ├─ toEntity(CadastroDTO) → Nova Especialista                   │
│ ├─ updateEntity(CadastroDTO, Existente) → Entidade atualizada  │
│ └─ Detecta PJ vs Especialista comum                            │
│                                                                 │
│ PessoaMapper (Entidade → DTO)                                   │
│ ├─ toSummaryDTO(Pessoa) → PessoaSummaryDTO                     │
│ ├─ toDetailDTO(Pessoa) → PessoaDetailDTO (polimórfico!)        │
│ ├─ toSummaryDTO(Paciente) → PacienteSummaryDTO                 │
│ └─ toDetailDTO(Paciente) → PacienteDetailDTO                   │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

---

## ⚙️ Stack Recomendado

```java
// Controller (REST)
@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {
    @Autowired private ProfissionalService service;
    
    // Recebe/Retorna APENAS DTOs
}

// Service (Negócio)
@Service
public class ProfissionalService {
    @Autowired private ProfissionalRepository repository;
    @Autowired private ProfissionalMapper mapper;           // DTO saída
    @Autowired private ProfissionalDTOMapper dtoMapper;    // DTO entrada
    
    // Orquestra: Repository → Mapper → DTO
}

// Repository (Dados)
public interface ProfissionalRepository extends JpaRepository<Especialista, Integer> {
    // Retorna Entidades JPA (nunca DTOs!)
}

// Mapper (Conversão)
@Component
public class ProfissionalMapper {
    // Converte Especialista ↔ DTOs
}

// DTOs (Contrato API)
public record ProfissionalSummaryDTO { ... }
public record ProfissionalDetailDTO { ... }
```

---

**Arquitetura Completa da Camada de DTOs - GISA API REST**
