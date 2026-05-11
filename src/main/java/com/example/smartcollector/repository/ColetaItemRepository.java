package com.example.smartcollector.repository;

import com.example.smartcollector.model.ColetaItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColetaItemRepository extends JpaRepository<ColetaItem, ColetaItem.ColetaItemId> {
}