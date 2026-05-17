package com.example.smartcollector.dto;

import java.time.LocalDateTime;

public record ColetaResponse(
        Long idColeta,
        LocalDateTime data,
        Long catadorId,
        Long descartadorId,
        Long centroColetaId,
        Boolean foiFinalizada
) {}