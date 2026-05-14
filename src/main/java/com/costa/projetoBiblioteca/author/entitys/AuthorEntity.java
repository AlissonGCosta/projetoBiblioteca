package com.costa.projetoBiblioteca.author.entitys;

import com.costa.projetoBiblioteca.books.entitys.BookEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "author")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "data_nascimento")
    private String dataNascimenteo;

    @OneToMany(mappedBy = "author")
    @Column(name = "livros")
    private Set<BookEntity> books = new HashSet<>();

}
