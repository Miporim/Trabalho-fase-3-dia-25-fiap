package com.example.smartcollector.controller;

import com.example.smartcollector.model.Descartador;
import com.example.smartcollector.service.DescartadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/descartadores")
public class DescartadorController {

    private final DescartadorService descartadorService;

    public DescartadorController(DescartadorService descartadorService) {
        this.descartadorService = descartadorService;
    }

    @GetMapping
    public ResponseEntity<List<Descartador>> listarTodos() {
        List<Descartador> descartadores = descartadorService.listarTodos();
        return ResponseEntity.ok(descartadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Descartador> buscarPorId(@PathVariable Long id) {
        return descartadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Descartador> cadastrar(@RequestBody Descartador descartador) {
        Descartador descartadorSalvo = descartadorService.salvar(descartador);
        return ResponseEntity.ok(descartadorSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Descartador> atualizar(
            @PathVariable Long id,
            @RequestBody Descartador descartador
    ) {
        Descartador descartadorAtualizado = descartadorService.atualizar(id, descartador);
        return ResponseEntity.ok(descartadorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        descartadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}