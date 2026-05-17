package com.example.smartcollector.dto;

public record CentroColetaRequest(
        String endereco,
        Double volumeItensTotal,
        Double volumeItensAtual
) {}