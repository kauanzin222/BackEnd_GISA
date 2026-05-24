/**
 * Controllers - REST Endpoints da Aplicação GISA
 * 
 * Esta camada é responsável por:
 * - Expor endpoints REST para consumo da API
 * - Mapear requisições HTTP (GET, POST, PUT, DELETE)
 * - Validar entrada básica de dados
 * - Formatar respostas HTTP com status codes apropriados
 * - Tratamento de erros e exceções
 * 
 * Padrão de URLs:
 * - GET    /api/{recurso}        → listar todos
 * - POST   /api/{recurso}        → criar novo
 * - GET    /api/{recurso}/{id}   → buscar por ID
 * - PUT    /api/{recurso}/{id}   → atualizar
 * - DELETE /api/{recurso}/{id}   → deletar
 * 
 * Exemplo:
 * @RestController
 * @RequestMapping("/api/pacientes")
 * public class PacienteController {
 *     @PostMapping
 *     public ResponseEntity<Paciente> criar(@RequestBody Paciente paciente)
 * }
 * 
 * @package com.fatec.gisa.controllers
 */
package com.fatec.gisa.controllers;
