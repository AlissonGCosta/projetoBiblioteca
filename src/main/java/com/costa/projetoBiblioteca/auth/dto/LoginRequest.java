package com.costa.projetoBiblioteca.auth.dto;

public record LoginRequest(
        String email,
        String senha
) {
}
