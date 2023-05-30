package com.example.kotlinspring.model

import java.io.Serializable
import java.util.ArrayList
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity(name = "tb_categoria")
data class Categoria(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(targetEntity = Categoria::class)
    @JoinColumn(name = "torneio_id", nullable = false)
    val torneio_id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val nome: String = "",

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "id")
    val inscricoes: List<Inscricao> = ArrayList()

): Serializable
