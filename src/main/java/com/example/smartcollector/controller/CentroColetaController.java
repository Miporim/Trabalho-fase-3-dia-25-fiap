package com.example.smartcollector.controller;

import com.example.smartcollector.model.CentroColeta;
import com.example.smartcollector.service.CentroColetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centros-coleta")
public class CentroColetaController {

    private final CentroColetaService centroColetaService;

    public CentroColetaController(CentroColetaService centroColetaService) {
        this.centroColetaService = centroColetaService;
    }

    @GetMapping
    public ResponseEntity<List<CentroColeta>> listarTodos() {
        List<CentroColeta> centros = centroColetaService.listarTodos();
        return ResponseEntity.ok(centros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroColeta> buscarPorId(@PathVariable Long id) {
        return centroColetaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CentroColeta> cadastrar(@RequestBody CentroColeta centroColeta) {
        CentroColeta centroSalvo = centroColetaService.salvar(centroColeta);
        return ResponseEntity.ok(centroSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroColeta> atualizar(
            @PathVariable Long id,
            @RequestBody CentroColeta centroColeta
    ) {
        CentroColeta centroAtualizado = centroColetaService.atualizar(id, centroColeta);
        return ResponseEntity.ok(centroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        centroColetaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}