package com.example.project3.repo;

import com.example.project3.Models.modelUser;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<modelUser,Long> {
    modelUser findByUsername(String username);
    modelUser findById(long id);
}
