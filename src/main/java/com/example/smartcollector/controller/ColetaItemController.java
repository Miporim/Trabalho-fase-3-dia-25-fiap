package com.example.smartcollector.controller;

import com.example.smartcollector.model.ColetaItem;
import com.example.smartcollector.service.ColetaItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coleta-itens")
public class ColetaItemController {

    private final ColetaItemService coletaItemService;

    public ColetaItemController(ColetaItemService coletaItemService) {
        this.coletaItemService = coletaItemService;
    }

    @GetMapping
    public ResponseEntity<List<ColetaItem>> listarTodos() {
        List<ColetaItem> coletaItens = coletaItemService.listarTodos();
        return ResponseEntity.ok(coletaItens);
    }

    @GetMapping("/{idColeta}/{idItem}")
    public ResponseEntity<ColetaItem> buscarPorId(
            @PathVariable Long idColeta,
            @PathVariable Long idItem
    ) {
        return coletaItemService.buscarPorId(idColeta, idItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ColetaItem> cadastrar(@RequestBody ColetaItem coletaItem) {
        ColetaItem coletaItemSalvo = coletaItemService.salvar(coletaItem);
        return ResponseEntity.ok(coletaItemSalvo);
    }

    @DeleteMapping("/{idColeta}/{idItem}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long idColeta,
            @PathVariable Long idItem
    ) {
        coletaItemService.deletar(idColeta, idItem);
        return ResponseEntity.noContent().build();
    }
}