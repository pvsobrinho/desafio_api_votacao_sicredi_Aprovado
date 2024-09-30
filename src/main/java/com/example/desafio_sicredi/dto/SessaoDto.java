package com.example.desafio_sicredi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessaoDto {

    private Long id;

    @NotNull(message = "O ID da pauta é obrigatório")
    private Long pautaId;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDateTime inicio;

    @NotNull(message = "A data de fim é obrigatória")
    private LocalDateTime fim;

}