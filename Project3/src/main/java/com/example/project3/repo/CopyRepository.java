package com.example.project3.repo;

import com.example.project3.Models.Author;
import com.example.project3.Models.Book;
import com.example.project3.Models.Copy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CopyRepository extends CrudRepository<Copy, Long> {
    Copy findCopyByIdEquals(Long id);
}
