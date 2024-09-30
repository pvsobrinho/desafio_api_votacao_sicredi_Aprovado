package com.example.desafio_sicredi.controller.v1;


import com.example.desafio_sicredi.dto.SessaoDto;
import com.example.desafio_sicredi.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessoes")
@Tag(name = "Sessao", description = "APIs relacionadas a Sessões de Votação")
public class SessaoController {

    private final SessaoService sessaoService;
    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @Operation(summary = "Abrir uma nova sessão de votação", description = "Cria uma nova sessão de votação associada a uma pauta.")
    @PostMapping
    public SessaoDto abrirSessao(
            @RequestBody @Valid SessaoDto sessaoDto) {
        return sessaoService.abrirSessao(sessaoDto);
    }

    @Operation(summary = "Atualizar uma sessão existente", description = "Atualiza os dados de uma sessão de votação existente.")
    @PutMapping("/{id}")
    public SessaoDto atualizarSessao(
            @Parameter(description = "ID da sessão que será atualizada", required = true)
            @PathVariable Long id,
            @RequestBody @Valid SessaoDto sessaoDto) {
        return sessaoService.atualizarSessao(id, sessaoDto);
    }

    @Operation(summary = "Obter sessão por ID", description = "Busca uma sessão de votação pelo seu ID.")
    @GetMapping("/{id}")
    public SessaoDto getSessaoById(
            @Parameter(description = "ID da sessão", required = true)
            @PathVariable Long id) {
        return sessaoService.getSessaoById(id);
    }

    @Operation(summary = "Listar todas as sessões", description = "Retorna todas as sessões de votação registradas.")
    @GetMapping
    public List<SessaoDto> getAllSessoes() {
        return sessaoService.getAllSessoes();
    }
}
