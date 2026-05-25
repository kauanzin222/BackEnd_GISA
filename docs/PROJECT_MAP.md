# 📍 Mapa de Navegação do Projeto GISA

## 📂 Estrutura de Diretórios

```
gisa/
│
├── 📖 DOCUMENTAÇÃO
│   ├── QUICK_START.md                    ⭐ Comece aqui! (5 min)
│   ├── API_DOCUMENTATION.md              📚 Referência completa de endpoints
│   ├── README_SETUP.md                   🔧 Guia de instalação detalhado
│   ├── EXEMPLOS_REQUISICOES.http         💻 Exemplos de requisições HTTP
│   ├── TROUBLESHOOTING.md                🔍 Resolução de problemas
│   ├── IMPLEMENTATION_SUMMARY.md         📋 Sumário técnico completo
│   ├── VALIDATION_CHECKLIST.md           ✅ Checklist de validação
│   ├── PROJECT_MAP.md                    📍 Este arquivo
│   └── HELP.md                           ℹ️ Ajuda geral (existente)
│
├── 🔧 CONFIGURAÇÃO
│   ├── pom.xml                           Maven: dependências e build
│   ├── mvnw / mvnw.cmd                   Maven wrapper scripts
│   ├── .gitignore / .gitattributes       Git configuration
│   └── .mvn/                             Maven configurações
│
├── 💾 CÓDIGO-FONTE
│   └── src/main/java/com/fatec/gisa/
│       │
│       ├── 🎯 controllers/               (18 classes + package-info.java)
│       │   ├── PacienteController.java
│       │   ├── ProfissionalController.java
│       │   ├── EspecialistaController.java
│       │   ├── EspecialistaPJController.java
│       │   ├── TerapiaController.java
│       │   ├── ProntuarioController.java
│       │   ├── EspecialidadeController.java
│       │   ├── CIDController.java
│       │   ├── CargoController.java
│       │   ├── CBOController.java
│       │   ├── EnderecoController.java
│       │   ├── UsuarioController.java
│       │   ├── PerfilController.java
│       │   ├── PermissaoController.java
│       │   ├── PessoaController.java
│       │   ├── ResponsavelController.java
│       │   ├── EscolaController.java
│       │   ├── ModalidadeController.java
│       │   └── package-info.java         📖 Documentação da camada
│       │
│       ├── ⚙️ services/                  (18 classes + package-info.java)
│       │   ├── PacienteService.java
│       │   ├── ProfissionalService.java
│       │   ├── EspecialistaService.java
│       │   ├── EspecialistaPJService.java
│       │   ├── TerapiaService.java
│       │   ├── ProntuarioService.java
│       │   ├── EspecialidadeService.java
│       │   ├── CIDService.java
│       │   ├── CargoService.java
│       │   ├── CBOService.java
│       │   ├── EnderecoService.java
│       │   ├── UsuarioService.java
│       │   ├── PerfilService.java
│       │   ├── PermissaoService.java
│       │   ├── PessoaService.java
│       │   ├── ResponsavelService.java
│       │   ├── EscolaService.java
│       │   ├── ModalidadeService.java
│       │   └── package-info.java         📖 Documentação da camada
│       │
│       ├── 📊 repositories/              (18 interfaces + package-info.java)
│       │   ├── PacienteRepository.java
│       │   ├── ProfissionalRepository.java
│       │   ├── EspecialistaRepository.java
│       │   ├── EspecialistaPJRepository.java
│       │   ├── TerapiaRepository.java
│       │   ├── ProntuarioRepository.java
│       │   ├── EspecialidadeRepository.java
│       │   ├── CIDRepository.java
│       │   ├── CargoRepository.java
│       │   ├── CBORepository.java
│       │   ├── EnderecoRepository.java
│       │   ├── UsuarioRepository.java
│       │   ├── PerfilRepository.java
│       │   ├── PermissaoRepository.java
│       │   ├── PessoaRepository.java
│       │   ├── ResponsavelRepository.java
│       │   ├── EscolaRepository.java
│       │   ├── ModalidadeRepository.java
│       │   └── package-info.java         📖 Documentação da camada
│       │
│       ├── 📈 models/                    (18 classes + package-info.java)
│       │   ├── Pessoa.java               👤 Superclasse (herança JOINED)
│       │   ├── Paciente.java             👥 Estende Pessoa
│       │   ├── Profissional.java         💼 Estende Pessoa
│       │   ├── Especialista.java         👨‍⚕️ Estende Profissional
│       │   ├── EspecialistaPJ.java       🏢 Estende Especialista
│       │   ├── Responsavel.java          👨‍👩‍👧 Estende Pessoa
│       │   ├── Endereco.java             📍 Relacionamento M-1 com Pessoa
│       │   ├── Usuario.java              🔐 Usuário do sistema
│       │   ├── Perfil.java               📋 Perfil de acesso
│       │   ├── Permissao.java            🔑 Permissão do sistema
│       │   ├── Terapia.java              💊 Terapia/Tratamento
│       │   ├── Prontuario.java           📝 Prontuário médico
│       │   ├── Especialidade.java        🏥 Especialidade médica
│       │   ├── CID.java                  🗂️ Classificação de doença
│       │   ├── Cargo.java                💼 Cargo profissional
│       │   ├── CBO.java                  📊 Classificação ocupacional
│       │   ├── Escola.java               🎓 Instituição escolar
│       │   ├── Modalidade.java           🎯 Modalidade de terapia
│       │   └── package-info.java         📖 Documentação da camada
│       │
│       ├── 📌 enums/                     (7 enumerações + package-info.java)
│       │   ├── EstadoCivil.java          Civil status
│       │   ├── StatusCadastro.java       Registration status
│       │   ├── StatusPaciente.java       Patient status
│       │   ├── StatusTerapia.java        Therapy status
│       │   ├── TipoEntrada.java          Entry type
│       │   ├── TipoEscola.java           School type
│       │   ├── Modalidade.java           Therapy modality
│       │   └── package-info.java         📖 Documentação dos enums
│       │
│       └── GisaApplication.java          🚀 Classe principal Spring Boot
│
├── ⚙️ RECURSOS
│   └── src/main/resources/
│       └── application.properties        🗂️ Configurações Oracle/JPA/Logging
│
├── 🧪 TESTES
│   └── src/test/java/com/fatec/gisa/
│       └── GisaApplicationTests.java     ⏱️ Testes da aplicação
│
└── 📦 BUILD
    ├── target/                           Artefatos compilados
    ├── .vscode/                          Configurações VS Code
    └── .gitignore                        Arquivos ignorados pelo Git
```

