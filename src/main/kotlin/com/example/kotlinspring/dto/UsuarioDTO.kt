package com.example.kotlinspring.dto

import com.example.kotlinspring.model.Inscricao
import java.time.Instant
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


data class UsuarioDTO(
    val id: UUID = UUID.randomUUID(),

    @field: NotBlank(message = "Nome do Usuário é obrigatório!")
    @field: Size(max = 100)
    val nome: String = "",

    @field: NotBlank(message = "Login é obrigatório")
    @field: Size(max = 20)
    val login: String = "",

    @field: NotBlank(message = "Senha é obrigatório")
    @field: Size(min = 6, max = 999, message = "Senha não pode ser inferior a 6 digitos")
    var senha: String = "",

    val inscricoes: List<Inscricao> = ArrayList(),

    val createAt: Instant = Instant.now(),
    var updateAt: Instant = Instant.now()
)
