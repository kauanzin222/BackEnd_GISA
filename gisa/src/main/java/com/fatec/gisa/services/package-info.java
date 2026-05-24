/**
 * Services - Camada de Lógica de Negócio da Aplicação GISA
 * 
 * Esta camada é responsável por:
 * - Implementar a lógica de negócio da aplicação
 * - Realizar validações complexas
 * - Orquestrar operações entre múltiplos repositórios
 * - Tratamento e conversão de exceções
 * - Operações CRUD com regras de negócio
 * 
 * Padrão de métodos:
 * - criar(Entity)        → valida e salva a entidade
 * - listarTodos()        → retorna lista de entidades
 * - buscarPorId(id)      → busca entidade específica
 * - atualizar(id, Entity) → valida e atualiza parcialmente
 * - deletar(id)          → deleta entidade
 * 
 * Exemplo:
 * @Service
 * public class PacienteService {
 *     @Autowired
 *     private PacienteRepository pacienteRepository;
 *     
 *     public Paciente criar(Paciente paciente) {
 *         // validações de negócio
 *         return pacienteRepository.save(paciente);
 *     }
 * }
 * 
 * @package com.fatec.gisa.services
 */
package com.fatec.gisa.services;
