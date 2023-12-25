package com.example.project3.Models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Введите псевдоним автора")
    @Length(min = 2)
    private String  alias;

    public Author() {}

    public Author(String alias) {
        this.alias = alias;
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
