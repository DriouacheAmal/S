package Services;

import Entities.DTOs.TaskRequestDto;
import Entities.DTOs.TaskResponseDto;
import Repository.TaskRepository;
import Utils.MappingProfile;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepo;

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepo.findAll().stream()
                .map(MappingProfile::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public TaskResponseDto createTask(TaskRequestDto taskDto) {
        var task = MappingProfile.mapToEntity(taskDto);
        task.setStatus(Status.NOTDONE);
        return MappingProfile.mapToDto(taskRepo.save(task));
    }

    @Override
    public TaskResponseDto getTaskById(Long id) throws Exception {
        var task = taskRepo.findById(id).orElseThrow(() -> new Exception("Task not found"));
        return MappingProfile.mapToDto(task);
    }

    @Override
    public TaskResponseDto updateTask(TaskRequestDto taskDto) throws Exception {
        return null;
    }

    @Override
    public TaskResponseDto updateTask(Long id, @NotNull TaskRequestDto taskDto) throws Exception {
        var task = taskRepo.findById(id).orElseThrow(() -> new Exception("Task not found"));
        task.setTitle(taskDto.getTitle()); // SET MODIFY VARIABLE //GET VARIABLE (TO MODIFY/ READ)
        task.setDescription(taskDto.getDescription());
        task.setId(taskDto.getId());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(task.getDueDate());
        return MappingProfile.mapToDto(taskRepo.save(task));
    }
    @Override
    public void deleteTask(Long id) throws Exception {
        var task = taskRepo.findById(id).orElseThrow(() -> new Exception("Task not found"));
        taskRepo.delete(task);
    }


}
