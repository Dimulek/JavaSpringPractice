package com.example.project3.Models;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Введите название жанра")
    @Size(min=2, message = "Название жанра не может быть меньше 2 букв")
    @Size(max=2, message = "Название жанра не может быть больше 100 букв")
    private String name;

    public Genre() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
