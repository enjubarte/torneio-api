package com.example.kotlinspring.model

import java.io.Serializable
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "tb_inscricoes")
data class Inscricao(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(targetEntity = Inscricao::class)
    @JoinColumn(name = "categoria_id", nullable = false)
    val categoriaId: UUID = UUID.randomUUID(),

    @ManyToOne(targetEntity = Inscricao::class)
    @JoinColumn(name = "usuario_id1", nullable = false)
    val usuarioId1: UUID = UUID.randomUUID(),

    @ManyToOne(targetEntity = Inscricao::class)
    @JoinColumn(name = "usuario_id2", nullable = false)
    val usuarioId2: UUID = UUID.randomUUID(),

    val createAt: Instant = Instant.now(),
): Serializable
