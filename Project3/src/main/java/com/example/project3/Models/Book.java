package com.example.project3.Models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Введите название книги")
    @Length(min = 2)
    private String name;

    @Column(columnDefinition="TEXT")
    @NotBlank(message = "Введите описсание книги")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    private Author author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (name="Book_Genre",
            joinColumns=@JoinColumn (name="book_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="genre_id", referencedColumnName="id"))
    private Set<Genre> genres = new HashSet<>();

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Author getAuthor() {
        return author;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
    public void addGenres(Genre genre) {
        this.genres.add(genre);
    }
    public void deleteGenres(Long id) {
        this.genres.removeIf(g -> g.getId() == id);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
