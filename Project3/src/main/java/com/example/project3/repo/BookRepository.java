package com.example.project3.repo;

import com.example.project3.Models.Author;
import com.example.project3.Models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Iterable<Book> findBooksByNameContains(String name);
    Book findBookByIdEquals(Long id);
}
