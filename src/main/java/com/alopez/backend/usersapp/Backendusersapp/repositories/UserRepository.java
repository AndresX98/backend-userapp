package com.alopez.backend.usersapp.Backendusersapp.repositories;

import com.alopez.backend.usersapp.Backendusersapp.models.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository
        extends CrudRepository<User, Long>{

            Optional<User> findByUsername(String username);

}
