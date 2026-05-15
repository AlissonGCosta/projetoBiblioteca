package com.costa.projetoBiblioteca.author.controller;

import com.costa.projetoBiblioteca.author.dto.AuthorRequestDto;
import com.costa.projetoBiblioteca.author.dto.AuthorResponseDto;
import com.costa.projetoBiblioteca.author.entitys.AuthorRepository;
import com.costa.projetoBiblioteca.author.services.AuthorServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/autores")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServices authorServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAutor(@RequestBody @Valid AuthorRequestDto dto){
        authorServices.createAuthor(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorResponseDto> listarAutores(){
        return authorServices.listarAutores();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAutor(@PathVariable UUID id){
        authorServices.deleteAuthor(id);
    }

}
