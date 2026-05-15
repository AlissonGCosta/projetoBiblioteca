package com.costa.projetoBiblioteca.author.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated

public class AuthorRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String dataNascimento;
}
