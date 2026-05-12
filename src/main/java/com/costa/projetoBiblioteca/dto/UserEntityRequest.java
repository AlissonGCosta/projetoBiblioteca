package com.costa.projetoBiblioteca.dto;

import jakarta.annotation.Nullable;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityRequest {

    @Nullable
    private String nome;

    @Nullable
    private String email;

}
