--- =====================================================================
--- BLOCO 1: INSERTS NAS TABELAS BASE 
--- =====================================================================

--- TAB_ENDERECO (IDs 1 a 5)
INSERT INTO tab_endereco (idendereco, cep, rua, cidade, bairro, estado, numero, complemento) VALUES (1, '18035-000', 'Avenida General Carneiro', 'Sorocaba', 'Cerrado', 'SP', 120, 'Apto 32');
INSERT INTO tab_endereco (idendereco, cep, rua, cidade, bairro, estado, numero, complemento) VALUES (2, '18043-000', 'Rua Afonso Vergueiro', 'Sorocaba', 'Centro', 'SP', 1500, NULL);
INSERT INTO tab_endereco (idendereco, cep, rua, cidade, bairro, estado, numero, complemento) VALUES (3, '18050-000', 'Rua Américo Figueiredo', 'Sorocaba', 'Júlio de Mesquita', 'SP', 45, 'Casa B');
INSERT INTO tab_endereco (idendereco, cep, rua, cidade, bairro, estado, numero, complemento) VALUES (4, '18010-000', 'Rua Quinze de Novembro', 'Sorocaba', 'Centro', 'SP', 300, 'Sala 4');
INSERT INTO tab_endereco (idendereco, cep, rua, cidade, bairro, estado, numero, complemento) VALUES (5, '18020-000', 'Rua Itavuvu', 'Sorocaba', 'Zona Norte', 'SP', 850, NULL);

--- TAB_PESSOA 
INSERT INTO tab_pessoa (idcadastro, cpf, nome, datanascimento, sexo, celular, estadocivil, numcns, statuscadastro) VALUES (1, '11122233344', 'Carlos Alberto Silva', TO_DATE('1985-03-15', 'YYYY-MM-DD'), 'M', '15991112222', 'CASADO', '123456789012345', 'ATIVO');
INSERT INTO tab_pessoa (idcadastro, cpf, nome, datanascimento, sexo, celular, estadocivil, numcns, statuscadastro) VALUES (2, '22233344455', 'Ana Beatriz Rocha', TO_DATE('1990-07-22', 'YYYY-MM-DD'), 'F', '15992223333', 'SOLTEIRO', '234567890123456', 'ATIVO');
INSERT INTO tab_pessoa (idcadastro, cpf, nome, datanascimento, sexo, celular, estadocivil, numcns, statuscadastro) VALUES (3, '33344455566', 'Ricardo Souza Melo', TO_DATE('1978-11-05', 'YYYY-MM-DD'), 'M', '15993334444', 'DIVORCIADO', '345678901234567', 'ATIVO');
INSERT INTO tab_pessoa (idcadastro, cpf, nome, datanascimento, sexo, celular, estadocivil, numcns, statuscadastro) VALUES (4, '44455566677', 'Juliana Lima Costa', TO_DATE('1988-05-30', 'YYYY-MM-DD'), 'F', '15994445555', 'CASADO', '456789012345688', 'ATIVO');
INSERT INTO tab_pessoa (idcadastro, cpf, nome, datanascimento, sexo, celular, estadocivil, numcns, statuscadastro) VALUES (5, '55566677788', 'Marcos Paulo Vieira', TO_DATE('1982-01-25', 'YYYY-MM-DD'), 'M', '15995556666', 'SOLTEIRO', '567890123456789', 'ATIVO');
INSERT INTO tab_pessoa (idcadastro, cpf, nome, datanascimento, sexo, celular, estadocivil, numcns, statuscadastro) VALUES (6, '66677788899', 'Kauã Lucas Ferreira', TO_DATE('2018-04-12', 'YYYY-MM-DD'), 'M', '15996667777', 'SOLTEIRO', '678901234567890', 'ATIVO');
INSERT INTO tab_pessoa (idcadastro, cpf, nome, datanascimento, sexo, celular, estadocivil, numcns, statuscadastro) VALUES (7, '77788899900', 'Mariana Dias Gomes', TO_DATE('2019-09-18', 'YYYY-MM-DD'), 'F', '15997778888', 'SOLTEIRO', '789012345678901', 'ATIVO');
INSERT INTO tab_pessoa (idcadastro, cpf, nome, datanascimento, sexo, celular, estadocivil, numcns, statuscadastro) VALUES (8, '88899900011', 'Pedro Henrique Antunes', TO_DATE('2017-01-30', 'YYYY-MM-DD'), 'M', '15998889999', 'SOLTEIRO', '890123456789012', 'ATIVO');
INSERT INTO tab_pessoa (idcadastro, cpf, nome, datanascimento, sexo, celular, estadocivil, numcns, statuscadastro) VALUES (9, '99900011122', 'Sofia Rodrigues Lima', TO_DATE('2016-06-14', 'YYYY-MM-DD'), 'F', '15999990000', 'SOLTEIRO', '901234567890123', 'ATIVO');
INSERT INTO tab_pessoa (idcadastro, cpf, nome, datanascimento, sexo, celular, estadocivil, numcns, statuscadastro) VALUES (10, '12345678901', 'Enzo Gabriel Santos', TO_DATE('2020-11-23', 'YYYY-MM-DD'), 'M', '15991110000', 'SOLTEIRO', '123012345678901', 'ATIVO');

