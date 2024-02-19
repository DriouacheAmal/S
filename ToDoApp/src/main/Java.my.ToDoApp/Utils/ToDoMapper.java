package Utils;

import Entities.DTOs.UserRequestDto;
import Entities.DTOs.UserResponseDto;
import com.google.common.base.Converter;
import Entities.DTOs.TaskRequestDto;
import Entities.DTOs.TaskResponseDto;
import org.h2.engine.User;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.scheduling.config.Task;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

public class MappingProfile {
    private final ModelMapper modelMapper;
    public MappingProfile(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureMappings();
    }
    public static Task mapToEntity(TaskRequestDto taskDto) {
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
                destination.setId(Long.valueOf(source.getId()));
                destination.setEmail(source.getuserEmail());
                destination.setFullName(source.getuserFirstName() + " " + source.getuserLastName());
                return destination;
            }
        };
        modelMapper.addConverter(userToUserResponseDtoConverter);
    }


}
