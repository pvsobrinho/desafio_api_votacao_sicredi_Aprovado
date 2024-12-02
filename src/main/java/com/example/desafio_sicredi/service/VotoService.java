package com.example.desafio_sicredi.service;

import com.example.desafio_sicredi.converter.VotoConverter;
import com.example.desafio_sicredi.dto.VotoDto;
import com.example.desafio_sicredi.entity.PautaEntity;
import com.example.desafio_sicredi.entity.VotoEntity;
import com.example.desafio_sicredi.repository.PautaRepository;
import com.example.desafio_sicredi.repository.VotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotoService {

    // Diferente das outras classes de serviço. adicionei descrições/comentários para entendimento do fluxo de validação

    private final VotoRepository votoRepository;
    private final PautaRepository pautaRepository;
    private final CpfValidationService cpfValidationService;

    public VotoService(VotoRepository votoRepository, PautaRepository pautaRepository, CpfValidationService cpfValidationService) {
        this.votoRepository = votoRepository;
        this.pautaRepository = pautaRepository;
        this.cpfValidationService = cpfValidationService;
    }

    public VotoDto registrarVoto(VotoDto votoDto) {
        // Valida se a pauta existe
        PautaEntity pauta = pautaRepository.findById(votoDto.pautaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada"));

        // Valida o CPF via serviço simulado
        boolean canVote = cpfValidationService.isAbleToVote(votoDto.cpf());
        if (!canVote) {
            // Retorna um erro 400 (Bad Request) se o CPF for inválido
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido ou associado não está apto a votar.");
        }
        VotoEntity voto = VotoConverter.toEntity(votoDto, pautaRepository);
        voto = votoRepository.save(voto);
        return VotoConverter.toDto(voto);
    }

    public VotoDto atualizarVoto(Long id, VotoDto votoDto) {
        if (!votoRepository.existsById(id)) {
            throw new EntityNotFoundException("Voto não encontrado");
        }

        // Validar o CPF novamente ao atualizar o voto
        // Como dito anteriormente a informação do CPF não é gravada no banco de dados para este exemplo o voto é secreto.
        boolean canVote = cpfValidationService.isAbleToVote(votoDto.cpf());
        if (!canVote) {
            throw new IllegalArgumentException("Associado não está apto a votar ou CPF inválido (simulação de validação).");
        }

        VotoEntity voto = VotoConverter.toEntity(votoDto, pautaRepository);
        voto.setId(id);
        voto = votoRepository.save(voto);
        return VotoConverter.toDto(voto);
    }

    public VotoDto getVotoById(Long id) {
        VotoEntity votoEntity = votoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Voto não encontrado"));
        return VotoConverter.toDto(votoEntity);
    }

    public List<VotoDto> getAllVotos() {
        return votoRepository.findAll().stream()
                .map(VotoConverter::toDto)
                .collect(Collectors.toList());
    }
}
