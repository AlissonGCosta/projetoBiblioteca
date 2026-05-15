package com.costa.projetoBiblioteca.books.controller;


import com.costa.projetoBiblioteca.books.dto.BookRequestDto;
import com.costa.projetoBiblioteca.books.dto.BookResponseDto;
import com.costa.projetoBiblioteca.books.entitys.BookEntity;
import com.costa.projetoBiblioteca.books.services.BooksService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponseDto> getBooks() {
        return booksService.findAllBooks();
    }

    @GetMapping("/{title}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDto getBooksByTitle(@PathVariable String title ) {
        return booksService.findBookbyTitle(title);
    }

    @DeleteMapping("/{title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String title) {
        booksService.deleteBookbyTitle(title);
    }
}
