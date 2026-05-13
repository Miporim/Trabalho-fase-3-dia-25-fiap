package com.example.smartcollector.controller;

import com.example.smartcollector.model.Catador;
import com.example.smartcollector.service.CatadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catadores")
public class CatadorController {

    private final CatadorService catadorService;

    public CatadorController(CatadorService catadorService) {
        this.catadorService = catadorService;
    }

    @GetMapping
    public ResponseEntity<List<Catador>> listarTodos() {
        List<Catador> catadores = catadorService.listarTodos();
        return ResponseEntity.ok(catadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catador> buscarPorId(@PathVariable Long id) {
        return catadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Catador> cadastrar(@RequestBody Catador catador) {
        Catador catadorSalvo = catadorService.salvar(catador);
        return ResponseEntity.ok(catadorSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catador> atualizar(
            @PathVariable Long id,
            @RequestBody Catador catador
    ) {
        Catador catadorAtualizado = catadorService.atualizar(id, catador);
        return ResponseEntity.ok(catadorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        catadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}