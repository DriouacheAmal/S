package Services;

import Entities.DTOs.UserRequestDto;
import Entities.DTOs.UserResponseDto;
import Repository.UserRepository;
import Utils.MappingProfile;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import lombok.AllArgsConstructor;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {


    @Autowired
    private final UserRepos userRepos;
    public List<UserResponseDto> getAllUsers () {
        return userRepos.findAll()
                .stream()
                .map(MappingProfile::mapToUserDto).toList();
    }

    public UserResponseDto createUser (UserRequestDto userDto){
        var user = MappingProfile.mapToUserEntity(userDto);
        return MappingProfile.mapToUserDto(userRepo.save(user));
    }

    public Object getUserById (Long id) throws Exception {
        var user = userRepo.findById(id).orElseThrow(() -> new Exception("User not found"));
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

    public UserResponseDto addUser (UserRequestDto userDto){
        var user = MappingProfile.mapToUserEntity(userDto);
        return MappingProfile.mapToUserDto(userRepo.save(user));
    }

    public UserResponseDto updateUser (Long id, UserRequestDto userDto) throws Exception {
        var user = userRepo.findById(id).orElseThrow(() -> new Exception("User not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return MappingProfile.mapToUserDto(userRepo.save(user));
    }

    //
    @Transactional
    public void createUser (User user){
        try {
            userRepsitory.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("User with this email already exists", e);
        } catch (ValidationException e) {
            throw new InvalidDataException("Invalid user data", e);
        } catch (Exception e) {
            throw new CustomException("An unexpected error occurred", e);
        }

    }


}




