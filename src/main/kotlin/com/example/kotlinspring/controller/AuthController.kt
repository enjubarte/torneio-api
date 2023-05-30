package com.example.kotlinspring.controller

import com.example.kotlinspring.dto.AuthDTO
import com.example.kotlinspring.dto.UsuarioDTO
import com.example.kotlinspring.service.AuthService
import com.example.kotlinspring.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/auth")
class AuthController(
    private val authService: AuthService,
    private val usuarioService: UsuarioService
) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody authDTO: AuthDTO): ResponseEntity<Any>{
        return authService.auth(authDTO)
    }

    @PostMapping("/register")
    fun create(@Valid @RequestBody usuario: UsuarioDTO) = usuarioService.create(usuario)
}