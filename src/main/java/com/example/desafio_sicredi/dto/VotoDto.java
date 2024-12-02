package com.example.desafio_sicredi.dto;

import com.example.desafio_sicredi.util.ValidCPF;
import jakarta.validation.constraints.NotNull;

public record VotoDto(
        Long id,

        @NotNull(message = "O ID do associado é obrigatório")
        Long associadoId,

        @NotNull(message = "O ID da pauta é obrigatório")
        Long pautaId,

        @NotNull(message = "O voto (sim/não) é obrigatório")
        Boolean voto,

        @NotNull(message = "O CPF do associado é obrigatório")
        @ValidCPF
        String cpf
) {}
