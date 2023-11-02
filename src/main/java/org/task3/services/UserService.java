package org.task3.services;

import org.springframework.http.ResponseEntity;
import org.task3.dtos.UserRequestDto;
import org.task3.dtos.UserResponseDto;

import java.util.List;

public interface UserService {
    ResponseEntity<Void> save(UserRequestDto request);

    ResponseEntity<Void> update(Long id, UserRequestDto request);

    ResponseEntity<UserResponseDto> get(Long id);

    List<UserResponseDto> getAll();
}
