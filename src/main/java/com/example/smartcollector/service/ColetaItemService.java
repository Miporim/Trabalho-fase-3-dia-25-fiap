package com.example.smartcollector.service;

import com.example.smartcollector.model.Coleta;
import com.example.smartcollector.model.ColetaItem;
import com.example.smartcollector.model.Item;
import com.example.smartcollector.repository.ColetaItemRepository;
import com.example.smartcollector.repository.ColetaRepository;
import com.example.smartcollector.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColetaItemService {

    private final ColetaItemRepository coletaItemRepository;
    private final ColetaRepository coletaRepository;
    private final ItemRepository itemRepository;

    public ColetaItemService(
            ColetaItemRepository coletaItemRepository,
            ColetaRepository coletaRepository,
            ItemRepository itemRepository
    ) {
        this.coletaItemRepository = coletaItemRepository;
        this.coletaRepository = coletaRepository;
        this.itemRepository = itemRepository;
    }

    public List<ColetaItem> listarTodos() {
        return coletaItemRepository.findAll();
    }

    public Optional<ColetaItem> buscarPorId(Long idColeta, Long idItem) {
        ColetaItem.ColetaItemId id = new ColetaItem.ColetaItemId(idColeta, idItem);
        return coletaItemRepository.findById(id);
    }

    public ColetaItem salvar(ColetaItem coletaItem) {
        Long idColeta = coletaItem.getIdColeta();
        Long idItem = coletaItem.getIdItem();

        Coleta coleta = coletaRepository.findById(idColeta)
                .orElseThrow(() -> new RuntimeException("Coleta não encontrada"));

        Item item = itemRepository.findById(idItem)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        coletaItem.setColeta(coleta);
        coletaItem.setItem(item);

        return coletaItemRepository.save(coletaItem);
    }

    public void deletar(Long idColeta, Long idItem) {
        ColetaItem.ColetaItemId id = new ColetaItem.ColetaItemId(idColeta, idItem);

        if (!coletaItemRepository.existsById(id)) {
            throw new RuntimeException("Item da coleta não encontrado");
        }

        coletaItemRepository.deleteById(id);
    }
}