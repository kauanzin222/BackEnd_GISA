--- =====================================================================
--- 2b.1: TABELAS BASE (Não possuem FKs internas ou dependências)
--- =====================================================================
--- TAB_ENDERECO 
CREATE TABLE tab_endereco (
   idendereco number(10) PRIMARY KEY,
   cep varchar2(9) NOT NULL,
   rua varchar2(100) NOT NULL,
   cidade varchar2(50) NOT NULL,
   bairro varchar2(50) NOT NULL,
   estado varchar2(2) NOT NULL,
   numero varchar2(5),
   complemento varchar2(75)
);

--- TAB_PESSOA
CREATE TABLE tab_pessoa (
   idcadastro number(10) PRIMARY KEY,
   cpf varchar2(11) NOT NULL,
   nome varchar2(100) NOT NULL,
   datanascimento date NOT NULL,
   sexo char(1) NOT NULL,
   celular varchar2(15) NOT NULL,
   estadocivil varchar2(20) NOT NULL,
   numcns varchar2(15) NOT NULL,
   email varchar2(100),
   statuscadastro varchar2(20) NOT NULL
);

--- TAB_PERFIL
CREATE TABLE tab_perfil (
   idperfil number(10) PRIMARY KEY,
   nome varchar2(50) NOT NULL
);

--- TAB_PERMISSAO
CREATE TABLE tab_permissao (
   idpermissao number(10) PRIMARY KEY,
   nome varchar2(50) NOT NULL,
   descricao varchar2(400) NOT NULL
);

--- TAB_ESCOLA
CREATE TABLE tab_escola (
   idescola number(10) PRIMARY KEY,
   nome varchar2(50) NOT NULL,
   tipoescola number(1),
   telefone varchar2(11) NOT NULL
);

--- TAB_CID
CREATE TABLE tab_cid (
   codigocid varchar2(10) PRIMARY KEY,
   descricao varchar2(200) NOT NULL
);

--- TAB_CBO
CREATE TABLE tab_cbo (
   codigocbo number(6) PRIMARY KEY,
   titulocbo varchar2(50) NOT NULL
);

--- TAB_TERAPIA
CREATE TABLE tab_terapia (
   idterapia NUMBER(10) PRIMARY KEY,
   data DATE DEFAULT SYSDATE,
   descricao VARCHAR2(200),
   statusterapia VARCHAR2(20) NOT NULL,
   modalidade VARCHAR2(50)
);

--- TAB_ESPECIALIDADE
CREATE TABLE tab_especialidade (
   idespecialidade NUMBER(10) PRIMARY KEY,
   nome VARCHAR2(50) NOT NULL,
   descricao VARCHAR2(200)
);

--- =====================================================================
--- 2b.2: TABELAS DEPENDENTES (Subclasses, Associativas e Chaves Inline)
--- =====================================================================
--- TAB_CARGO (Depende de tab_cbo)
CREATE TABLE tab_cargo (
   idcargo number(10) PRIMARY KEY,
   codigocbo number(6) NOT NULL,
   nomecargo varchar2(50) NOT NULL,
   CONSTRAINT fk_cargo_cbo FOREIGN KEY (codigocbo) REFERENCES tab_cbo (codigocbo)
);

--- TAB_PROFISSIONAL (Depende de tab_pessoa e tab_cargo)
--- Herança JOINED: IDPROFISSIONAL é PK e FK referenciando IDCADASTRO de TAB_PESSOA
CREATE TABLE tab_profissional (
   idprofissional NUMBER(10) PRIMARY KEY,
   idcargo NUMBER(10),
   CONSTRAINT fk_profissional_pessoa FOREIGN KEY (idprofissional) REFERENCES tab_pessoa (idcadastro),
   CONSTRAINT fk_profissional_cargo FOREIGN KEY (idcargo) REFERENCES tab_cargo (idcargo)
);

--- TAB_ESPECIALISTA (Depende de tab_profissional)
--- Herança JOINED: IDESPECIALISTA é PK e FK referenciando IDPROFISSIONAL de TAB_PROFISSIONAL
CREATE TABLE tab_especialista (
   idespecialista NUMBER(10) PRIMARY KEY,
   registroconselho VARCHAR2(20),
   CONSTRAINT fk_especialista_profissional FOREIGN KEY (idespecialista) REFERENCES tab_profissional (idprofissional)
);

