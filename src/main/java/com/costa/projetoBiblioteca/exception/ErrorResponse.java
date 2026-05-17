package com.costa.projetoBiblioteca.exception;

public record ErrorResponse(
        String timeStamp,
        int status,
        String error,
        String message,
        String path
) {
}
