package com.example.smartcollector.dto;

public record DescartadorResponse(
        Long id,
        Long usuarioId,
        String endereco
) {}