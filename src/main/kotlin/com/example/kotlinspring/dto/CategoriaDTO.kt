package com.example.kotlinspring.dto

import java.time.Instant
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CategoriaDTO(
    val id: UUID,

    @field: NotBlank(message = "O torneio não pode ser nulo.")
    val torneio_id: UUID,

    @field: NotBlank(message = "O nome da categoria não pode ser nula.")
    @field: Size(max = 100, message = "O nome da categoria nẽo pode ter mais que 100 caracteres.")
    val nome: String = "",

    val createAt: Instant = Instant.now()
)
