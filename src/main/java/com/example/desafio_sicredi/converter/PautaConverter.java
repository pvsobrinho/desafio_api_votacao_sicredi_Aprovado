package com.example.desafio_sicredi.converter;

import com.example.desafio_sicredi.dto.PautaDto;
import com.example.desafio_sicredi.entity.PautaEntity;

public class PautaConverter {

    public static PautaEntity toEntity(PautaDto dto) {
        PautaEntity pautaEntity = new PautaEntity();
        pautaEntity.setId(dto.getId());
        pautaEntity.setDescricao(dto.getDescricao());
        pautaEntity.setDataCriacao(dto.getDataCriacao());
        return pautaEntity;
    }

    public static PautaDto toDto(PautaEntity pautaEntity) {
        PautaDto dto = new PautaDto();
        dto.setId(pautaEntity.getId());
        dto.setDescricao(pautaEntity.getDescricao());
        dto.setDataCriacao(pautaEntity.getDataCriacao());
        return dto;
    }
}
