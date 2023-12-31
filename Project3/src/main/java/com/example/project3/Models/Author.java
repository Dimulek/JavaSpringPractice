package com.example.project3.Models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

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

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

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
