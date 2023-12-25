package com.example.project3.repo;

import com.example.project3.Models.Author;
import com.example.project3.Models.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    Iterable<Publisher> findPublishersByNameContains(String name);
    Publisher findPublisherByIdEquals(Long id);
    Publisher findPublisherByNameEquals(String name);
}