--- TAB_PERFIL
INSERT INTO tab_perfil (idperfil, nome) VALUES (1, 'Administrador');
INSERT INTO tab_perfil (idperfil, nome) VALUES (2, 'Secretaria');
INSERT INTO tab_perfil (idperfil, nome) VALUES (3, 'Faturamento');
INSERT INTO tab_perfil (idperfil, nome) VALUES (4, 'Terapeuta Clínico');
INSERT INTO tab_perfil (idperfil, nome) VALUES (5, 'Médico Especialista');

--- TAB_PERMISSAO
INSERT INTO tab_permissao (idpermissao, nome, descricao) VALUES (1, 'ACESSAR_SISTEMA', 'Permite o login no sistema clinico');
INSERT INTO tab_permissao (idpermissao, nome, descricao) VALUES (2, 'CRIAR_PACIENTE', 'Permite cadastrar um novo paciente no sistema');
INSERT INTO tab_permissao (idpermissao, nome, descricao) VALUES (3, 'REGISTRAR_EVOLUCAO', 'Permite inserir dados na evolucao do prontuario');
INSERT INTO tab_permissao (idpermissao, nome, descricao) VALUES (4, 'AGENDAR_TERAPIA', 'Permite criar novas sessoes de terapia na agenda');
INSERT INTO tab_permissao (idpermissao, nome, descricao) VALUES (5, 'CONTROLE_TOTAL', 'Acesso irrestrito a todas as configuracoes administrativas');

--- TAB_ESCOLA
INSERT INTO tab_escola (idescola, nome, tipoescola, telefone) VALUES (1, 'Escola Estadual Achilles de Almeida', 1, '1532211010');
INSERT INTO tab_escola (idescola, nome, tipoescola, telefone) VALUES (2, 'Colégio Objetivo Sorocaba', 2, '1532334040');
INSERT INTO tab_escola (idescola, nome, tipoescola, telefone) VALUES (3, 'EMEF Dr. Getúlio Vargas', 1, '1532251515');
INSERT INTO tab_escola (idescola, nome, tipoescola, telefone) VALUES (4, 'Escola Municipal Baltazar Fernandes', 1, '1532271818');
INSERT INTO tab_escola (idescola, nome, tipoescola, telefone) VALUES (5, 'Colégio Dom Aguirre', 2, '1532129000');

--- TAB_CID
INSERT INTO tab_cid (codigocid, descricao) VALUES ('F84.0', 'Autismo Infantil');
INSERT INTO tab_cid (codigocid, descricao) VALUES ('F90.0', 'Distúrbios da Atividade e da Atenção (TDAH)');
INSERT INTO tab_cid (codigocid, descricao) VALUES ('F80.0', 'Transtorno Específico da Articulação da Fala');
INSERT INTO tab_cid (codigocid, descricao) VALUES ('F81.0', 'Transtorno Específico de Leitura (Dislexia)');
INSERT INTO tab_cid (codigocid, descricao) VALUES ('G80.0', 'Paralisia Cerebral Espástica');

