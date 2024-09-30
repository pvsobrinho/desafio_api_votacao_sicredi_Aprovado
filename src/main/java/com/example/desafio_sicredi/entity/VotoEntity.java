package com.example.desafio_sicredi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class VotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long associadoId;

    @ManyToOne
    private PautaEntity pautaEntity;

    private Boolean voto;

}
