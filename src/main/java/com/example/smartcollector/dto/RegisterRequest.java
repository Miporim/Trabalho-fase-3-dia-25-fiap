package com.example.smartcollector.dto;

import com.example.smartcollector.model.UsuarioRole;

public record RegisterRequest(
        String nome,
        String email,
        String senha,
        UsuarioRole funcao
) {}
