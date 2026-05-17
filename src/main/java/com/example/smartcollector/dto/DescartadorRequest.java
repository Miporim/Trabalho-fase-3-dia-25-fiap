package com.example.smartcollector.dto;

public record DescartadorRequest(
        Long usuarioId,
        String endereco
) {}