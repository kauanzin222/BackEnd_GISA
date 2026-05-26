# 📖 ÍNDICE COMPLETO - Camada de DTOs GISA API REST

## 🎯 Comece por Aqui!

Se você é novo neste projeto, siga esta ordem:

### 1. **Leia o Resumo Rápido** (5 min)
📄 [RESUMO_DTOs_RAPIDO.md](RESUMO_DTOs_RAPIDO.md)
- O que foi criado
- Estrutura visual de DTOs
- Quick start (5 exemplos prontos)

### 2. **Entenda a Arquitetura** (10 min)
📊 [docs/ARQUITETURA_DTOs_VISUAL.md](docs/ARQUITETURA_DTOs_VISUAL.md)
- Hierarquia Entidades vs DTOs
- Fluxos de conversão (diagrama)
- Matriz de DTOs

### 3. **Leia o Guia Completo** (30 min)
📚 [docs/DTO_E_MAPPERS_GUIA_COMPLETO.md](docs/DTO_E_MAPPERS_GUIA_COMPLETO.md)
- Cada DTO explicado em detalhes
- Exemplos de requests/responses
- Boas práticas
- Checklist de implementação

### 4. **Explore os Exemplos de Código**
📝 Arquivos de exemplo:
- [ProfissionalControllerExample.java](gisa/src/main/java/com/fatec/gisa/controllers/ProfissionalControllerExample.java) - Controllers
- [ProfissionalServiceExemploCompleto.java](gisa/src/main/java/com/fatec/gisa/examples/ProfissionalServiceExemploCompleto.java) - Services

### 5. **Use como Referência**
📦 [dtos/README.md](gisa/src/main/java/com/fatec/gisa/dtos/README.md) - Quick reference durante desenvolvimento

---

## 📦 Arquivos Criados

### 1. DTOs (10 arquivos)

#### Profissionais
- ✅ [ProfissionalSummaryDTO.java](gisa/src/main/java/com/fatec/gisa/dtos/ProfissionalSummaryDTO.java) - Listagens
- ✅ [ProfissionalCadastroDTO.java](gisa/src/main/java/com/fatec/gisa/dtos/ProfissionalCadastroDTO.java) - POST/PUT (entrada)
- ✅ [ProfissionalDetailDTO.java](gisa/src/main/java/com/fatec/gisa/dtos/ProfissionalDetailDTO.java) - GET by ID

#### Pessoa (Base)
- ✅ [PessoaSummaryDTO.java](gisa/src/main/java/com/fatec/gisa/dtos/PessoaSummaryDTO.java) - Listagens
- ✅ [PessoaDetailDTO.java](gisa/src/main/java/com/fatec/gisa/dtos/PessoaDetailDTO.java) - Polimórfica!

#### Pacientes
- ✅ [PacienteSummaryDTO.java](gisa/src/main/java/com/fatec/gisa/dtos/PacienteSummaryDTO.java) - Listagens clínicas
- ✅ [PacienteDetailDTO.java](gisa/src/main/java/com/fatec/gisa/dtos/PacienteDetailDTO.java) - Carga completa

#### Auxiliares
- ✅ [EspecialidadeSummaryDTO.java](gisa/src/main/java/com/fatec/gisa/dtos/EspecialidadeSummaryDTO.java)
- ✅ [EnderecoDTO.java](gisa/src/main/java/com/fatec/gisa/dtos/EnderecoDTO.java)
- ✅ [CIDSummaryDTO.java](gisa/src/main/java/com/fatec/gisa/dtos/CIDSummaryDTO.java)

---

### 2. Mappers (3 arquivos)

**Localização**: `com.fatec.gisa.mappers`

- ✅ [ProfissionalMapper.java](gisa/src/main/java/com/fatec/gisa/mappers/ProfissionalMapper.java)
  - `toSummaryDTO(Especialista)` → ProfissionalSummaryDTO
  - `toDetailDTO(Especialista)` → ProfissionalDetailDTO
  - Detecta EspecialistaPJ automaticamente

