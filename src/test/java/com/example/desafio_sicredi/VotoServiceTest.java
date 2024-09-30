package com.example.desafio_sicredi;

import com.example.desafio_sicredi.dto.VotoDto;
import com.example.desafio_sicredi.entity.PautaEntity;
import com.example.desafio_sicredi.entity.VotoEntity;
import com.example.desafio_sicredi.repository.PautaRepository;
import com.example.desafio_sicredi.repository.VotoRepository;
import com.example.desafio_sicredi.service.CpfValidationService;
import com.example.desafio_sicredi.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VotoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private CpfValidationService cpfValidationService;

    @InjectMocks
    private VotoService votoService;

    private VotoDto votoDto;
    private PautaEntity pauta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        votoDto = new VotoDto();
        votoDto.setPautaId(1L);
        votoDto.setCpf("12345678909");
        votoDto.setVoto(true);

        pauta = new PautaEntity();
        pauta.setId(1L);
        pauta.setDescricao("Pauta de Teste");
    }

    @Test
    void registrarVoto_DeveRegistrarQuandoCpfValido() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));

        when(cpfValidationService.isAbleToVote("12345678909")).thenReturn(true);

        VotoEntity votoEntity = new VotoEntity();
        votoEntity.setId(1L);
        votoEntity.setPautaEntity(pauta);

        when(votoRepository.save(any(VotoEntity.class))).thenReturn(votoEntity);

        VotoDto resultado = votoService.registrarVoto(votoDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(votoRepository, times(1)).save(any(VotoEntity.class));
    }


    @Test
    void registrarVoto_DeveLancarExcecaoQuandoPautaNaoExistir() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            votoService.registrarVoto(votoDto);
        });

        assertEquals(404, exception.getStatusCode().value());
        assertEquals("Pauta não encontrada", exception.getReason());
    }

    @Test
    void registrarVoto_DeveLancarExcecaoQuandoCpfInvalido() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));

        when(cpfValidationService.isAbleToVote("12345678909")).thenReturn(false);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            votoService.registrarVoto(votoDto);
        });
        assertEquals(400, exception.getStatusCode().value());
        assertEquals("CPF inválido ou associado não está apto a votar.", exception.getReason());
    }
}

