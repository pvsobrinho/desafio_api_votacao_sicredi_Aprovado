package com.example.desafio_sicredi.converter;

import com.example.desafio_sicredi.dto.PautaDto;
import com.example.desafio_sicredi.entity.PautaEntity;

public class PautaConverter {

    public static PautaEntity toEntity(PautaDto dto) {
        PautaEntity pautaEntity = new PautaEntity();
        pautaEntity.setId(dto.id());
        pautaEntity.setDescricao(dto.descricao());
        pautaEntity.setDataCriacao(dto.dataCriacao());
        return pautaEntity;
    }

    public static PautaDto toDto(PautaEntity pautaEntity) {
        return new PautaDto(
                pautaEntity.getId(),
                pautaEntity.getDescricao(),
                pautaEntity.getDataCriacao()
        );
    }
}
