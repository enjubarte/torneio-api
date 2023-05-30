package com.example.kotlinspring.model

import java.io.Serializable
import java.time.Instant
import java.util.ArrayList
import java.util.Optional
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotBlank

@Entity(name = "tb_torneio")
data class Torneio(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val nome: String = "",

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "id")
    val categorias: List<Categoria> = ArrayList(),

    val createAt: Instant = Instant.now()
): Serializable