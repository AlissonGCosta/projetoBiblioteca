package com.costa.projetoBiblioteca.books.entitys;


import com.costa.projetoBiblioteca.author.entitys.AuthorEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "books")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column
    private boolean available;

    @Column(nullable = false)
    private String sinopse;

}
