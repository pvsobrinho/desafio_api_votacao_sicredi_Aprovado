package com.example.desafio_sicredi.converter;

import com.example.desafio_sicredi.dto.VotoDto;
import com.example.desafio_sicredi.entity.PautaEntity;
import com.example.desafio_sicredi.entity.VotoEntity;
import com.example.desafio_sicredi.repository.PautaRepository;
import jakarta.persistence.EntityNotFoundException;

public class VotoConverter {

    public static VotoEntity toEntity(VotoDto dto, PautaRepository pautaRepository) {
        VotoEntity votoEntity = new VotoEntity();
        votoEntity.setId(dto.getId());
        votoEntity.setAssociadoId(dto.getAssociadoId());
        votoEntity.setVoto(dto.getVoto());

        PautaEntity pautaEntity = pautaRepository.findById(dto.getPautaId())
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada"));
        votoEntity.setPautaEntity(pautaEntity);

        return votoEntity;
    }

    public static VotoDto toDto(VotoEntity votoEntity) {
        VotoDto dto = new VotoDto();
        dto.setId(votoEntity.getId());
        dto.setAssociadoId(votoEntity.getAssociadoId());
        dto.setPautaId(votoEntity.getPautaEntity().getId());
        dto.setVoto(votoEntity.getVoto());
        return dto;
    }
}
