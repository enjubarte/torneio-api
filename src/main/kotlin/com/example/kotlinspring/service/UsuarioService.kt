package com.example.kotlinspring.service


import com.example.kotlinspring.dto.UsuarioDTO
import com.example.kotlinspring.mapper.dtoToUsuario
import com.example.kotlinspring.model.Inscricao
import com.example.kotlinspring.model.Usuario
import com.example.kotlinspring.repository.UsuarioRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.net.URI
import java.util.UUID
import java.util.stream.Collectors

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
): UserDetailsService{

    fun create(usuarioDTO: UsuarioDTO): ResponseEntity<Any>{
        return if (usuarioRepository.existsByLogin(usuarioDTO.login)){
           ResponseEntity.badRequest().body("Usuário já cadastrado")
        }else{
            usuarioRepository.save(usuarioDTO.dtoToUsuario(usuarioDTO))
            ResponseEntity.created(URI.create("usuario/${usuarioDTO.id}")).build()
        }
    }

    fun findById(id: UUID): ResponseEntity<Any> {
        return if (!usuarioRepository.existsById(id)){
            ResponseEntity.notFound().build()
        }else{
            ResponseEntity.ok(usuarioRepository.findById(id).get())
        }
    }

    fun findByLogin(login: String): ResponseEntity<Any>{
       return when{
           !usuarioRepository.existsByLogin(login) -> ResponseEntity.notFound().build()
           else -> {
               ResponseEntity.ok().body(usuarioRepository.findByLogin(login))
           }
       }
    }

    fun update(id: UUID, usuarioDTO: UsuarioDTO): ResponseEntity<Any>{
        val usuario = usuarioDTO.dtoToUsuario(usuarioDTO)
        return if (usuarioRepository.existsById(id)) {
            usuarioRepository.save(usuario)
            ResponseEntity.ok().build()
        }else{
            ResponseEntity.notFound().build()
        }
    }

    fun delete(id: UUID): ResponseEntity<Any>{
        return if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id)
            ResponseEntity.noContent().build()
        }else{
            ResponseEntity.notFound().build()
        }
    }

    fun findAllInscricoes(id: UUID): MutableList<Inscricao>? {
        return usuarioRepository.findById(id).get().inscricoes.stream().collect(Collectors.toList())
    }

    override fun loadUserByUsername(username: String?): Usuario? = username?.let { usuarioRepository.findByLogin(it) }
}