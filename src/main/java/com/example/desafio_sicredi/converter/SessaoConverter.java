package com.example.desafio_sicredi.converter;

import com.example.desafio_sicredi.dto.SessaoDto;
import com.example.desafio_sicredi.entity.PautaEntity;
import com.example.desafio_sicredi.entity.SessaoEntity;
import com.example.desafio_sicredi.repository.PautaRepository;
import jakarta.persistence.EntityNotFoundException;

public class SessaoConverter {

    public static SessaoEntity toEntity(SessaoDto dto, PautaRepository pautaRepository) {
        SessaoEntity sessaoEntity = new SessaoEntity();
        sessaoEntity.setId(dto.id());
        sessaoEntity.setInicio(dto.inicio());
        sessaoEntity.setFim(dto.fim());

        PautaEntity pautaEntity = pautaRepository.findById(dto.pautaId())
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada"));
        sessaoEntity.setPautaEntity(pautaEntity);

        return sessaoEntity;
    }

    public static SessaoDto toDto(SessaoEntity sessaoEntity) {
        return new SessaoDto(
                sessaoEntity.getId(),
                sessaoEntity.getPautaEntity().getId(),
                sessaoEntity.getInicio(),
                sessaoEntity.getFim()
        );
    }
}
