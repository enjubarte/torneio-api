package com.example.kotlinspring.repository

import com.example.kotlinspring.model.Categoria
import com.example.kotlinspring.model.Inscricao
import com.example.kotlinspring.model.Torneio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CategoriaRepository : JpaRepository<Categoria, UUID>{
}