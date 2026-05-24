/**
 * Models - Entidades JPA da Aplicação GISA
 * 
 * Esta camada define:
 * - Entidades JPA (@Entity) mapeadas para tabelas do banco
 * - Relacionamentos entre entidades (OneToMany, ManyToOne, ManyToMany, OneToOne)
 * - Validações de integridade estrutural
 * - Getters e Setters para acesso aos atributos
 * - Sequências para geração de IDs
 * 
 * Anotações Principais:
 * @Entity          → marca como entidade JPA
 * @Table           → especifica nome da tabela
 * @Id              → identifica chave primária
 * @GeneratedValue  → auto-incremento
 * @Column          → configura coluna do banco
 * @OneToMany       → relacionamento 1:N
 * @ManyToOne       → relacionamento N:1
 * @ManyToMany      → relacionamento M:N
 * @JoinColumn      → especifica coluna de FK
 * @JoinTable       → especifica tabela de junção (M:M)
 * 
 * Exemplo:
 * @Entity
 * @Table(name = "PACIENTE")
 * public class Paciente extends Pessoa {
 *     @ManyToOne
 *     @JoinColumn(name = "ID_ESCOLA")
 *     private Escola escola;
 * }
 * 
 * @package com.fatec.gisa.models
 */
package com.fatec.gisa.models;
