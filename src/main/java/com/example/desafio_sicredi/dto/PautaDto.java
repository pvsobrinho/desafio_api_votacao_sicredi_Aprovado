package com.example.desafio_sicredi.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PautaDto(
        Long id,

        @NotBlank(message = "A descrição da pauta é obrigatória")
        String descricao,

        LocalDateTime dataCriacao
) {}
