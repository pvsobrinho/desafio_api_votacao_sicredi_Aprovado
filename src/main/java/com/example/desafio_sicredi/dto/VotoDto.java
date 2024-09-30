package com.example.desafio_sicredi.dto;

import com.example.desafio_sicredi.util.ValidCPF;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VotoDto {

    private Long id;

    @NotNull(message = "O ID do associado é obrigatório")
    private Long associadoId;

    @NotNull(message = "O ID da pauta é obrigatório")
    private Long pautaId;

    @NotNull(message = "O voto (sim/não) é obrigatório")
    private Boolean voto;

    @NotNull(message = "O CPF do associado é obrigatório")
    @ValidCPF // anotação personalizada para validar o formato do CPF
    private String cpf;


}