---

## 🗂️ Índice de Arquivos por Camada

### 🎯 Camada de Apresentação (Controllers)

| Arquivo | Recurso | Endpoints |
|---------|---------|-----------|
| PacienteController.java | `/api/pacientes` | 6 |
| ProfissionalController.java | `/api/profissionais` | 6 |
| EspecialistaController.java | `/api/especialistas` | 6 |
| EspecialistaPJController.java | `/api/especialistas-pj` | 6 |
| ResponsavelController.java | `/api/responsaveis` | 6 |
| TerapiaController.java | `/api/terapias` | 6 |
| ProntuarioController.java | `/api/prontuarios` | 6 |
| EspecialidadeController.java | `/api/especialidades` | 6 |
| CIDController.java | `/api/cids` | 6 |
| CargoController.java | `/api/cargos` | 6 |
| CBOController.java | `/api/cbos` | 6 |
| EnderecoController.java | `/api/enderecos` | 6 |
| UsuarioController.java | `/api/usuarios` | 6 |
| PerfilController.java | `/api/perfis` | 6 |
| PermissaoController.java | `/api/permissoes` | 6 |
| PessoaController.java | `/api/pessoas` | 6 |
| EscolaController.java | `/api/escolas` | 6 |
| ModalidadeController.java | `/api/modalidades` | 6 |

### ⚙️ Camada de Lógica de Negócio (Services)

Cada Service implementa:
- `criar(Entity)` - POST
- `listarTodos()` - GET lista
- `buscarPorId(Integer)` - GET por ID
- `buscarPor[Campo](valor)` - GET customizado
- `atualizar(Integer, Entity)` - PUT
- `deletar(Integer)` - DELETE

### 📊 Camada de Persistência (Repositories)

Cada Repository estende `JpaRepository<Entity, Integer>` e adiciona query methods customizados conforme necessário.

---

## 📖 Como Usar este Mapa

### 1. Começando do Zero?
→ Leia [QUICK_START.md](QUICK_START.md)

### 2. Quer Detalhes de Endpoints?
→ Consulte [API_DOCUMENTATION.md](API_DOCUMENTATION.md)

### 3. Precisa Configurar o Banco?
→ Siga [README_SETUP.md](README_SETUP.md)

### 4. Quer Exemplos de Requisições?
→ Veja [EXEMPLOS_REQUISICOES.http](EXEMPLOS_REQUISICOES.http)

### 5. Encontrou um Problema?
→ Procure em [TROUBLESHOOTING.md](TROUBLESHOOTING.md)

### 6. Quer Validar Tudo?
→ Use [VALIDATION_CHECKLIST.md](VALIDATION_CHECKLIST.md)

### 7. Quer Resumo Técnico?
→ Leia [IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md)

---

## 🔍 Encontre Rápido

### Por Tipo de Tarefa

