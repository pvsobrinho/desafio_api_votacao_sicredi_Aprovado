package com.example.desafio_sicredi.service;

import com.example.desafio_sicredi.converter.PautaConverter;
import com.example.desafio_sicredi.dto.PautaDto;
import com.example.desafio_sicredi.entity.PautaEntity;
import com.example.desafio_sicredi.repository.PautaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public PautaDto cadastrarPauta(PautaDto pautaDto) {
        PautaEntity pautaEntity = PautaConverter.toEntity(pautaDto);
        pautaEntity = pautaRepository.save(pautaEntity);
        return PautaConverter.toDto(pautaEntity);
    }

    public PautaDto atualizarPauta(Long id, PautaDto pautaDto) {
        if (!pautaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pauta não encontrada");
        }
        PautaEntity pautaEntity = PautaConverter.toEntity(pautaDto);
        pautaEntity.setId(id);
        pautaEntity = pautaRepository.save(pautaEntity);
        return PautaConverter.toDto(pautaEntity);
    }

    public List<PautaDto> listarPautas() {
        return pautaRepository.findAll().stream()
                .map(PautaConverter::toDto)
                .collect(Collectors.toList());
    }
}
