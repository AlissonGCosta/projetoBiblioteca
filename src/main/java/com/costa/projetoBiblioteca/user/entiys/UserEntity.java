package com.costa.projetoBiblioteca.user.entiys;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Nullable
    private String nome;

    @Column(nullable = false, unique = true )
    @Nullable
    private String email;

    @Column(nullable = false)
    @Nullable
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
