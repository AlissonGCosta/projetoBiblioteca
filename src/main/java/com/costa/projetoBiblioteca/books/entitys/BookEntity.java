package com.costa.projetoBiblioteca.books.entitys;


import com.costa.projetoBiblioteca.author.entitys.AuthorEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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


    @Column
    private boolean available;

    @Column(nullable = false, length = 10000)
    private String prefacio;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @JsonBackReference
    private AuthorEntity authorEntity;

}
