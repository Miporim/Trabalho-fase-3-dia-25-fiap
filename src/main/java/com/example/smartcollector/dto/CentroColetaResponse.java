package com.example.smartcollector.dto;

public record CentroColetaResponse(
        Long id,
        String endereco,
        Double volumeItensTotal,
        Double volumeItensAtual
) {}