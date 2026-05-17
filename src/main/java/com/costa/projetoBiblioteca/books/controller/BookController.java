package com.costa.projetoBiblioteca.books.controller;


import com.costa.projetoBiblioteca.books.dto.BookRequestDto;
import com.costa.projetoBiblioteca.books.dto.BookResponseDto;
import com.costa.projetoBiblioteca.books.services.BooksService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDto getBookById(@PathVariable UUID id) {
        return  booksService.findBookbyId(id);
    }

    @PutMapping("/{id}/livros")
    @ResponseStatus(HttpStatus.CREATED)
    public void putBook(@PathVariable UUID id, @RequestBody @Valid BookRequestDto dto) {
        booksService.putBook(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable UUID id) {
        booksService.deleteBookbyId(id);
    }
}
