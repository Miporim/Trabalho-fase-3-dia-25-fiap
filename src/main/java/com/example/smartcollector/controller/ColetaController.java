package com.example.smartcollector.controller;

import com.example.smartcollector.model.Coleta;
import com.example.smartcollector.service.ColetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coletas")
public class ColetaController {

    private final ColetaService coletaService;

    public ColetaController(ColetaService coletaService) {
        this.coletaService = coletaService;
    }

    @GetMapping
    public ResponseEntity<List<Coleta>> listarTodas() {
        List<Coleta> coletas = coletaService.listarTodas();
        return ResponseEntity.ok(coletas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coleta> buscarPorId(@PathVariable Long id) {
        return coletaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Coleta> cadastrar(@RequestBody Coleta coleta) {
        Coleta coletaSalva = coletaService.salvar(coleta);
        return ResponseEntity.ok(coletaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coleta> atualizar(
            @PathVariable Long id,
            @RequestBody Coleta coleta
    ) {
        Coleta coletaAtualizada = coletaService.atualizar(id, coleta);
        return ResponseEntity.ok(coletaAtualizada);
    }

    @PutMapping("/{idColeta}/aceitar/{idCatador}")
    public ResponseEntity<Coleta> aceitarColeta(
            @PathVariable Long idColeta,
            @PathVariable Long idCatador
    ) {
        Coleta coletaAtualizada = coletaService.aceitarColeta(idColeta, idCatador);
        return ResponseEntity.ok(coletaAtualizada);
    }

    @PutMapping("/{idColeta}/finalizar/{idCentro}")
    public ResponseEntity<Coleta> finalizarColeta(
            @PathVariable Long idColeta,
            @PathVariable Long idCentro
    ) {
        Coleta coletaFinalizada = coletaService.finalizarColeta(idColeta, idCentro);
        return ResponseEntity.ok(coletaFinalizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        coletaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}