--- TAB_CBO
INSERT INTO tab_cbo (codigocbo, titulocbo) VALUES (251510, 'Psicólogo Clínico');
INSERT INTO tab_cbo (codigocbo, titulocbo) VALUES (223905, 'Terapeuta Ocupacional');
INSERT INTO tab_cbo (codigocbo, titulocbo) VALUES (121010, 'Diretor Geral');
INSERT INTO tab_cbo (codigocbo, titulocbo) VALUES (411010, 'Assistente Administrativo');
INSERT INTO tab_cbo (codigocbo, titulocbo) VALUES (223810, 'Fonoaudiólogo');

--- TAB_TERAPIA
INSERT INTO tab_terapia (idterapia, data, descricao, statusterapia, modalidade) VALUES (1, SYSDATE, 'Sessão de Integração Sensorial', 'AGENDADA', 'INDIVIDUAL');
INSERT INTO tab_terapia (idterapia, data, descricao, statusterapia, modalidade) VALUES (2, SYSDATE + 1, 'Análise do Comportamento Aplicada (ABA)', 'AGENDADA', 'INDIVIDUAL');
INSERT INTO tab_terapia (idterapia, data, descricao, statusterapia, modalidade) VALUES (3, SYSDATE + 2, 'Terapia de Fala e Linguagem', 'CONCLUIDA', 'INDIVIDUAL');
INSERT INTO tab_terapia (idterapia, data, descricao, statusterapia, modalidade) VALUES (4, SYSDATE + 3, 'Terapia Fonoaudiológica em Grupo', 'AGENDADA', 'COLETIVA');
INSERT INTO tab_terapia (idterapia, data, descricao, statusterapia, modalidade) VALUES (5, SYSDATE + 4, 'Avaliação Psicológica Coletiva', 'AGENDADA', 'COLETIVA');

--- TAB_ESPECIALIDADE (A nova tabela do Modelo!)
INSERT INTO tab_especialidade (idespecialidade, nome, descricao) VALUES (1, 'Neuropediatria', 'Foco no desenvolvimento neurologico infantil');
INSERT INTO tab_especialidade (idespecialidade, nome, descricao) VALUES (2, 'Integração Sensorial', 'Tratamento de disfuncoes de processamento sensorial');
INSERT INTO tab_especialidade (idespecialidade, nome, descricao) VALUES (3, 'Análise do Comportamento (ABA)', 'Intervencao comportamental para TEA');
INSERT INTO tab_especialidade (idespecialidade, nome, descricao) VALUES (4, 'Linguagem e Fala', 'Reabilitacao de disturbios fonoaudiologicos');
INSERT INTO tab_especialidade (idespecialidade, nome, descricao) VALUES (5, 'Psicopedagogia Institucional', 'Dificuldades de aprendizagem escolar');


--- =====================================================================
--- BLOCO 2: INSERTS NAS TABELAS DEPENDENTES E ASSOCIATIVAS
--- =====================================================================

--- TAB_CARGO
INSERT INTO tab_cargo (idcargo, codigocbo, nomecargo) VALUES (1, 121010, 'Gerente Clínico');
INSERT INTO tab_cargo (idcargo, codigocbo, nomecargo) VALUES (2, 411010, 'Recepcionista Pleno');
INSERT INTO tab_cargo (idcargo, codigocbo, nomecargo) VALUES (3, 411010, 'Analista de Faturamento');
INSERT INTO tab_cargo (idcargo, codigocbo, nomecargo) VALUES (4, 251510, 'Psicólogo Terapeuta');
INSERT INTO tab_cargo (idcargo, codigocbo, nomecargo) VALUES (5, 223905, 'Terapeuta Ocupacional');

--- TAB_PROFISSIONAL (Vincula as Pessoas de 1 a 5 aos seus cargos de contratação)
INSERT INTO tab_profissional (idprofissional, idcargo) VALUES (1, 1); -- Carlos (Gerente)
INSERT INTO tab_profissional (idprofissional, idcargo) VALUES (2, 2); -- Ana (Recepcionista)
INSERT INTO tab_profissional (idprofissional, idcargo) VALUES (3, 3); -- Ricardo (Faturamento)
INSERT INTO tab_profissional (idprofissional, idcargo) VALUES (4, 4); -- Juliana (Psicóloga)
INSERT INTO tab_profissional (idprofissional, idcargo) VALUES (5, 5); -- Marcos (T.O.)