| Tarefa | Arquivo |
|--------|---------|
| Testar API | EXEMPLOS_REQUISICOES.http |
| Implementar novo Controller | controllers/[NovaClasse]Controller.java |
| Implementar lógica de negócio | services/[NovaClasse]Service.java |
| Adicionar query customizada | repositories/[NovaClasse]Repository.java |
| Criar nova entidade | models/[NovaClasse].java |
| Configurar propriedades | src/main/resources/application.properties |
| Entender a arquitetura | models/package-info.java |
| Resolver problema | TROUBLESHOOTING.md |

### Por Recurso de Negócio

| Recurso | Models | Services | Controllers | Repositories |
|---------|--------|----------|-------------|--------------|
| Pacientes | Paciente.java | PacienteService.java | PacienteController.java | PacienteRepository.java |
| Profissionais | Profissional.java | ProfissionalService.java | ProfissionalController.java | ProfissionalRepository.java |
| Especialistas | Especialista.java | EspecialistaService.java | EspecialistaController.java | EspecialistaRepository.java |
| Terapias | Terapia.java | TerapiaService.java | TerapiaController.java | TerapiaRepository.java |

---

## 🎯 Fluxo de Requisição HTTP

```
HTTP Request
    ↓
Controller (@RequestMapping("/api/{recurso}"))
    ↓
Service (@Service, lógica de negócio)
    ↓
Repository (extends JpaRepository)
    ↓
Entidade JPA (@Entity)
    ↓
Oracle Database
    ↓
[Response: 200, 201, 204, 400, 404, 500]
```

---

## 💾 Mapeamento de Banco de Dados

### Tabelas Oracle (18 principais)

| Tabela | Entidade | Tipo | ID |
|--------|----------|------|-----|
| PESSOA | Pessoa.java | Base | SEQ_PESSOA |
| PACIENTE | Paciente.java | Herança | SEQ_PACIENTE |
| PROFISSIONAL | Profissional.java | Herança | SEQ_PROFISSIONAL |
| ESPECIALISTA | Especialista.java | Herança | SEQ_ESPECIALISTA |
| ESPECIALISTA_PJ | EspecialistaPJ.java | Herança | SEQ_ESPECIALISTA_PJ |
| RESPONSAVEL | Responsavel.java | Herança | SEQ_RESPONSAVEL |
| ENDERECO | Endereco.java | Relacionamento | SEQ_ENDERECO |
| USUARIO | Usuario.java | Independente | SEQ_USUARIO |
| PERFIL | Perfil.java | Independente | SEQ_PERFIL |
| PERMISSAO | Permissao.java | Independente | SEQ_PERMISSAO |
| TERAPIA | Terapia.java | Independente | SEQ_TERAPIA |
| PRONTUARIO | Prontuario.java | Independente | SEQ_PRONTUARIO |
| ESPECIALIDADE | Especialidade.java | Independente | SEQ_ESPECIALIDADE |
| CID | CID.java | Independente | SEQ_CID |
| CARGO | Cargo.java | Independente | SEQ_CARGO |
| CBO | CBO.java | Independente | SEQ_CBO |
| ESCOLA | Escola.java | Independente | SEQ_ESCOLA |
| MODALIDADE | Modalidade.java | Independente | SEQ_MODALIDADE |

---

## 🚀 Próximos Passos

1. **Desenvolvimento**
   - Adicionar novos Controllers/Services/Repositories
   - Implementar autenticação
   - Adicionar validações avançadas

2. **Testes**
   - Testes unitários (JUnit)
   - Testes de integração
   - Testes de carga

3. **Deployment**
   - Containerizar com Docker
   - Deploy em produção
   - Configurar CI/CD

4. **Monitoramento**
   - Logging centralizado
   - Métricas com Micrometer
   - Health checks

---

## 📞 Comandos Úteis

```bash
# Compilar
mvn clean install

# Executar
mvn spring-boot:run

# Testar
mvn test

# Gerar JAR
mvn package

# Ver dependências
mvn dependency:tree

# Limpar cache
mvn clean

# Verificar cobertura
mvn test jacoco:report
```

---

## 🎓 Aprender Mais

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [JPA/Hibernate Guide](https://www.hibernate.org/orm/)
- [REST API Best Practices](https://restfulapi.net/)
- [Oracle JDBC Driver Docs](https://docs.oracle.com/en/database/oracle/oracle-database/21/jajdb/)

---

## ✅ Checklist de Entendimento

- [ ] Entendi a estrutura em 3 camadas (Controllers → Services → Repositories)
- [ ] Conheço aonde encontrar cada classe
- [ ] Sei como adicionar um novo endpoint
- [ ] Entendo o fluxo de requisição HTTP
- [ ] Sei como testar os endpoints
- [ ] Conheço os arquivos de documentação disponíveis

---

**Última atualização:** 24 de Maio de 2024
**Status:** ✅ Projeto Completo e Documentado
