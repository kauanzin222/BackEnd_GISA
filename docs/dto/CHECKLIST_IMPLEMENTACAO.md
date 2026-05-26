# ✅ CHECKLIST PRÁTICO - Implementação de DTOs

## 📋 Checklist de Desenvolvimento

### 1️⃣ Preparação Inicial

- [ ] Leu [INDICE_DTOs_COMPLETO.md](INDICE_DTOs_COMPLETO.md)
- [ ] Leu [RESUMO_DTOs_RAPIDO.md](RESUMO_DTOs_RAPIDO.md)
- [ ] Viu diagramas em [docs/ARQUITETURA_DTOs_VISUAL.md](docs/ARQUITETURA_DTOs_VISUAL.md)
- [ ] Entendeu diferença: Summary vs Detail vs Cadastro
- [ ] Entendeu como funcionam os Mappers

### 2️⃣ Estrutura do Projeto

- [ ] Pasta `dtos/` criada e contém todos os DTOs
- [ ] Pasta `mappers/` criada e contém todos os Mappers
- [ ] Arquivos `.java` seguem convenção de nomes
- [ ] Sem erros de compilação nos novos arquivos

### 3️⃣ DTOs de Profissionais

- [ ] `ProfissionalSummaryDTO.java` ✅ CRIADO
  - [ ] Tem campos: ID, nome, especialidades, registro, email, status
  - [ ] É Record (imutável)

- [ ] `ProfissionalCadastroDTO.java` ✅ CRIADO
  - [ ] Tem todos os campos do formulário cadastro_profissional.html
  - [ ] Campos opcionais: cnpj, razaoSocial (PJ)
  - [ ] É Record (imutável)

- [ ] `ProfissionalDetailDTO.java` ✅ CRIADO
  - [ ] Tem todos os campos para visualização
  - [ ] Tem flag isPJ
  - [ ] Inclui endereços
  - [ ] É Record (imutável)

### 4️⃣ DTOs de Pessoa/Pacientes

- [ ] `PessoaSummaryDTO.java` ✅ CRIADO
  - [ ] Apenas campos base de Pessoa
  - [ ] Sem dados de subclasses

- [ ] `PessoaDetailDTO.java` ✅ CRIADO
  - [ ] Tem campo `tipo` (detecta PACIENTE, ESPECIALISTA, etc)
  - [ ] Engloba dinamicamente campos de cada tipo

- [ ] `PacienteSummaryDTO.java` ✅ CRIADO
  - [ ] Tem ID, nome, status, tipo entrada

- [ ] `PacienteDetailDTO.java` ✅ CRIADO
  - [ ] Carga completa (pessoa + paciente + CIDs + escola)

### 5️⃣ DTOs Auxiliares

- [ ] `EspecialidadeSummaryDTO.java` ✅ CRIADO
- [ ] `EnderecoDTO.java` ✅ CRIADO
- [ ] `CIDSummaryDTO.java` ✅ CRIADO

### 6️⃣ Mappers

- [ ] `ProfissionalMapper.java` ✅ CRIADO
  - [ ] Método `toSummaryDTO(Especialista)` implementado
  - [ ] Método `toDetailDTO(Especialista)` implementado
  - [ ] Detecta EspecialistaPJ automaticamente
  - [ ] Trata nulos corretamente

- [ ] `ProfissionalDTOMapper.java` ✅ CRIADO
  - [ ] Método `toEntity(CadastroDTO)` implementado
  - [ ] Método `updateEntity(CadastroDTO, Existente)` implementado
  - [ ] Lida com criação de EspecialistaPJ

- [ ] `PessoaMapper.java` ✅ CRIADO
  - [ ] Todos os métodos `toDTO()` implementados
  - [ ] Detecção de tipo em `toDetailDTO(Pessoa)`

### 7️⃣ Controllers

- [ ] Controller recebe DTOs (nunca Entidades)
  ```java
  @PostMapping
  public ResponseEntity<ProfissionalDetailDTO> criar(
      @RequestBody ProfissionalCadastroDTO dto) { ... }
  ```

- [ ] Controller retorna DTOs (nunca Entidades)
  ```java
  @GetMapping("/{id}")
  public ResponseEntity<ProfissionalDetailDTO> buscar(...) { ... }
  ```

- [ ] GET endpoints retornam SummaryDTO para listas
  ```java
  @GetMapping
  public ResponseEntity<Page<ProfissionalSummaryDTO>> listar(...) { ... }
  ```

