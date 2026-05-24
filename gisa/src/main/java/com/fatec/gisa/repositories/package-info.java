/**
 * Repositories - Camada de Acesso a Dados da Aplicação GISA
 * 
 * Esta camada é responsável por:
 * - Abstração do acesso ao banco de dados
 * - Implementar padrão Repository usando Spring Data JPA
 * - Fornecer métodos CRUD básicos (herdados de JpaRepository)
 * - Permitir criação de query methods customizadas
 * - Integração com Hibernate/JPA para mapeamento O/R
 * 
 * Padrão:
 * - Herdar de JpaRepository<Entity, ID>
 * - Adicionar query methods customizados conforme necessário
 * - Não implementar lógica de negócio aqui
 * - Retornar apenas entidades mapeadas
 * 
 * Exemplo:
 * @Repository
 * public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
 *     Paciente findByCpf(String cpf);
 *     List<Paciente> findByStatusPaciente(StatusPaciente status);
 * }
 * 
 * Métodos herdados de JpaRepository:
 * - save(Entity)
 * - findById(id)
 * - findAll()
 * - delete(Entity)
 * - deleteById(id)
 * 
 * @package com.fatec.gisa.repositories
 */
package com.fatec.gisa.repositories;
