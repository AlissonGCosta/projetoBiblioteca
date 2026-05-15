package com.costa.projetoBiblioteca.author.dto;

import com.costa.projetoBiblioteca.books.entitys.BookEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record AuthorResponseDto(
        UUID id,
        String name,
        String dataNascimento,
        Set<BookEntity> book

) {
}
