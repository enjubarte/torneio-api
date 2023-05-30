package com.example.kotlinspring.service

import com.example.kotlinspring.dto.AuthDTO
import com.example.kotlinspring.utils.JwtUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthService (
    private val authenticationManager: AuthenticationManager,
    private val usuarioService: UsuarioService,
    private val jwtUtils: JwtUtils
){

    fun auth(authDTO: AuthDTO): ResponseEntity<Any>{
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authDTO.login,authDTO.senha)
        )

        val usuario = usuarioService.loadUserByUsername(authDTO.login)

        return if(usuario != null){
            ResponseEntity.ok().body(jwtUtils.generateToken(usuario))
        }else{
            ResponseEntity.badRequest().build()
        }
    }
}