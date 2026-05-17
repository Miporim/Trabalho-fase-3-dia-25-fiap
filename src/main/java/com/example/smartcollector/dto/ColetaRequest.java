package com.example.smartcollector.dto;

import java.time.LocalDateTime;

public record ColetaRequest(
        LocalDateTime data,
        Long catadorId,
        Long descartadorId,
        Long centroColetaId,
        Boolean foiFinalizada
) {}