- [ ] GET endpoints retornam DetailDTO para detalhes
  ```java
  @GetMapping("/{id}")
  public ResponseEntity<ProfissionalDetailDTO> buscar(...) { ... }
  ```

### 8️⃣ Services

- [ ] Service usa `Mapper` para conversão
  ```java
  return mapper.toDetailDTO(especialista);
  ```

- [ ] Service nunca retorna Entidades
  ```java
  // ❌ ERRADO
  return especialista;
  
  // ✅ CORRETO
  return mapper.toDetailDTO(especialista);
  ```

- [ ] Service valida entrada
  ```java
  if (dto.nome() == null) {
      throw new IllegalArgumentException("Nome obrigatório");
  }
  ```

- [ ] Service cria Usuario associado (para POST)
  ```java
  Usuario usuario = new Usuario();
  usuario.setPessoa(especialistaSalva);
  usuario.setSenha(criptografar(dto.senhaProvisoria()));
  usuarioRepository.save(usuario);
  ```

### 9️⃣ Segurança

- [ ] Nenhum DTO expõe `senha`
- [ ] Nenhum DTO expõe `token`
- [ ] Nenhum DTO expõe `@Version`
- [ ] Nenhum DTO expõe IDs internos do banco (exceto idCadastro público)
- [ ] Email NÃO vem de Pessoa (check se está em Usuario ou adicionado em Pessoa)

### 🔟 Documentação

- [ ] DTOs têm Javadoc explicando quando usar
- [ ] Mappers têm Javadoc explicando conversão
- [ ] Controllers têm Javadoc de cada endpoint
- [ ] README.md em pasta `dtos/` está atualizado

---

## 📌 Checklist de Testes

### Testes Unitários

- [ ] Teste Mapper: `Especialista` → `ProfissionalSummaryDTO`
- [ ] Teste Mapper: `Especialista` → `ProfissionalDetailDTO`
- [ ] Teste Mapper: `EspecialistaPJ` → `ProfissionalDetailDTO` (isPJ = true)
- [ ] Teste Mapper: `Paciente` → `PacienteDetailDTO`
- [ ] Teste Mapper: `Pessoa` polimórfica detecta tipo correto
- [ ] Teste DTO Mapper: `ProfissionalCadastroDTO` → `Especialista`

### Testes de Integração

- [ ] GET /api/profissionais (lista com paginação)
  - [ ] Retorna `Page<ProfissionalSummaryDTO>`
  - [ ] Campos corretos
  - [ ] Paginação funciona

- [ ] GET /api/profissionais/{id} (detalhe)
  - [ ] Retorna `ProfissionalDetailDTO`
  - [ ] isPJ = false para Especialista comum
  - [ ] isPJ = true para EspecialistaPJ
  - [ ] Todos os campos preenchidos

- [ ] POST /api/profissionais (criar)
  - [ ] Aceita `ProfissionalCadastroDTO`
  - [ ] Retorna 201 Created
  - [ ] Retorna `ProfissionalDetailDTO` com ID
  - [ ] Usuario criado associado
  - [ ] Senha criptografada

- [ ] PUT /api/profissionais/{id} (atualizar)
  - [ ] Aceita `ProfissionalCadastroDTO`
  - [ ] Retorna 200 OK
  - [ ] Retorna `ProfissionalDetailDTO` atualizado
  - [ ] Dados persistidos no banco

- [ ] GET /api/pessoas/{id} (polimórfico)
  - [ ] Paciente retorna tipo = "PACIENTE"
  - [ ] Especialista retorna tipo = "ESPECIALISTA"
  - [ ] EspecialistaPJ retorna tipo = "ESPECIALISTA_PJ"
  - [ ] Campos específicos preenchidos conforme tipo

### Testes de Resposta

- [ ] Resposta JSON valida schema do DTO
- [ ] Nenhuma senha em resposta
- [ ] Nenhum token em resposta
- [ ] Datas em formato ISO-8601
- [ ] Booleans como `true`/`false` (não strings)
- [ ] Arrays vazios como `[]` (não null)

---

## 🔍 Checklist de Code Review

### Padrão de Código

- [ ] Nomes de variáveis em inglês
- [ ] Nomes de método: `toSummaryDTO`, `toDetailDTO`, etc
- [ ] Métodos privados para helpers
- [ ] Records sem getters/setters
- [ ] Records sem construtores personalizados
- [ ] Imports organizados (sem asteriscos)

### Tratamento de Erros

