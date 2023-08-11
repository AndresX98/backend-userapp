package com.alopez.backend.usersapp.Backendusersapp.services;

import com.alopez.backend.usersapp.Backendusersapp.models.entities.User;
import com.alopez.backend.usersapp.Backendusersapp.models.entities.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(User user);
    Optional<UserDto> update(User user, Long id);

    void remove(Long id);
}
