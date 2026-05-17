package com.costa.projetoBiblioteca.exception;

public record ValidationErrorResponse(
        String timeStamp,
        int status,
        String error,
        String message,
        String path,
        java.util.Map<String,String> fieldsErrors
) {
}
