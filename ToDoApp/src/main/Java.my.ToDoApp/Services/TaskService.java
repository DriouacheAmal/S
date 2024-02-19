package Services;
import Entities.DTOs.TaskRequestDto;
import Entities.DTOs.TaskResponseDto;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskService {
    List<TaskResponseDto> getAllTasks(); //GET LIST FROM DATABASE
    Task createTask(TaskRequestDto taskDto); //ADD NEW TASK
    TaskResponseDto getTaskById(Long id) throws Exception; // GET ONLY 1 TASK AND GIVE IT AN ID
    TaskResponseDto updateTask(TaskRequestDto taskDto) throws Exception; // UPDATE TASK ADD NEW VALUES

    TaskResponseDto updateTask(Long id, TaskRequestDto taskDto) throws Exception;

    void deleteTask(Long id) throws Exception;

}
