package com.costa.projetoBiblioteca.user.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto{

    private UUID id;
    private String nome;
    private String email;
}
