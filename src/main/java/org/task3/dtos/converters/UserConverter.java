package org.task3.dtos.converters;


import org.modelmapper.ModelMapper;
import org.task3.infrastructure.entities.User;
import org.task3.dtos.UserRequestDto;
import org.task3.dtos.UserResponseDto;

public final class UserConverter {

    public static User convert(UserRequestDto userRequest, ModelMapper modelMapper) {
        var user = modelMapper.map(userRequest, User.class);
        user.setSubmissionBirthday(userRequest.getBirthday());
        return user;
    }

    public static UserResponseDto convert(User user, ModelMapper modelMapper) {
        var userResponseDto = modelMapper.map(user, UserResponseDto.class);
        userResponseDto.setSubmissionBirthday(user.getBirthday());
        return userResponseDto;
    }

    private UserConverter() {}
}