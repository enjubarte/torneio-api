package com.example.kotlinspring.controller


import com.example.kotlinspring.dto.UsuarioDTO
import com.example.kotlinspring.service.InscricoesService
import com.example.kotlinspring.service.TorneioService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("api/v1")
class TorneioController(
    private val torneioService: TorneioService,
    private val inscricoesService: InscricoesService,
) {

    @GetMapping("/")
    fun findAll() = torneioService.findAll()

    @GetMapping("/{torneioId}/categorias")
    fun findAllCategoriasByTorneio(@PathVariable("torneioId") id: UUID) = torneioService.findAllCategoriasByTorneio(id)

    @PostMapping("/{torneioId}/categorias/{categoriaId}/inscricao")
    fun createInscricao(@PathVariable("categoriaId") categoriaId: UUID, @RequestHeader("Authorization") token: String, @Valid @RequestBody usuarioDto: UsuarioDTO): ResponseEntity<Any> {
        return  inscricoesService.createInscricao(categoriaId, token,usuarioDto)
    }

}