--- TAB_ESPECIALISTA (Apenas os IDs 4 e 5 entram aqui, pois realizam atendimento!)
INSERT INTO tab_especialista (idespecialista, registroconselho) VALUES (4, 'CRP-06/12345');
INSERT INTO tab_especialista (idespecialista, registroconselho) VALUES (5, 'CREFITO-3/6789');

--- especialista_profissional (A associativa que mapeia as especialidades técnicas dos especialistas)
INSERT INTO especialista_profissional (idespecialidade, idespecialista) VALUES (3, 4); -- Juliana tem especialidade em ABA
INSERT INTO especialista_profissional (idespecialidade, idespecialista) VALUES (5, 4); -- Juliana também atua em Psicopedagogia
INSERT INTO especialista_profissional (idespecialidade, idespecialista) VALUES (2, 5); -- Marcos tem especialidade em Integração Sensorial
INSERT INTO especialista_profissional (idespecialidade, idespecialista) VALUES (1, 5); -- Marcos também possui especialização em Neuropediatria

--- TAB_USUARIO (Garante acesso ao sistema para todos os funcionários 1 a 5 | PACIENTES NÃO ENTRAM)
INSERT INTO tab_usuario (idcadastro, senha) VALUES (1, 'hash_senha_gerente');
INSERT INTO tab_usuario (idcadastro, senha) VALUES (2, 'hash_senha_recepcao');
INSERT INTO tab_usuario (idcadastro, senha) VALUES (3, 'hash_senha_financeiro');
INSERT INTO tab_usuario (idcadastro, senha) VALUES (4, 'hash_senha_juliana_psic');
INSERT INTO tab_usuario (idcadastro, senha) VALUES (5, 'hash_senha_marcos_to');

--- TAB_PACIENTE (Isolados com IDs de 6 a 10)
INSERT INTO tab_paciente (idpaciente, idescola, cidprincipal, statuspaciente, datacadastro, convenio, tipoentrada) VALUES (6, 1, 'F84.0', 'ATIVO', SYSDATE, 1, 'MUNICIPAL');
INSERT INTO tab_paciente (idpaciente, idescola, cidprincipal, statuspaciente, datacadastro, convenio, tipoentrada) VALUES (7, 2, 'F90.0', 'ATIVO', SYSDATE, 0, 'MUNICIPAL');
INSERT INTO tab_paciente (idpaciente, idescola, cidprincipal, statuspaciente, datacadastro, convenio, tipoentrada) VALUES (8, 3, 'F80.0', 'ATIVO', SYSDATE, 0, 'MUNICIPAL');
INSERT INTO tab_paciente (idpaciente, idescola, cidprincipal, statuspaciente, datacadastro, convenio, tipoentrada) VALUES (9, 1, 'F81.0', 'ATIVO', SYSDATE, 0, 'MUNICIPAL');
INSERT INTO tab_paciente (idpaciente, idescola, cidprincipal, statuspaciente, datacadastro, convenio, tipoentrada) VALUES (10, 4, 'G80.0', 'ATIVO', SYSDATE, 1, 'MUNICIPAL');

--- TAB_RESPONSAVEL (Funcionários ou terceiros atuando como pais. Usaremos IDs 1 a 3 para simular pais/mães)
INSERT INTO tab_responsavel (idresponsavel, ocupacao) VALUES (1, 'Engenheiro Civil');
INSERT INTO tab_responsavel (idresponsavel, ocupacao) VALUES (2, 'Professora');
INSERT INTO tab_responsavel (idresponsavel, ocupacao) VALUES (3, 'Comerciante');

--- TAB_PRONTUARIO
INSERT INTO tab_prontuario (idpaciente, alergias, comorbidade, mobilidade) VALUES (6, 'Nenhuma', 'Distúrbio do Sono', 'Normal');
INSERT INTO tab_prontuario (idpaciente, alergias, comorbidade, mobilidade) VALUES (7, 'Dipirona', 'Nenhuma', 'Normal');
INSERT INTO tab_prontuario (idpaciente, alergias, comorbidade, mobilidade) VALUES (8, 'Nenhuma', 'Rinite Alérgica', 'Normal');
INSERT INTO tab_prontuario (idpaciente, alergias, comorbidade, mobilidade) VALUES (9, 'Glúten', 'Ansiedade', 'Normal');
INSERT INTO tab_prontuario (idpaciente, alergias, comorbidade, mobilidade) VALUES (10, 'Nenhuma', 'Epilepsia', 'Reduzida');