--- TAB_USUARIO (Depende de tab_pessoa)
--- Não é herança, mas tem relação 1:1 via @MapsId: IDCADASTRO é PK e FK para IDCADASTRO de TAB_PESSOA
CREATE TABLE tab_usuario (
   idcadastro number(10) PRIMARY KEY,
   senha varchar2(100) NOT NULL,
   CONSTRAINT fk_usuario_pessoa FOREIGN KEY (idcadastro) REFERENCES tab_pessoa (idcadastro)
);

--- TAB_PACIENTE (Depende de tab_escola e tab_pessoa)
--- Herança JOINED: IDPACIENTE é PK e FK referenciando IDCADASTRO de TAB_PESSOA
CREATE TABLE tab_paciente (
   idpaciente number(10) PRIMARY KEY,
   idescola number(10),
   cidprincipal varchar2(10),
   statuspaciente varchar2(10) NOT NULL,
   datacadastro date DEFAULT sysdate,
   convenio number(1, 0),
   tipoentrada varchar2(20),
   CONSTRAINT fk_paciente_pessoa FOREIGN KEY (idpaciente) REFERENCES tab_pessoa (idcadastro),
   CONSTRAINT fk_paciente_escola FOREIGN KEY (idescola) REFERENCES tab_escola (idescola),
   CONSTRAINT fk_paciente_cid_principal FOREIGN KEY (cidprincipal) REFERENCES tab_cid (codigocid)
);

--- TAB_RESPONSAVEL (Depende de tab_pessoa)
--- Herança JOINED: IDRESPONSAVEL é PK e FK referenciando IDCADASTRO de TAB_PESSOA
CREATE TABLE tab_responsavel (
   idresponsavel number(10) PRIMARY KEY,
   ocupacao varchar2(50),
   CONSTRAINT fk_responsavel_pessoa FOREIGN KEY (idresponsavel) REFERENCES tab_pessoa (idcadastro)
);

--- TAB_PRONTUARIO (Depende de tab_paciente)
--- Relação 1:1 via @MapsId: IDPACIENTE é PK e FK para IDPACIENTE de TAB_PACIENTE
CREATE TABLE tab_prontuario (
   idpaciente number(10) PRIMARY KEY,
   alergias varchar2(100),
   comorbidade varchar2(100),
   mobilidade varchar2(20),
   CONSTRAINT fk_prontuario_paciente FOREIGN KEY (idpaciente) REFERENCES tab_paciente (idpaciente)
);

--- ENDERECO_PESSOA -> TABELA ASSOCIATIVA
CREATE TABLE endereco_pessoa (
   idcadastro number(10) NOT NULL,
   idendereco number(10) NOT NULL,
   CONSTRAINT pk_pessoa_endereco PRIMARY KEY (idendereco, idcadastro),
   CONSTRAINT fk_endereco FOREIGN KEY (idendereco) REFERENCES tab_endereco (idendereco),
   CONSTRAINT fk_pessoa_endereco_pessoa FOREIGN KEY (idcadastro) REFERENCES tab_pessoa (idcadastro)
);

--- USUARIO_PERFIL -> TABELA ASSOCIATIVA
CREATE TABLE usuario_perfil (
   idcadastro number(10) NOT NULL,
   idperfil number(10) NOT NULL,
   CONSTRAINT pk_usuario_perfil PRIMARY KEY (idcadastro, idperfil),
   CONSTRAINT fk_usuario FOREIGN KEY (idcadastro) REFERENCES tab_usuario (idcadastro),
   CONSTRAINT fk_perfil_usuario_perfil FOREIGN KEY (idperfil) REFERENCES tab_perfil (idperfil)
);

--- PERFIL_PERMISSAO -> TABELA ASSOCIATIVA
CREATE TABLE perfil_permissao (
   idperfil number(10) NOT NULL,
   idpermissao number(10) NOT NULL,
   CONSTRAINT pk_perfil_permissao PRIMARY KEY (idperfil, idpermissao),
   CONSTRAINT fk_perfil_perfil_permissao FOREIGN KEY (idperfil) REFERENCES tab_perfil (idperfil),
   CONSTRAINT fk_permissao FOREIGN KEY (idpermissao) REFERENCES tab_permissao (idpermissao)
);

