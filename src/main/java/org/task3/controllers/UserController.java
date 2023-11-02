package org.task3.controllers;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.task3.dtos.UserRequestDto;
import org.task3.dtos.UserResponseDto;
import org.task3.services.UserService;

import java.util.List;

@Tag(name = "Users", description = "User controller")
@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created"),
            @ApiResponse(code = 409, message = "User exists"),
    })
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@Valid @RequestBody
                                           @Parameter(name = "User request") UserRequestDto request) {
        return userService.save(request);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User modified"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable(name = "id") Long id,
                                           @Parameter(name = "User request") @Valid @RequestBody UserRequestDto request) {
        return userService.update(id, request);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResponseDto> getUser(@PathVariable(name = "id") Long id) {
        return userService.get(id);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserResponseDto>> getAll(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                        @RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                        @RequestParam(name = "birthday", required = false) String birthday,
                                                        @RequestParam(name = "name", required = false) String name,
                                                        @RequestParam(name = "surname", required = false) String surname) {
        return new ResponseEntity<>(userService.getAll(pageSize, pageNum, birthday, name, surname), HttpStatus.OK);
    }
}