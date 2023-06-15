package com.example.svaan1.userservice.converter;

import com.example.svaan1.userservice.dto.UserDTO;
import com.example.svaan1.userservice.dto.UserResponse;
import com.example.svaan1.userservice.model.User;

import java.util.List;

public class UserConverter {

    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

     public List<UserResponse> toResponseList(List<User> users) {
        return users.stream().map(this::toResponse).toList();
    }

}