- [ ] Null checks em Mappers
  ```java
  if (especialista == null) return null;
  ```

- [ ] Try-catch onde necessário
- [ ] Validações em Service
- [ ] Mensagens de erro claras
- [ ] Status HTTP apropriados (201, 404, 400, etc)

### Performance

- [ ] Use `SummaryDTO` para listas grandes
- [ ] Lazy load relacionamentos onde apropriado
- [ ] Paginação implementada
- [ ] `@Transactional(readOnly=true)` para GETs

### Segurança

- [ ] Sem exposição de senhas
- [ ] Sem exposição de tokens
- [ ] Sem dados sensíveis desnecessários
- [ ] Validação de entrada
- [ ] Escape de strings (se aplicável)

---

## 🚀 Checklist de Deployment

### Pré-Produção

- [ ] Todos os testes passando
- [ ] Sem warnings do compilador
- [ ] Code review completado
- [ ] Documentação atualizada
- [ ] Exemplos funcionando

### Produção

- [ ] Senha com BCrypt (não placeholder)
- [ ] Validações habilitadas
- [ ] Logs configurados
- [ ] Monitoramento ativo
- [ ] Backup do banco feito

### Pós-Deploy

- [ ] Endpoints retornando DTOs corretos
- [ ] Performance aceitável
- [ ] Nenhum erro 500
- [ ] Usuários conseguem usar API
- [ ] Documentação Swagger atualizada

---

## 📊 Matriz de Implementação

Preencha conforme implementa:

| Endpoint | DTO (entrada) | DTO (saída) | Status | Teste |
|----------|--------------|-----------|--------|-------|
| GET /profissionais | - | `ProfissionalSummaryDTO` | ⬜ | ⬜ |
| GET /profissionais/{id} | - | `ProfissionalDetailDTO` | ⬜ | ⬜ |
| POST /profissionais | `CadastroDTO` | `DetailDTO` | ⬜ | ⬜ |
| PUT /profissionais/{id} | `CadastroDTO` | `DetailDTO` | ⬜ | ⬜ |
| DELETE /profissionais/{id} | - | - | ⬜ | ⬜ |
| GET /pacientes | - | `PacienteSummaryDTO` | ⬜ | ⬜ |
| GET /pacientes/{id} | - | `PacienteDetailDTO` | ⬜ | ⬜ |
| GET /pessoas/{id} | - | `PessoaDetailDTO` | ⬜ | ⬜ |

---

## 🎯 Marcos de Conclusão

### Fase 1: Setup (Hoje)
- [ ] DTOs criados
- [ ] Mappers criados
- [ ] Sem erros de compilação
- [ ] **Estimado**: 30 min

### Fase 2: Implementação (Próximos 2 dias)
- [ ] Controllers atualizados
- [ ] Services atualizados
- [ ] Testes de integração passando
- [ ] **Estimado**: 2-3 horas por módulo

### Fase 3: Validação (Próximo dia)
- [ ] Todos endpoints testados
- [ ] Resposta conforme esperado
- [ ] Documentação atualizada
- [ ] **Estimado**: 1-2 horas

### Fase 4: Deploy (Próxima semana)
- [ ] Code review completado
- [ ] Deploy em produção
- [ ] Monitoramento ativo
- [ ] **Estimado**: Conforme seu pipeline

---

## 📞 Ajuda Rápida

### "DTOs estão com erro de compilação"
→ Verifique imports e se tem Repositories necessários em Mappers

### "Mapper não está funcionando"
→ Verifique se passou entidade não-nula e se tem `@Component` em Mapper

### "Controller recebe null"
→ Verifique `@RequestBody`, anotações, e se DTO tem Record válido

### "Teste falha"
→ Verifique se dados mock são válidos e se Mapper trata nulos

---

## 📈 Progresso

Você está aqui: **Fase 1 - Setup** ✅ COMPLETO

- [x] DTOs criados (10 arquivos)
- [x] Mappers criados (3 arquivos)
- [x] Exemplos fornecidos
- [x] Documentação completa
- [ ] Controllers implementados ← **PRÓXIMO**
- [ ] Services implementados
- [ ] Testes criados
- [ ] Deploy finalizado

---

**Tempo estimado para completar tudo**: 1-2 semanas (dependendo do tempo disponível)

**Próximo passo**: Implementar GET /api/profissionais conforme exemplo em [RESUMO_DTOs_RAPIDO.md](RESUMO_DTOs_RAPIDO.md#1️⃣-get---listar-profissionais)

**Sucesso!** 🎉
