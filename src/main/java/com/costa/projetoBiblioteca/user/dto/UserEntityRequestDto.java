package com.costa.projetoBiblioteca.user.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class UserEntityRequestDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

}
