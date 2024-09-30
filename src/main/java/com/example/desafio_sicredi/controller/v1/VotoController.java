package com.example.desafio_sicredi.controller.v1;

import com.example.desafio_sicredi.dto.VotoDto;
import com.example.desafio_sicredi.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/votos")
@Tag(name = "Voto", description = "APIs relacionadas a Votos")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @Operation(summary = "Registrar um voto", description = "Registra um voto (sim/não) associado a uma pauta.")
    @PostMapping
    public VotoDto registrarVoto(
            @RequestBody @Valid VotoDto votoDto) {
        return votoService.registrarVoto(votoDto);
    }

    @Operation(summary = "Atualizar um voto existente", description = "Atualiza os dados de um voto existente.")
    @PutMapping("/{id}")
    public VotoDto atualizarVoto(
            @Parameter(description = "ID do voto que será atualizado", required = true)
            @PathVariable Long id,
            @RequestBody @Valid VotoDto votoDto) {
        return votoService.atualizarVoto(id, votoDto);
    }

    @Operation(summary = "Obter voto por ID", description = "Busca um voto pelo seu ID.")
    @GetMapping("/{id}")
    public VotoDto getVotoById(
            @Parameter(description = "ID do voto", required = true)
            @PathVariable Long id) {
        return votoService.getVotoById(id);
    }

    @Operation(summary = "Listar todos os votos", description = "Retorna todos os votos registrados.")
    @GetMapping
    public List<VotoDto> getAllVotos() {
        return votoService.getAllVotos();
    }
}
