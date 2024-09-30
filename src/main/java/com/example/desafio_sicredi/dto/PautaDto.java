package com.example.desafio_sicredi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PautaDto {

    private Long id;

    @NotBlank(message = "A descrição da pauta é obrigatória")
    private String descricao;

    private LocalDateTime dataCriacao;
}
