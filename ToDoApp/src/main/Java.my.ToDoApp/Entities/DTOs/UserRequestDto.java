package Entities.DTOs;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class UserRequestDto {
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
}
