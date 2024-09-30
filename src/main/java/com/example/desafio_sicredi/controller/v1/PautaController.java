package com.example.desafio_sicredi.controller.v1;

import com.example.desafio_sicredi.dto.PautaDto;
import com.example.desafio_sicredi.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/pautas")
@Tag(name = "Pauta", description = "APIs relacionadas a Pautas")
public class PautaController {
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @Operation(summary = "Cadastrar uma nova pauta", description = "Cria uma nova pauta para votação.")
    @PostMapping
    public PautaDto cadastrarPauta(
            @RequestBody @Valid PautaDto pautaDto) {
        return pautaService.cadastrarPauta(pautaDto);
    }

    @Operation(summary = "Atualizar uma pauta existente", description = "Atualiza os dados de uma pauta existente.")
    @PutMapping("/{id}")
    public PautaDto atualizarPauta(
            @Parameter(description = "ID da pauta que será atualizada", required = true)
            @PathVariable Long id,
            @RequestBody @Valid PautaDto pautaDto) {
        return pautaService.atualizarPauta(id, pautaDto);
    }

    @Operation(summary = "Listar todas as pautas", description = "Retorna todas as pautas cadastradas.")
    @GetMapping
    public List<PautaDto> listarPautas() {
        return pautaService.listarPautas();
    }
}
