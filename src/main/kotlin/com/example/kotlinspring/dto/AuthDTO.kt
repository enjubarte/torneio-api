package com.example.kotlinspring.dto

import javax.validation.constraints.NotBlank

data class AuthDTO (
    @field: NotBlank(message = "Login é obrigatório")
    val login: String = "",

    @field: NotBlank(message = "Senha é obrigatória")
    val senha: String = ""
)