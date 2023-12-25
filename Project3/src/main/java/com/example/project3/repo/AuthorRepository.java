package com.example.project3.repo;

import com.example.project3.Models.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> findAuthorsByAliasContains(String alias);
    Author findAuthorByIdEquals(Long id);
    Author findAuthorByAliasEquals(String name);
}