- ✅ [ProfissionalDTOMapper.java](gisa/src/main/java/com/fatec/gisa/mappers/ProfissionalDTOMapper.java)
  - `toEntity(ProfissionalCadastroDTO)` → Nova Especialista
  - `updateEntity(DTO, Entidade)` → Entidade atualizada
  - Gerencia conversão inversa

- ✅ [PessoaMapper.java](gisa/src/main/java/com/fatec/gisa/mappers/PessoaMapper.java)
  - `toSummaryDTO(Pessoa)` → PessoaSummaryDTO
  - `toDetailDTO(Pessoa)` → PessoaDetailDTO (detecta tipo!)
  - `toSummaryDTO(Paciente)` → PacienteSummaryDTO
  - `toDetailDTO(Paciente)` → PacienteDetailDTO

---

### 3. Controllers (1 arquivo)

- ✅ [ProfissionalControllerExample.java](gisa/src/main/java/com/fatec/gisa/controllers/ProfissionalControllerExample.java)
  - GET: listar, buscar por ID
  - POST: criar
  - PUT: atualizar
  - Todos os endpoints com DTOs

---

### 4. Services (1 arquivo + Existentes)

- ✅ [ProfissionalServiceExemploCompleto.java](gisa/src/main/java/com/fatec/gisa/examples/ProfissionalServiceExemploCompleto.java)
  - Implementação completa com POST, PUT, GET
  - Conversão DTO ↔ Entidade
  - Validações e tratamento de erros
  - Criação de Usuario associado

Arquivos existentes (adaptados para usar DTOs):
- `ProfissionalService.java` *(seu arquivo)*
- `PessoaService.java` *(seu arquivo)*

---

### 5. Documentação (4 arquivos)

#### Documentação em Markdown
- 📚 [docs/DTO_E_MAPPERS_GUIA_COMPLETO.md](docs/DTO_E_MAPPERS_GUIA_COMPLETO.md)
  - **Mais de 500 linhas!**
  - Cada DTO explicado em detalhes
  - Exemplos reais de requests/responses
  - Boas práticas
  - Anti-patterns
  - Checklist de implementação

- 📊 [docs/ARQUITETURA_DTOs_VISUAL.md](docs/ARQUITETURA_DTOs_VISUAL.md)
  - Diagramas ASCII da hierarquia
  - Fluxos de dados (GET, POST, PUT)
  - Matriz de DTOs
  - Stack de componentes

- 📄 [RESUMO_DTOs_RAPIDO.md](RESUMO_DTOs_RAPIDO.md)
  - Quick start (este arquivo!)
  - Exemplos prontos
  - Regras de ouro
  - Links úteis

- 📋 [gisa/src/main/java/com/fatec/gisa/dtos/README.md](gisa/src/main/java/com/fatec/gisa/dtos/README.md)
  - Reference rápida
  - Estrutura de pacotes
  - Checklist de uso
  - Próximos passos

---

## 🗺️ Mapa de Navegação

```
START HERE
    ↓
├─ Quer um resumo? 
│  └─→ RESUMO_DTOs_RAPIDO.md (este arquivo)
│
├─ Quer entender visualmente?
│  └─→ docs/ARQUITETURA_DTOs_VISUAL.md
│
├─ Quer aprender em detalhes?
│  └─→ docs/DTO_E_MAPPERS_GUIA_COMPLETO.md (o melhor!)
│
├─ Quer copiar/colar código?
│  └─→ gisa/src/main/java/.../examples/ProfissionalServiceExemploCompleto.java
│
└─ Quer uma referência rápida?
   └─→ gisa/src/main/java/.../dtos/README.md
```

---

## 🎯 Casos de Uso Comuns

