package com.costa.projetoBiblioteca.books.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class BookRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String sinopse;

}
