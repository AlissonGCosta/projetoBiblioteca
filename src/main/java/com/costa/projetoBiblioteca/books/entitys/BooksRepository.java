package com.costa.projetoBiblioteca.books.entitys;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BooksRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findByTitle(String title);
}