--- ENDERECO_PESSOA
INSERT INTO endereco_pessoa (idcadastro, idendereco) VALUES (1, 1);
INSERT INTO endereco_pessoa (idcadastro, idendereco) VALUES (2, 2);
INSERT INTO endereco_pessoa (idcadastro, idendereco) VALUES (3, 3);
INSERT INTO endereco_pessoa (idcadastro, idendereco) VALUES (4, 4);
INSERT INTO endereco_pessoa (idcadastro, idendereco) VALUES (5, 5);

--- USUARIO_PERFIL
INSERT INTO usuario_perfil (idcadastro, idperfil) VALUES (1, 1); -- Carlos -> Admin
INSERT INTO usuario_perfil (idcadastro, idperfil) VALUES (2, 2); -- Ana -> Secretaria
INSERT INTO usuario_perfil (idcadastro, idperfil) VALUES (3, 3); -- Ricardo -> Faturamento
INSERT INTO usuario_perfil (idcadastro, idperfil) VALUES (4, 4); -- Juliana -> Terapeuta
INSERT INTO usuario_perfil (idcadastro, idperfil) VALUES (5, 4); -- Marcos -> Terapeuta

--- PERFIL_PERMISSAO
INSERT INTO perfil_permissao (idperfil, idpermissao) VALUES (1, 5);
INSERT INTO perfil_permissao (idperfil, idpermissao) VALUES (2, 2);
INSERT INTO perfil_permissao (idperfil, idpermissao) VALUES (2, 4);
INSERT INTO perfil_permissao (idperfil, idpermissao) VALUES (4, 3);
INSERT INTO perfil_permissao (idperfil, idpermissao) VALUES (5, 3);

--- PACIENTE_CID
INSERT INTO paciente_cid (codigocid, idpaciente) VALUES ('F84.0', 6);
INSERT INTO paciente_cid (codigocid, idpaciente) VALUES ('F90.0', 7);
INSERT INTO paciente_cid (codigocid, idpaciente) VALUES ('F80.0', 8);
INSERT INTO paciente_cid (codigocid, idpaciente) VALUES ('F81.0', 9);
INSERT INTO paciente_cid (codigocid, idpaciente) VALUES ('G80.0', 10);

--- RESPONSAVEL_PACIENTE
INSERT INTO responsavel_paciente (idresponsavel, idpaciente, grauparentesco) VALUES (1, 6, 'Pai');
INSERT INTO responsavel_paciente (idresponsavel, idpaciente, grauparentesco) VALUES (2, 7, 'Mãe');
INSERT INTO responsavel_paciente (idresponsavel, idpaciente, grauparentesco) VALUES (3, 8, 'Pai');

--- PACIENTE_TERAPIA
INSERT INTO paciente_terapia (idterapia, idpaciente) VALUES (1, 6);
INSERT INTO paciente_terapia (idterapia, idpaciente) VALUES (2, 7);
INSERT INTO paciente_terapia (idterapia, idpaciente) VALUES (3, 8);
INSERT INTO paciente_terapia (idterapia, idpaciente) VALUES (4, 9);
INSERT INTO paciente_terapia (idterapia, idpaciente) VALUES (4, 10);

--- ESPECIALISTA_TERAPIA (Apenas os especialistas válidos (4 e 5) assumem as terapias!)
INSERT INTO especialista_terapia (idterapia, idespecialista) VALUES (1, 5); -- Marcos (ID 5) assume a Terapia 1
INSERT INTO especialista_terapia (idterapia, idespecialista) VALUES (2, 4); -- Juliana (ID 4) assume a Terapia 2
INSERT INTO especialista_terapia (idterapia, idespecialista) VALUES (3, 4); -- Juliana assume a Terapia 3
INSERT INTO especialista_terapia (idterapia, idespecialista) VALUES (4, 5); -- Marcos assume a Terapia em Grupo 4
INSERT INTO especialista_terapia (idterapia, idespecialista) VALUES (5, 4); -- Juliana assume a Terapia em Grupo 5

COMMIT;
