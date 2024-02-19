package Entities.DTOs;

import Entities.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private long userId;
    @NotNull
    private Date dueDate;
}
