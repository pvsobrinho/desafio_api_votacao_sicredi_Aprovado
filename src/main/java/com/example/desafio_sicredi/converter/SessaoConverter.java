package com.example.desafio_sicredi.converter;

import com.example.desafio_sicredi.dto.SessaoDto;
import com.example.desafio_sicredi.entity.PautaEntity;
import com.example.desafio_sicredi.entity.SessaoEntity;
import com.example.desafio_sicredi.repository.PautaRepository;
import jakarta.persistence.EntityNotFoundException;

public class SessaoConverter {

    public static SessaoEntity toEntity(SessaoDto dto, PautaRepository pautaRepository) {
        SessaoEntity sessaoEntity = new SessaoEntity();
        sessaoEntity.setId(dto.getId());
        sessaoEntity.setInicio(dto.getInicio());
        sessaoEntity.setFim(dto.getFim());

        PautaEntity pautaEntity = pautaRepository.findById(dto.getPautaId())
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada"));
        sessaoEntity.setPautaEntity(pautaEntity);

        return sessaoEntity;
    }

    public static SessaoDto toDto(SessaoEntity sessaoEntity) {
        SessaoDto dto = new SessaoDto();
        dto.setId(sessaoEntity.getId());
        dto.setPautaId(sessaoEntity.getPautaEntity().getId());
        dto.setInicio(sessaoEntity.getInicio());
        dto.setFim(sessaoEntity.getFim());
        return dto;
    }
}