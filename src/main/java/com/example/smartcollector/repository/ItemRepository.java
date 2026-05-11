package com.example.smartcollector.repository;

import com.example.smartcollector.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}