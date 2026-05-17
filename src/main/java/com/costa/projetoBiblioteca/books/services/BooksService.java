package com.costa.projetoBiblioteca.books.services;


import com.costa.projetoBiblioteca.author.entitys.AuthorEntity;
import com.costa.projetoBiblioteca.author.entitys.AuthorRepository;
import com.costa.projetoBiblioteca.books.dto.BookRequestDto;
import com.costa.projetoBiblioteca.books.dto.BookResponseDto;
import com.costa.projetoBiblioteca.books.entitys.BookEntity;
import com.costa.projetoBiblioteca.books.entitys.BooksRepository;
import com.costa.projetoBiblioteca.exception.BadRequestExcetpiton;
import com.costa.projetoBiblioteca.exception.ConflictEcxecption;
import com.costa.projetoBiblioteca.exception.ResourceNotFoundException;
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
            throw new ConflictEcxecption("Livro ja cadastrado");
        }

        //validação do titulo
        if(dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw  new BadRequestExcetpiton("O titulo é obrigatorio");
        }

        //validação do autor
        String nomeAuthor = dto.getAuthor().trim();

        if(nomeAuthor.isEmpty() || nomeAuthor == null) {
            throw new BadRequestExcetpiton("O Autor é obrigatorio");
        }

        //checagem se um author desse livro já existe em banco
        AuthorEntity author = authorRepository.findByName(nomeAuthor)
                .orElseThrow(() -> new ResourceNotFoundException("Crie um Autor antes de cadastrar um livro"));

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
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));

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
                .orElseThrow(() -> new ResourceNotFoundException("livro não encontrado"));


        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthorEntity().getName(),
                book.getPrefacio(),
                book.isAvailable()
        );
    }


    // altera o produto por completo ou grande parte dos atributos
    public void putBook(BookRequestDto dto, UUID id) {


      BookEntity book =  booksRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Livro não cadastrado"));


        AuthorEntity author = authorRepository.findByName(dto.getAuthor())
                .orElseThrow(() -> new ResourceNotFoundException("Autor não cadastrado, cadastreo para adcionar o livro a ele"));

                book.setTitle(dto.getTitle());
                book.setAuthorEntity(author);
                book.setPrefacio(dto.getPrefacio());


        booksRepository.save(book);
    }

    // um delete simples
    public void deleteBookbyId(UUID uuid) {
        booksRepository.findById(uuid)
                .ifPresent(booksRepository::delete);
    }
}
