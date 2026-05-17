package com.example.smartcollector.dto;

public record CatadorRequest(
        Long usuarioId,
        Double capacidadeVolumeTotal
) {}