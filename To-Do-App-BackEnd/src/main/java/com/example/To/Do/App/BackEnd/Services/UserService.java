package com.example.To.Do.App.BackEnd.Services;

import com.example.To.Do.App.BackEnd.Entities.Dtos.UserRequestDto;
import com.example.To.Do.App.BackEnd.Entities.Dtos.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto createUser(UserRequestDto userDto);
    public UserResponseDto addUser(UserRequestDto userDto);
    UserResponseDto updateUser(Long id, UserRequestDto userDto) throws Exception;
}