--- PACIENTE_CID -> TABELA ASSOCIATIVA
CREATE TABLE paciente_cid (
   codigocid varchar2(10) NOT NULL,
   idpaciente number(10) NOT NULL,
   CONSTRAINT pk_paciente_cid PRIMARY KEY (codigocid, idpaciente),
   CONSTRAINT fk_cid FOREIGN KEY (codigocid) REFERENCES tab_cid (codigocid),
   CONSTRAINT fk_paciente_paciente_cid FOREIGN KEY (idpaciente) REFERENCES tab_paciente (idpaciente)
);

--- RESPONSAVEL_PACIENTE -> TABELA ASSOCIATIVA
CREATE TABLE responsavel_paciente (
   idresponsavel number(10) NOT NULL,
   idpaciente number(10) NOT NULL,
   grauparentesco varchar2(50),
   CONSTRAINT pk_responsavel_paciente PRIMARY KEY (idresponsavel, idpaciente),
   CONSTRAINT fk_responsavel FOREIGN KEY (idresponsavel) REFERENCES tab_responsavel (idresponsavel),
   CONSTRAINT fk_paciente FOREIGN KEY (idpaciente) REFERENCES tab_paciente (idpaciente)
);

--- PACIENTE_TERAPIA -> TABELA ASSOCIATIVA
CREATE TABLE paciente_terapia (
   idterapia NUMBER(10) NOT NULL,
   idpaciente NUMBER(10) NOT NULL,
   CONSTRAINT pk_paciente_terapia PRIMARY KEY (idterapia, idpaciente),
   CONSTRAINT fk_terapia_paciente FOREIGN KEY (idterapia) REFERENCES tab_terapia (idterapia),
   CONSTRAINT fk_paciente_paciente_terapia FOREIGN KEY (idpaciente) REFERENCES tab_paciente (idpaciente)
);

--- ESPECIALISTA_TERAPIA -> TABELA ASSOCIATIVA
CREATE TABLE especialista_terapia (
   idterapia NUMBER(10) NOT NULL,
   idespecialista NUMBER(10) NOT NULL,
   CONSTRAINT pk_especialista_terapia PRIMARY KEY (idterapia, idespecialista),
   CONSTRAINT fk_terapia_especialista FOREIGN KEY (idterapia) REFERENCES tab_terapia (idterapia),
   CONSTRAINT fk_especialista_especialista_terapia FOREIGN KEY (idespecialista) REFERENCES tab_especialista (idespecialista)
);

--- ESPECIALISTA_PROFISSIONAL (Depende de tab_especialista e tab_especialidade)
--- Tabela de junção para ManyToMany: Especialista <-> Especialidade
CREATE TABLE especialista_profissional (
   idespecialidade NUMBER(10) NOT NULL,
   idespecialista NUMBER(10) NOT NULL,
   CONSTRAINT pk_especialista_profissional PRIMARY KEY (idespecialidade, idespecialista),
   CONSTRAINT fk_especialidade_esp_prof FOREIGN KEY (idespecialidade) REFERENCES tab_especialidade (idespecialidade),
   CONSTRAINT fk_especialista_esp_prof FOREIGN KEY (idespecialista) REFERENCES tab_especialista (idespecialista)
);

CREATE TABLE TAB_ESPECIALISTAPJ (
   -- 1. Chave Primária e Estrangeira unificadas (Herança)
   IDESPECIALISTA NUMBER(10) NOT NULL,
   -- 2. Atributos específicos da Pessoa Jurídica
   CNPJ VARCHAR2(18) NOT NULL,
   RAZAOSOCIAL VARCHAR2(150) NOT NULL,
   NOMEFANTASIA VARCHAR2(150),
   INSCRICAOESTADUAL VARCHAR2(30),
   -- 3. Restrições de Integridade (Constraints)
   CONSTRAINT PK_ESPECIALISTAPJ PRIMARY KEY (IDESPECIALISTA),
   CONSTRAINT FK_ESPE_PJ_TO_ESPECIALISTA FOREIGN KEY (IDESPECIALISTA) REFERENCES TAB_ESPECIALISTA(IDESPECIALISTA),
   CONSTRAINT UK_ESPECIALISTAPJ_CNPJ UNIQUE (CNPJ)
);

--- =====================================================================
--- 2b.3: SEQUENCES PARA AUTO-GERAÇÃO DE IDS
--- =====================================================================
CREATE SEQUENCE seq_pessoa START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_cargo START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_terapia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_escola START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_especialidade START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_perfil START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_permissao START WITH 1 INCREMENT BY 1;