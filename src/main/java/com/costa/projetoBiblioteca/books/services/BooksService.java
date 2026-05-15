package com.costa.projetoBiblioteca.books.services;


import com.costa.projetoBiblioteca.author.entitys.AuthorEntity;
import com.costa.projetoBiblioteca.author.entitys.AuthorRepository;
import com.costa.projetoBiblioteca.books.dto.BookRequestDto;
import com.costa.projetoBiblioteca.books.dto.BookResponseDto;
import com.costa.projetoBiblioteca.books.entitys.BookEntity;
import com.costa.projetoBiblioteca.books.entitys.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BooksService {

    private final BooksRepository booksRepository;
    private final AuthorRepository authorRepository;

    public void createBook(BookRequestDto dto) {

        boolean ativo = true;

        //checagem se o livro ja existe
        if (booksRepository.findByTitle(dto.getTitle()).isPresent()) {
            throw new RuntimeException("Livro ja cadastrado");
        }


        String nomeAuthor = dto.getAuthor().trim();

        //checagem se um autho desse livro ja esta criado
        if(!authorRepository.findByName(nomeAuthor).isPresent()) {
            throw new RuntimeException("Crie um Autor antes de cadastrar o livro");
        }

        //criando o metodo para adcionar no create a um autor ja criado
        BookEntity bookEntity = BookEntity.builder()
                .title(dto.getTitle())
                .author(nomeAuthor)
                .prefacio(dto.getPrefacio())
                .available(ativo)
                .build();

        AuthorEntity authorEntity = authorRepository.findAll()
                .stream()
                .filter(a -> a.getName() != null && a.getName().equalsIgnoreCase(nomeAuthor))
                .findFirst()
                .orElse(null);

        if (authorEntity != null) {
            bookEntity.setAuthor(authorEntity.getName());
            authorEntity.getBooks().add(bookEntity);
        }


        // Salvando o livro na database
        booksRepository.save(bookEntity);
    }

    public List<BookResponseDto> findAllBooks() {

        return booksRepository.findAll()
                .stream()
                .map(book -> new BookResponseDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPrefacio(),
                        book.isAvailable()
                )).toList();
    }

    public BookResponseDto findBookbyTitle(String title) {

        BookEntity book = booksRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrefacio(),
                book.isAvailable()
        );
    }

    public void deleteBookbyTitle(String title) {
        booksRepository.findByTitle(title)
                .ifPresent(book -> booksRepository.delete(book));
    }
}
