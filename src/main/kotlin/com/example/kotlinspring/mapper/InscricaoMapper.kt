package com.example.kotlinspring.mapper

import com.example.kotlinspring.dto.InscricaoDTO
import com.example.kotlinspring.model.Inscricao

fun Inscricao.inscricaoToDTO(inscricao: Inscricao): InscricaoDTO{
    return InscricaoDTO(
        inscricao.id,
        inscricao.categoriaId,
        inscricao.usuarioId1,
        inscricao.usuarioId2,
        inscricao.createAt
    )
}

fun Inscricao.dtoToInsncricao(inscricaoDTO: InscricaoDTO): Inscricao{
    return Inscricao(
        inscricaoDTO.id,
        inscricaoDTO.categoriaId,
        inscricaoDTO.usuarioId1,
        inscricaoDTO.usuarioId2,
        inscricaoDTO.createAt
    )
}
