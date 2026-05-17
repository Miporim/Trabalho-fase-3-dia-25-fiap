package com.example.smartcollector.dto;

public record CatadorResponse(
        Long id,
        Long usuarioId,
        Double capacidadeVolumeTotal
) {}