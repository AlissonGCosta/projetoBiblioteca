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
import java.util.UUID;

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
        AuthorEntity author = authorRepository.findByName(nomeAuthor)
                .orElseThrow(() -> new RuntimeException("Crie um Autor antes de cadastrar um livro"));

        //criando o metodo para adcionar no create a um autor ja criado
        BookEntity bookEntity = BookEntity.builder()
                .title(dto.getTitle())
                .prefacio(dto.getPrefacio())
                .available(ativo)
                .authorEntity(author)
                .build();

        // Salvando o livro na database
        booksRepository.save(bookEntity);
    }


    // listando todos os livros
    public List<BookResponseDto> findAllBooks() {

        return booksRepository.findAll()
                .stream()
                .map(book -> new BookResponseDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthorEntity().getName(),
                        book.getPrefacio(),
                        book.isAvailable()
                )).toList();
    }


    // listando os livros por titulo
    public BookResponseDto findBookbyTitle(String title) {

        BookEntity book = booksRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthorEntity().getName(),
                book.getPrefacio(),
                book.isAvailable()
        );
    }


    // listando os livros por id
    public BookResponseDto findBookbyId(UUID id) {

        BookEntity book = booksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("livro não encontrado"));


        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthorEntity().getName(),
                book.getPrefacio(),
                book.isAvailable()
        );
    }


    // alera o produto por completo ou grande parte dos atributos
    public void putBook(BookRequestDto dto, UUID id) {


        if(!booksRepository.findById(id).isPresent()) {
            throw new RuntimeException("Livro não cadastrado");
        }

        AuthorEntity author = authorRepository.findByName(dto.getAuthor())
                .orElseThrow(() -> new RuntimeException("Autor não cadastrado, cadastreo para adcionar o livro a ele"));


                BookEntity book = new BookEntity();
                book.setTitle(dto.getTitle());
                book.setAuthorEntity(author);
                book.setPrefacio(dto.getPrefacio());


        booksRepository.save(book);
    }

    // um delete simples
    public void deleteBookbyTitle(String title) {
        booksRepository.findByTitle(title)
                .ifPresent(booksRepository::delete);
    }
}
