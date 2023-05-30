package com.example.kotlinspring.mapper

import com.example.kotlinspring.dto.UsuarioDTO
import com.example.kotlinspring.model.Usuario
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun Usuario.usuarioToDTO(usuario: Usuario): UsuarioDTO{
    return UsuarioDTO(
        usuario.id,
        usuario.nome,
        usuario.login,
        usuario.senha,
        usuario.inscricoes
    )
}

fun UsuarioDTO.dtoToUsuario(usuarioDTO: UsuarioDTO): Usuario{
    return Usuario(
        usuarioDTO.id,
        usuarioDTO.nome,
        usuarioDTO.login,
        BCryptPasswordEncoder().encode(usuarioDTO.senha),
        usuarioDTO.inscricoes
    )
}