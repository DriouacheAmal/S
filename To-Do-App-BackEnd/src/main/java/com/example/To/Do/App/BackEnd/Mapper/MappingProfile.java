package com.example.To.Do.App.BackEnd.Mapper;

import com.example.To.Do.App.BackEnd.Entities.Dtos.TaskRequestDto;
import com.example.To.Do.App.BackEnd.Entities.Dtos.TaskResponseDto;
import com.example.To.Do.App.BackEnd.Entities.Dtos.UserRequestDto;
import com.example.To.Do.App.BackEnd.Entities.Dtos.UserResponseDto;
import com.example.To.Do.App.BackEnd.Entities.Task;
import com.example.To.Do.App.BackEnd.Entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.Converter;


public class MappingProfile {
    private static final ModelMapper modelMapper= new ModelMapper();

    public static Task mapToEntity(TaskRequestDto taskDto){
        return modelMapper.map(taskDto, Task.class);

    }
    public static TaskResponseDto mapToDto(Task task) {
        return modelMapper.map(task, TaskResponseDto.class);
    }

    public static User mapToUserEntity(UserRequestDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public static UserResponseDto mapToUserDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    static {
        Converter<User, UserResponseDto> userToUserResponseDtoConverter = new Converter<User, UserResponseDto>() {
            public UserResponseDto convert(MappingContext<User, UserResponseDto> context) {
                User source = context.getSource();
                UserResponseDto destination = new UserResponseDto();
                destination.setId(source.getId());
                destination.setEmail(source.getEmail());
                destination.setFullName(source.getFirstName() + " " + source.getLastName());
                return destination;
            }
        };
        modelMapper.addConverter(userToUserResponseDtoConverter);
    }
}
