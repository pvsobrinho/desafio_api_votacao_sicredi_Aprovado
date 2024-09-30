package com.example.desafio_sicredi.service;


import com.example.desafio_sicredi.converter.SessaoConverter;
import com.example.desafio_sicredi.dto.SessaoDto;
import com.example.desafio_sicredi.entity.SessaoEntity;
import com.example.desafio_sicredi.repository.PautaRepository;
import com.example.desafio_sicredi.repository.SessaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;

    public SessaoService(SessaoRepository sessaoRepository, PautaRepository pautaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
    }

    public SessaoDto abrirSessao(SessaoDto sessaoDto) {
        SessaoEntity sessaoEntity = SessaoConverter.toEntity(sessaoDto, pautaRepository);
        sessaoEntity = sessaoRepository.save(sessaoEntity);
        return SessaoConverter.toDto(sessaoEntity);
    }

    public SessaoDto atualizarSessao(Long id, SessaoDto sessaoDto) {
        if (!sessaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Sessão não encontrada");
        }
        SessaoEntity sessaoEntity = SessaoConverter.toEntity(sessaoDto, pautaRepository);
        sessaoEntity.setId(id);
        sessaoEntity = sessaoRepository.save(sessaoEntity);
        return SessaoConverter.toDto(sessaoEntity);
    }

    public SessaoDto getSessaoById(Long id) {
        SessaoEntity sessaoEntity = sessaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sessão não encontrada"));
        return SessaoConverter.toDto(sessaoEntity);
    }

    public List<SessaoDto> getAllSessoes() {
        return sessaoRepository.findAll().stream()
                .map(SessaoConverter::toDto)
                .collect(Collectors.toList());
    }
}
