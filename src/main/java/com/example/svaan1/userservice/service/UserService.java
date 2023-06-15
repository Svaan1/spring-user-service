package com.example.svaan1.userservice.service;

import com.example.svaan1.userservice.converter.UserConverter;
import com.example.svaan1.userservice.dto.UserDTO;
import com.example.svaan1.userservice.dto.UserQueryDTO;
import com.example.svaan1.userservice.dto.UserResponse;
import com.example.svaan1.userservice.exception.UserNotFoundException;
import com.example.svaan1.userservice.model.User;
import com.example.svaan1.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private final UserConverter userConverter;

    public List<UserResponse> query(UserQueryDTO userQueryDTO) {
        return userConverter.toResponseList(userRepository.findAll(userQueryDTO.buildSpecification()));
    }

    public ResponseEntity<?> createUser(UserDTO userDTO) {
        User user = userConverter.toEntity(userDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userRepository.save(user).getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return userConverter.toResponse(user);
    }

    public void updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

