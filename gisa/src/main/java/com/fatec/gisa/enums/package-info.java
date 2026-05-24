/**
 * Enums - Enumerações da Aplicação GISA
 * 
 * Esta camada define:
 * - Valores constantes e controlados para campos específicos
 * - Enumerações mapeadas em colunas de banco com @Enumerated
 * - Tipos seguros (type-safe) para valores predefinidos
 * 
 * Enums Definidos:
 * - EstadoCivil: SOLTEIRO, CASADO, SEPARADO, DIVORCIADO, VIUVO
 * - StatusCadastro: ATIVO, INATIVO, SUSPENSO, EXCLUIDO
 * - StatusPaciente: ATIVO, DESLIGADO, SUSPENSO, AGUARDANDO
 * - StatusTerapia: AGENDADA, REALIZADA, CANCELADA, REMARCADA
 * - TipoEntrada: PRIMEIRA_VEZ, RETORNO, ENCAMINHADO
 * - TipoEscola: PUBLICA, PRIVADA, MUNICIPAL, ESTADUAL
 * - Modalidade: INDIVIDUAL, COLETIVA
 * 
 * Uso em Entidades:
 * @Enumerated(EnumType.STRING)
 * @Column(name = "ESTADO_CIVIL")
 * private EstadoCivil estadoCivil;
 * 
 * @package com.fatec.gisa.enums
 */
package com.fatec.gisa.enums;
