package com.costa.projetoBiblioteca.author.services;

import com.costa.projetoBiblioteca.author.dto.AuthorRequestDto;
import com.costa.projetoBiblioteca.author.dto.AuthorResponseDto;
import com.costa.projetoBiblioteca.author.entitys.AuthorEntity;
import com.costa.projetoBiblioteca.author.entitys.AuthorRepository;
import com.costa.projetoBiblioteca.exception.BadRequestExcetpiton;
import com.costa.projetoBiblioteca.exception.ConflictEcxecption;
import com.costa.projetoBiblioteca.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServices {

    private final AuthorRepository authorRepository;

    //criando o autor
    public void createAuthor( AuthorRequestDto dto){

        //validação tanto do nome quanto a data de nascimento do author

        if(dto.getName() == null || dto.getName().isEmpty()){
            throw new BadRequestExcetpiton("O nome é obrigatorio");
        }

        if(dto.getDataNascimento() == null || dto.getDataNascimento().isEmpty()){
            throw new BadRequestExcetpiton("A data de nascimento é obrigatoria");
        }


        //verificando se ja tem um autor com esse nome
        if(authorRepository.findByName(dto.getName()).isPresent()){
           throw new ConflictEcxecption("Esse Autor ja foi cadastrado");
        }

         authorRepository.save(
                AuthorEntity.builder()
                        .name(dto.getName())
                        .dataNascimento(dto.getDataNascimento())
                        .build()
        );
    }

    // listando todos os autores
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

    //delete simples para admin ou para testes
    public void deleteAuthor(UUID id){

        if(!authorRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException("Autor n existente");
        }

        authorRepository.deleteById(id);
    }

    //lista usuarios por id
    public AuthorResponseDto findAuthorById(UUID id){

        AuthorEntity a = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor n encontrado"));

        return new AuthorResponseDto(
                a.getId(),
                a.getName(),
                a.getDataNascimento(),
                a.getBooks()
        );
    }

    //Altera Todos ou em partes os atributos do author

    public void alterarAuthorById(UUID id, AuthorRequestDto dto){

       AuthorEntity novoAuthor  =   authorRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("Autor não cadastrado"));


        novoAuthor.setName(dto.getName());
        novoAuthor.setDataNascimento(dto.getDataNascimento());

        authorRepository.save(novoAuthor);
    }
}
