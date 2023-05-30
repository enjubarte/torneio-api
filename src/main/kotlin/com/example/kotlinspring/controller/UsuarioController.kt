package com.example.kotlinspring.controller

import com.example.kotlinspring.dto.UsuarioDTO
import com.example.kotlinspring.service.UsuarioService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid


@RestController
@RequestMapping("api/v1/usuario")
class UsuarioController(
    private val usuarioService: UsuarioService
){

    @GetMapping("/")
    fun findByLogin(@RequestParam("login") login: String) = usuarioService.findByLogin(login)

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: UUID) = usuarioService.findById(id)
    @GetMapping("/{id}/inscricoes")
    fun findAllInscricoes(@PathVariable("id") id: UUID) = usuarioService.findAllInscricoes(id)

    @PutMapping("/{id}")
    fun update(@Valid @PathVariable("id") id: UUID, @RequestBody usuario: UsuarioDTO) = usuarioService.update(id,usuario)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: UUID) = usuarioService.delete(id)

}