package com.alopez.backend.usersapp.Backendusersapp.services;

import com.alopez.backend.usersapp.Backendusersapp.models.entities.Role;
import com.alopez.backend.usersapp.Backendusersapp.models.entities.User;
import com.alopez.backend.usersapp.Backendusersapp.models.entities.dto.UserDto;
import com.alopez.backend.usersapp.Backendusersapp.models.entities.dto.mapper.DtoMapperUser;
import com.alopez.backend.usersapp.Backendusersapp.repositories.RoleRepository;
import com.alopez.backend.usersapp.Backendusersapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplement implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = (List<User>) repository.findAll();

        return users
                .stream()
                .map(user -> DtoMapperUser.biulder().setUser(user).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        return repository.findById(id).map(user -> DtoMapperUser
                .biulder()
                .setUser(user)
                .build());

    }

    @Override
    @Transactional
    public UserDto save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> o = roleRepository.findByName("ROLE_USER");

        List<Role> roles = new ArrayList<>();
        if (o.isPresent()){
            roles.add(o.orElseThrow());
        }
        user.setRoles(roles);

        return DtoMapperUser.biulder().setUser(repository.save(user)).build();
    }

    @Override
    @Transactional
    public Optional<UserDto> update(User user, Long id) {
        Optional<User> o = repository.findById(id);
        User userOptional = null;
        if (o.isPresent()){
            User userDb = o.orElseThrow();
            userDb.setUsername(user.getUsername());
            userDb.setEmail(user.getEmail());
            userOptional = repository.save(userDb);
        }
        return Optional.ofNullable(DtoMapperUser.biulder().setUser(userOptional).build());
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);

    }
}
