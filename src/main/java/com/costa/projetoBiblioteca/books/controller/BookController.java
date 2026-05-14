package com.costa.projetoBiblioteca.books.controller;


import com.costa.projetoBiblioteca.books.dto.BookRequestDto;
import com.costa.projetoBiblioteca.books.services.BooksService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/livros")
@RequiredArgsConstructor
public class BookController {

    private final BooksService booksService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody @Valid BookRequestDto dto) {
        booksService.createBook(dto);
    }
}
