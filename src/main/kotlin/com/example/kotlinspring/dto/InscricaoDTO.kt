package com.example.kotlinspring.dto

import java.time.Instant
import java.util.*
import javax.validation.constraints.NotBlank

data class InscricaoDTO(

    val id: UUID = UUID.randomUUID(),

    @field: NotBlank(message = "A categoria não pode ser nula")
    val categoriaId: UUID,

    @field: NotBlank(message = "O primeiro usuário não pode ser nulo")
    val usuarioId1: UUID,

    @field: NotBlank(message = "O segundo usuário não pode ser nulo")
    val usuarioId2: UUID,

    val createAt: Instant = Instant.now()
)
