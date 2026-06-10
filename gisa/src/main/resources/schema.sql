-- =====================================================================
-- SCHEMA.SQL - Criação de Tabelas (Executado automaticamente pelo Spring)
-- =====================================================================

-- =====================================================================
-- TABELAS BASE (Sem dependências internas)
-- =====================================================================

CREATE TABLE IF NOT EXISTS tab_endereco (
   idendereco INT PRIMARY KEY,
   cep VARCHAR(9) NOT NULL,
   rua VARCHAR(100) NOT NULL,
   cidade VARCHAR(50) NOT NULL,
   bairro VARCHAR(50) NOT NULL,
   estado VARCHAR(20) NOT NULL,
   numero VARCHAR(5),
   complemento VARCHAR(75)
);

CREATE TABLE IF NOT EXISTS tab_pessoa (
   idcadastro INT PRIMARY KEY,
   cpf VARCHAR(11) NOT NULL,
   nome VARCHAR(100) NOT NULL,
   datanascimento DATE NOT NULL,
   sexo CHAR(1) NOT NULL,
   celular VARCHAR(15) NOT NULL,
   estadocivil VARCHAR(20) NOT NULL,
   numcns VARCHAR(15) NOT NULL,
   email VARCHAR(100),
   statuscadastro VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS tab_perfil (
   idperfil INT PRIMARY KEY,
   nome VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tab_permissao (
   idpermissao INT PRIMARY KEY,
   nome VARCHAR(50) NOT NULL,
   descricao VARCHAR(400) NOT NULL
);

CREATE TABLE IF NOT EXISTS tab_escola (
   idescola INT PRIMARY KEY,
   nome VARCHAR(50) NOT NULL,
   tipoescola INT,
   telefone VARCHAR(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS tab_cid (
   codigocid VARCHAR(10) PRIMARY KEY,
   descricao VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS tab_cbo (
   codigocbo INT PRIMARY KEY,
   titulocbo VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tab_terapia (
   idterapia INT PRIMARY KEY,
   data DATE DEFAULT CURRENT_TIMESTAMP,
   descricao VARCHAR(200),
   statusterapia VARCHAR(20) NOT NULL,
   modalidade VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS tab_especialidade (
   idespecialidade INT PRIMARY KEY,
   nome VARCHAR(50) NOT NULL,
   descricao VARCHAR(200)
);

-- =====================================================================
-- TABELAS DEPENDENTES
-- =====================================================================

CREATE TABLE IF NOT EXISTS tab_cargo (
   idcargo INT PRIMARY KEY,
   codigocbo INT NOT NULL,
   nomecargo VARCHAR(50) NOT NULL,
   CONSTRAINT fk_cargo_cbo FOREIGN KEY (codigocbo) REFERENCES tab_cbo (codigocbo)
);

CREATE TABLE IF NOT EXISTS tab_profissional (
   idprofissional INT PRIMARY KEY,
   idcargo INT,
   CONSTRAINT fk_profissional_pessoa FOREIGN KEY (idprofissional) REFERENCES tab_pessoa (idcadastro),
   CONSTRAINT fk_profissional_cargo FOREIGN KEY (idcargo) REFERENCES tab_cargo (idcargo)
);

CREATE TABLE IF NOT EXISTS tab_especialista (
   idespecialista INT PRIMARY KEY,
   registroconselho VARCHAR(20),
   CONSTRAINT fk_especialista_profissional FOREIGN KEY (idespecialista) REFERENCES tab_profissional (idprofissional)
);

CREATE TABLE IF NOT EXISTS tab_usuario (
   idcadastro INT PRIMARY KEY,
   senha VARCHAR(100) NOT NULL,
   CONSTRAINT fk_usuario_pessoa FOREIGN KEY (idcadastro) REFERENCES tab_pessoa (idcadastro)
);

CREATE TABLE IF NOT EXISTS tab_paciente (
   idpaciente INT PRIMARY KEY,
   idescola INT,
   cidprincipal VARCHAR(10),
   statuspaciente VARCHAR(10) NOT NULL,
   datacadastro DATE DEFAULT CURRENT_TIMESTAMP,
   convenio INT,
   tipoentrada VARCHAR(20),
   CONSTRAINT fk_paciente_pessoa FOREIGN KEY (idpaciente) REFERENCES tab_pessoa (idcadastro),
   CONSTRAINT fk_paciente_escola FOREIGN KEY (idescola) REFERENCES tab_escola (idescola),
   CONSTRAINT fk_paciente_cid_principal FOREIGN KEY (cidprincipal) REFERENCES tab_cid (codigocid)
);

CREATE TABLE IF NOT EXISTS tab_responsavel (
   idresponsavel INT PRIMARY KEY,
   ocupacao VARCHAR(50),
   CONSTRAINT fk_responsavel_pessoa FOREIGN KEY (idresponsavel) REFERENCES tab_pessoa (idcadastro)
);

CREATE TABLE IF NOT EXISTS tab_prontuario (
   idpaciente INT PRIMARY KEY,
   alergias VARCHAR(100),
   comorbidade VARCHAR(100),
   mobilidade VARCHAR(20),
   CONSTRAINT fk_prontuario_paciente FOREIGN KEY (idpaciente) REFERENCES tab_paciente (idpaciente)
);

CREATE TABLE IF NOT EXISTS tab_especialistapj (
   idespecialista INT NOT NULL,
   cnpj VARCHAR(18) NOT NULL,
   razaosocial VARCHAR(150) NOT NULL,
   nomefantasia VARCHAR(150),
   inscricaoestadual VARCHAR(30),
   CONSTRAINT pk_especialistapj PRIMARY KEY (idespecialista),
   CONSTRAINT fk_espe_pj_to_especialista FOREIGN KEY (idespecialista) REFERENCES tab_especialista(idespecialista),
   CONSTRAINT uk_especialistapj_cnpj UNIQUE (cnpj)
);

-- =====================================================================
-- TABELAS ASSOCIATIVAS
-- =====================================================================

CREATE TABLE IF NOT EXISTS endereco_pessoa (
   idcadastro INT NOT NULL,
   idendereco INT NOT NULL,
   CONSTRAINT pk_pessoa_endereco PRIMARY KEY (idendereco, idcadastro),
   CONSTRAINT fk_endereco FOREIGN KEY (idendereco) REFERENCES tab_endereco (idendereco),
   CONSTRAINT fk_pessoa_endereco_pessoa FOREIGN KEY (idcadastro) REFERENCES tab_pessoa (idcadastro)
);

CREATE TABLE IF NOT EXISTS usuario_perfil (
   idcadastro INT NOT NULL,
   idperfil INT NOT NULL,
   CONSTRAINT pk_usuario_perfil PRIMARY KEY (idcadastro, idperfil),
   CONSTRAINT fk_usuario FOREIGN KEY (idcadastro) REFERENCES tab_usuario (idcadastro),
   CONSTRAINT fk_perfil_usuario_perfil FOREIGN KEY (idperfil) REFERENCES tab_perfil (idperfil)
);

CREATE TABLE IF NOT EXISTS perfil_permissao (
   idperfil INT NOT NULL,
   idpermissao INT NOT NULL,
   CONSTRAINT pk_perfil_permissao PRIMARY KEY (idperfil, idpermissao),
   CONSTRAINT fk_perfil_perfil_permissao FOREIGN KEY (idperfil) REFERENCES tab_perfil (idperfil),
   CONSTRAINT fk_permissao FOREIGN KEY (idpermissao) REFERENCES tab_permissao (idpermissao)
);

CREATE TABLE IF NOT EXISTS paciente_cid (
   codigocid VARCHAR(10) NOT NULL,
   idpaciente INT NOT NULL,
   CONSTRAINT pk_paciente_cid PRIMARY KEY (codigocid, idpaciente),
   CONSTRAINT fk_cid FOREIGN KEY (codigocid) REFERENCES tab_cid (codigocid),
   CONSTRAINT fk_paciente_paciente_cid FOREIGN KEY (idpaciente) REFERENCES tab_paciente (idpaciente)
);

CREATE TABLE IF NOT EXISTS responsavel_paciente (
   idresponsavel INT NOT NULL,
   idpaciente INT NOT NULL,
   grauparentesco VARCHAR(50),
   CONSTRAINT pk_responsavel_paciente PRIMARY KEY (idresponsavel, idpaciente),
   CONSTRAINT fk_responsavel FOREIGN KEY (idresponsavel) REFERENCES tab_responsavel (idresponsavel),
   CONSTRAINT fk_paciente FOREIGN KEY (idpaciente) REFERENCES tab_paciente (idpaciente)
);

CREATE TABLE IF NOT EXISTS paciente_terapia (
   idterapia INT NOT NULL,
   idpaciente INT NOT NULL,
   CONSTRAINT pk_paciente_terapia PRIMARY KEY (idterapia, idpaciente),
   CONSTRAINT fk_terapia_paciente FOREIGN KEY (idterapia) REFERENCES tab_terapia (idterapia),
   CONSTRAINT fk_paciente_paciente_terapia FOREIGN KEY (idpaciente) REFERENCES tab_paciente (idpaciente)
);

CREATE TABLE IF NOT EXISTS especialista_terapia (
   idterapia INT NOT NULL,
   idespecialista INT NOT NULL,
   CONSTRAINT pk_especialista_terapia PRIMARY KEY (idterapia, idespecialista),
   CONSTRAINT fk_terapia_especialista FOREIGN KEY (idterapia) REFERENCES tab_terapia (idterapia),
   CONSTRAINT fk_especialista_especialista_terapia FOREIGN KEY (idespecialista) REFERENCES tab_especialista (idespecialista)
);

CREATE TABLE IF NOT EXISTS especialista_profissional (
   idespecialidade INT NOT NULL,
   idespecialista INT NOT NULL,
   CONSTRAINT pk_especialista_profissional PRIMARY KEY (idespecialidade, idespecialista),
   CONSTRAINT fk_especialidade_esp_prof FOREIGN KEY (idespecialidade) REFERENCES tab_especialidade (idespecialidade),
   CONSTRAINT fk_especialista_esp_prof FOREIGN KEY (idespecialista) REFERENCES tab_especialista (idespecialista)
);

CREATE SEQUENCE seq_pessoa START WITH 10 INCREMENT BY 1;