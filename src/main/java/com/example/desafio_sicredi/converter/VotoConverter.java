package com.example.desafio_sicredi.converter;

import com.example.desafio_sicredi.dto.VotoDto;
import com.example.desafio_sicredi.entity.PautaEntity;
import com.example.desafio_sicredi.entity.VotoEntity;
import com.example.desafio_sicredi.repository.PautaRepository;
import jakarta.persistence.EntityNotFoundException;

public class VotoConverter {

    public static VotoEntity toEntity(VotoDto dto, PautaRepository pautaRepository) {
        VotoEntity votoEntity = new VotoEntity();
        votoEntity.setId(dto.id());
        votoEntity.setAssociadoId(dto.associadoId());
        votoEntity.setVoto(dto.voto());

        PautaEntity pautaEntity = pautaRepository.findById(dto.pautaId())
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada"));
        votoEntity.setPautaEntity(pautaEntity);

        return votoEntity;
    }

    public static VotoDto toDto(VotoEntity votoEntity) {
        return new VotoDto(
                votoEntity.getId(),
                votoEntity.getAssociadoId(),
                votoEntity.getPautaEntity().getId(),
                votoEntity.getVoto(),
                null 
        );
    }
}
