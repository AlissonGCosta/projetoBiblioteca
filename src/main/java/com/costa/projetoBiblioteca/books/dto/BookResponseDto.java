package com.costa.projetoBiblioteca.books.dto;

import jakarta.persistence.Column;

import java.util.UUID;

public record BookResponseDto(
         UUID id,
         String title,
         String author,
         String prefacio,
         boolean available
) {

}
