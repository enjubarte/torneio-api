package com.example.kotlinspring.service


import com.example.kotlinspring.dto.InscricaoDTO
import com.example.kotlinspring.dto.UsuarioDTO
import com.example.kotlinspring.mapper.dtoToInsncricao
import com.example.kotlinspring.model.Inscricao
import com.example.kotlinspring.repository.InscricaoRepository
import com.example.kotlinspring.repository.UsuarioRepository
import com.example.kotlinspring.utils.JwtUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class InscricoesService(
    private val inscricaoRepository: InscricaoRepository,
    private val usuarioRepository: UsuarioRepository,
    private val jwtUtils: JwtUtils
){
    fun createInscricao(categoriaId: UUID, token: String, usuarioDto: UsuarioDTO): ResponseEntity<Any> {
        val tokenId = UUID.fromString(jwtUtils.extractLogin(token))

        return when{
            usuarioRepository.existsByLogin(usuarioDto.login) -> {
                val inscricaoDTO= InscricaoDTO(categoriaId = categoriaId, usuarioId1 = tokenId, usuarioId2 = usuarioDto.id)
                val inscricao = Inscricao().dtoToInsncricao(inscricaoDTO)
                inscricaoRepository.save(inscricao)
                ResponseEntity.status(HttpStatus.CREATED).body(inscricao)
            }
            else -> {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível realizar sua inscrição!")
            }
        }
    }

}