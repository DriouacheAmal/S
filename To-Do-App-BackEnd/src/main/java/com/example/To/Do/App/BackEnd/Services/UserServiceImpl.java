package com.example.To.Do.App.BackEnd.Services;

import com.example.To.Do.App.BackEnd.Entities.Dtos.UserRequestDto;
import com.example.To.Do.App.BackEnd.Entities.Dtos.UserResponseDto;
import com.example.To.Do.App.BackEnd.Enum.ExceptionsMessage;
import com.example.To.Do.App.BackEnd.ExceptionHandler.UserInputNotValidException;
import com.example.To.Do.App.BackEnd.ExceptionHandler.UserNotFoundException;
import com.example.To.Do.App.BackEnd.Mapper.MappingProfile;
import com.example.To.Do.App.BackEnd.Repositories.UserRepository;
import com.example.To.Do.App.BackEnd.Utils.InputValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(MappingProfile::mapToUserDto).toList();
    }
    public UserResponseDto createUser(UserRequestDto userDto) {
        if(!InputValidatorUtil.isValidEmail(userDto.getEmail()))
            throw new UserInputNotValidException("Email already exists");
        if(!InputValidatorUtil.isStringEmpty(userDto.getFirstName()))
            throw new UserInputNotValidException("firstName  is null");
        if(!InputValidatorUtil.isStringEmpty(userDto.getLastName()))
            throw new UserInputNotValidException("lastName  is null");
        var user = MappingProfile.mapToUserEntity(userDto);
        return MappingProfile.mapToUserDto(userRepository.save(user));
    }
    public Object getUserById(Long id) throws UserNotFoundException {
        var user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ExceptionsMessage.USER_NOT_FOUND.getMessage()));
        return new Object() {
            public Long id = user.getId();
            public String fullName = user.getLastName().toUpperCase() + ", " + user.getFirstName();
            public String email = user.getEmail();
            public List<Object> tasks = Collections.singletonList(user.getTasks().stream().map(task -> new Object() {
                public Long id = task.getId();
                public String title = task.getTitle();
                public String description = task.getDescription();
                public String status = task.getStatus();
                public String dueDate = task.getDueDate().toString();
                public String createdAt = task.getCreatedAt().toString();
                public String updatedAt = task.getUpdatedAt().toString();
            }).toList());
        };
    }

    public UserResponseDto addUser(UserRequestDto userDto) {
        if(InputValidatorUtil.isValidEmail(userDto.getEmail()))
            throw new UserInputNotValidException("Email already exists");
        if(!InputValidatorUtil.isStringEmpty(userDto.getFirstName()))
            throw new UserInputNotValidException("firstName  is null");
        if(!InputValidatorUtil.isStringEmpty(userDto.getLastName()))
            throw new UserInputNotValidException("lastName  is null");
        var user = MappingProfile.mapToUserEntity(userDto);
        return MappingProfile.mapToUserDto(userRepository.save(user));
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userDto) throws UserNotFoundException {
        var user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ExceptionsMessage.USER_INPUT_NOT_VALID.getMessage()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return MappingProfile.mapToUserDto(userRepository.save(user));
    }


}
