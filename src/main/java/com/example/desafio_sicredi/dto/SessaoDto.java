package com.example.desafio_sicredi.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SessaoDto(
        Long id,

        @NotNull(message = "O ID da pauta é obrigatório")
        Long pautaId,

        @NotNull(message = "A data de início é obrigatória")
        LocalDateTime inicio,

        @NotNull(message = "A data de fim é obrigatória")
        LocalDateTime fim
) {}