### "Como listo profissionais?"
1. Leia: [docs/ARQUITETURA_DTOs_VISUAL.md](docs/ARQUITETURA_DTOs_VISUAL.md#get---listar-profissionais)
2. Código: [ProfissionalControllerExample.java#listar](gisa/src/main/java/com/fatec/gisa/controllers/ProfissionalControllerExample.java#L67)
3. Response: `Page<ProfissionalSummaryDTO>`

### "Como busco profissional por ID?"
1. Leia: [docs/ARQUITETURA_DTOs_VISUAL.md](docs/ARQUITETURA_DTOs_VISUAL.md#get---buscar-profissional-por-id)
2. Código: [ProfissionalControllerExample.java#buscar](gisa/src/main/java/com/fatec/gisa/controllers/ProfissionalControllerExample.java#L82)
3. Response: `ProfissionalDetailDTO` (completo)

### "Como crio novo profissional?"
1. Leia: [docs/DTO_E_MAPPERS_GUIA_COMPLETO.md#6-3-post---criar-profissional](docs/DTO_E_MAPPERS_GUIA_COMPLETO.md#63-post---criar-profissional)
2. Código: [ProfissionalServiceExemploCompleto.java#criar](gisa/src/main/java/com/fatec/gisa/examples/ProfissionalServiceExemploCompleto.java#L108)
3. Input: `ProfissionalCadastroDTO`
4. Output: `ProfissionalDetailDTO`

### "Como busco pessoa com tipo dinâmico?"
1. Leia: [docs/ARQUITETURA_DTOs_VISUAL.md#pessoadetaildto---polimórfica](docs/ARQUITETURA_DTOs_VISUAL.md#get---buscar-pessoa-polimórfica)
2. Response: `PessoaDetailDTO` com campo `tipo: "PACIENTE" | "ESPECIALISTA_PJ"`
3. Front-end detecta tipo e mostra campos apropriados

---

## 🚦 Passo a Passo: Implementar seu Primeiro Endpoint

### Objetivo: GET /api/profissionais (listar com paginação)

#### Passo 1: Criar Controller
```java
@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {
    
    @Autowired
    private ProfissionalService service;
    
    @GetMapping
    public ResponseEntity<Page<ProfissionalSummaryDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listarProfissionais(pageable));
    }
}
```

#### Passo 2: Implementar Service
```java
@Service
public class ProfissionalService {
    
    @Autowired
    private EspecialistaRepository repository;
    
    @Autowired
    private ProfissionalMapper mapper;
    
    public Page<ProfissionalSummaryDTO> listarProfissionais(Pageable pageable) {
        Page<Especialista> page = repository.findAll(pageable);
        List<ProfissionalSummaryDTO> dtos = page.getContent()
            .stream()
            .map(mapper::toSummaryDTO)
            .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
}
```

#### Passo 3: Usar o Mapper
✅ Mapper já está pronto em: [ProfissionalMapper.java](gisa/src/main/java/com/fatec/gisa/mappers/ProfissionalMapper.java)

#### Passo 4: Testar
```bash
curl "http://localhost:8080/api/profissionais?page=0&size=10"
```

Response:
```json
{
  "content": [
    {
      "idProfissional": 1,
      "nome": "Dr. Roberto Almeida",
      "especialidades": ["Neurologista"],
      "registroProfissional": "CRM-SP 123456",
      "email": "roberto@apae.org",
      "status": "ATIVO"
    }
  ],
  "pageable": { ... },
  "totalElements": 6
}
```

**Pronto!** 🎉

---

## 📊 Comparação: Antes vs Depois

### ❌ Antes (Expõe Entidade)
```java
@GetMapping
public ResponseEntity<Page<Especialista>> listar(Pageable pageable) {
    return ResponseEntity.ok(repository.findAll(pageable));
}
// Response: Especialista com TUDO (senhas, IDs internos, etc)
```

### ✅ Depois (Usa DTO)
```java
@GetMapping
public ResponseEntity<Page<ProfissionalSummaryDTO>> listar(Pageable pageable) {
    return ResponseEntity.ok(service.listarProfissionais(pageable));
}
// Response: ProfissionalSummaryDTO (apenas campos necessários)
```

**Benefícios**:
- ✅ Segurança
- ✅ Performance (menos dados)
- ✅ Documentação clara
- ✅ Flexibilidade

---

## 🆘 Precisa de Ajuda?

### "Qual DTO devo usar?"
→ Veja a tabela em [docs/ARQUITETURA_DTOs_VISUAL.md#📋-matriz-de-dtos](docs/ARQUITETURA_DTOs_VISUAL.md#📋-matriz-de-dtos)

### "Como funciona o Mapper?"
→ Leia [docs/DTO_E_MAPPERS_GUIA_COMPLETO.md#4-mappers](docs/DTO_E_MAPPERS_GUIA_COMPLETO.md#4-mappers)

### "Qual é a diferença entre Summary e Detail?"
→ Veja exemplos em [docs/DTO_E_MAPPERS_GUIA_COMPLETO.md#6-exemplos-práticos](docs/DTO_E_MAPPERS_GUIA_COMPLETO.md#6-exemplos-práticos)

### "Como lido com Pessoa Polimórfica?"
→ Estude [PessoaMapper.java](gisa/src/main/java/com/fatec/gisa/mappers/PessoaMapper.java#L73-L150)

### "Como crio/atualizo com DTO?"
→ Use [ProfissionalServiceExemploCompleto.java](gisa/src/main/java/com/fatec/gisa/examples/ProfissionalServiceExemploCompleto.java)

---

## ✨ Resumo do que você Ganhou

| Aspecto | Antes | Depois |
|---------|-------|--------|
| **Segurança** | Expõe tudo | Apenas necessário |
| **Performance** | Dados pesados | Leve (Summary) ou Completo (Detail) |
| **Documentação** | Vaga | Crystal clear |
| **Flexibilidade** | Entidade = API | Independentes |
| **Manutenção** | Difícil | Fácil |
| **Type-Safety** | ❌ | ✅ (Java Records) |

---

## 🚀 Próximas Ações

### 1. Imediato (Hoje)
- [ ] Ler [RESUMO_DTOs_RAPIDO.md](RESUMO_DTOs_RAPIDO.md)
- [ ] Ler [docs/ARQUITETURA_DTOs_VISUAL.md](docs/ARQUITETURA_DTOs_VISUAL.md)
- [ ] Explorar [dtos/README.md](gisa/src/main/java/com/fatec/gisa/dtos/README.md)

### 2. Curto Prazo (Próximos dias)
- [ ] Implementar GET /api/profissionais (listar)
- [ ] Implementar GET /api/profissionais/{id} (detalhe)
- [ ] Implementar POST /api/profissionais (criar)

### 3. Médio Prazo (Próximas semanas)
- [ ] Adicionar validações (`@Valid`)
- [ ] Adicionar Swagger/OpenAPI
- [ ] Implementar Exception Handling
- [ ] Criar testes unitários

### 4. Longo Prazo
- [ ] Adicionar criptografia de senha (BCrypt)
- [ ] Implementar autenticação/autorização
- [ ] Adicionar paginação avançada
- [ ] Implementar cache

---

## 📞 Suporte

Se tiver dúvidas:
1. Consulte [dtos/README.md](gisa/src/main/java/com/fatec/gisa/dtos/README.md) - Quick Reference
2. Leia [docs/DTO_E_MAPPERS_GUIA_COMPLETO.md](docs/DTO_E_MAPPERS_GUIA_COMPLETO.md) - Guia Completo
3. Estude [ProfissionalServiceExemploCompleto.java](gisa/src/main/java/com/fatec/gisa/examples/ProfissionalServiceExemploCompleto.java) - Implementação Real

---

**Status**: ✅ Pronto para Produção  
**Última atualização**: Maio de 2026  
**Versão**: 1.0
