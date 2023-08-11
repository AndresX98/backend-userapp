package com.alopez.backend.usersapp.Backendusersapp.repositories;

import com.alopez.backend.usersapp.Backendusersapp.models.entities.Role;
import com.alopez.backend.usersapp.Backendusersapp.models.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository
        extends CrudRepository<Role, Long>{
        Optional<Role> findByName(String name);

}
