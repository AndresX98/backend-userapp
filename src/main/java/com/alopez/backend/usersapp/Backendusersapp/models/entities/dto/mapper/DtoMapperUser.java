package com.alopez.backend.usersapp.Backendusersapp.models.entities.dto.mapper;

import com.alopez.backend.usersapp.Backendusersapp.models.entities.User;
import com.alopez.backend.usersapp.Backendusersapp.models.entities.dto.UserDto;

public class DtoMapperUser {


    private User user;
    private DtoMapperUser() {
    }

    public static DtoMapperUser biulder(){
        return  new DtoMapperUser();
    }


    public DtoMapperUser setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDto build(){
        if (user == null){
            throw new RuntimeException("Debe pasar el entity user");
        }
        return new UserDto(this.user.getId(), user.getUsername(), user.getEmail());
    }
}
