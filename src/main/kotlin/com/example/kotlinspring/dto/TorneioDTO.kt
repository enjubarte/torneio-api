package com.example.kotlinspring.dto

import java.time.Instant
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class TorneioDTO(
    val id: UUID,

    @field: NotBlank(message = "Nome do torneio é obrigatório.")
    @field: Size(max = 100, message = "Nome não pode ter mais que 100 caracteres.")
    val nome: String = "",

    val createAt: Instant = Instant.now()
)
