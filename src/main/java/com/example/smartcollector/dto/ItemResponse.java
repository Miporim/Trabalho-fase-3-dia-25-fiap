package com.example.smartcollector.dto;

public record ItemResponse(
        Long id,
        String nome,
        Double volume
) {}