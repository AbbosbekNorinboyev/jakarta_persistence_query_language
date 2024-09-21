package com.example.lesson8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String full_name;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "author"
    )
    private Set<Article> articles;
}
