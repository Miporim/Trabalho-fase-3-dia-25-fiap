package com.example.smartcollector.dto;

import com.example.smartcollector.model.UsuarioRole;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        UsuarioRole funcao
) {}