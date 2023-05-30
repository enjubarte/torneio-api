package com.example.kotlinspring.service

import com.example.kotlinspring.repository.TorneioRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TorneioService (
    private val torneioRepository: TorneioRepository,
    ) {

    fun findAll(): ResponseEntity<Any> = ResponseEntity.ok()
        .body(torneioRepository.findAll())

    fun findAllCategoriasByTorneio(id: UUID): ResponseEntity<Any> =  ResponseEntity.ok()
        .body(torneioRepository.findById(id).get().categorias)

}