package com.example.project3.repo;

import com.example.project3.Models.Author;
import com.example.project3.Models.Genre;
import com.example.project3.Models.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Iterable<Genre> findGenreByNameContains(String name);
    Genre findGenreByIdEquals(Long id);
    Genre findGenreByNameEquals(String name);
}
