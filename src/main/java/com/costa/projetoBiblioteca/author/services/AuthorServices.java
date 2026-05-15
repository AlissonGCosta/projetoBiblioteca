package com.costa.projetoBiblioteca.author.services;

import com.costa.projetoBiblioteca.author.dto.AuthorRequestDto;
import com.costa.projetoBiblioteca.author.dto.AuthorResponseDto;
import com.costa.projetoBiblioteca.author.entitys.AuthorEntity;
import com.costa.projetoBiblioteca.author.entitys.AuthorRepository;
import com.costa.projetoBiblioteca.books.dto.BookResponseDto;
import com.costa.projetoBiblioteca.books.entitys.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServices {

    private final AuthorRepository authorRepository;

    //criando o autor
    public void createAuthor( AuthorRequestDto dto){

        if(authorRepository.findByName(dto.getName()).isPresent()){
           throw new RuntimeException("Esse Autor ja foi cadastrado");
        }

         authorRepository.save(
                AuthorEntity.builder()
                        .name(dto.getName())
                        .dataNascimento(dto.getDataNascimento())
                        .build()
        );
    }

    public List<AuthorResponseDto> listarAutores(){



        return authorRepository.findAll()
                .stream()
                .map(autores -> new AuthorResponseDto(
                        autores.getId(),
                        autores.getName(),
                        autores.getDataNascimento(),
                        autores.getBooks()

                ))
                .toList();
    }

    public void deleteAuthor(UUID id){

        if(!authorRepository.findById(id).isPresent()){
            throw new RuntimeException("Autor n existente");
        }

        authorRepository.deleteById(id);
    }

}
