package com.example.To.Do.App.BackEnd.Services;

import com.example.To.Do.App.BackEnd.Entities.Dtos.TaskRequestDto;
import com.example.To.Do.App.BackEnd.Entities.Dtos.TaskResponseDto;
import com.example.To.Do.App.BackEnd.Enum.ExceptionsMessage;
import com.example.To.Do.App.BackEnd.ExceptionHandler.TaskNotFoundException;
import com.example.To.Do.App.BackEnd.ExceptionHandler.UserInputNotValidException;
import com.example.To.Do.App.BackEnd.Mapper.MappingProfile;
import com.example.To.Do.App.BackEnd.Repositories.TaskRepository;
import com.example.To.Do.App.BackEnd.Utils.InputValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    @Override
    public List<TaskResponseDto> getAllTasks(){
        return taskRepository.findAll().stream()
                .map(MappingProfile::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    public TaskResponseDto createTask(TaskRequestDto taskDto) {
        if (!InputValidatorUtil.isValidEmail(taskDto.getStatus()))
            throw new UserInputNotValidException("status is null");
        if (!InputValidatorUtil.isStringEmpty(taskDto.getTitle()))
            throw new UserInputNotValidException("title  is null");
        if (!InputValidatorUtil.isFueDateIsValid(taskDto.getDueDate()))
            throw new UserInputNotValidException("Date is not Valid");
        var task = MappingProfile.mapToEntity(taskDto);
        return MappingProfile.mapToDto(taskRepository.save(task));
    }
    @Override
    public TaskResponseDto getTaskById(Long id) throws TaskNotFoundException {
        var task = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException(ExceptionsMessage.TASK_NOT_FOUND.getMessage()));
        return MappingProfile.mapToDto(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskDto) throws TaskNotFoundException {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        ExceptionsMessage.TASK_NOT_FOUND.getMessage()));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setId(taskDto.getId());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(task.getDueDate());
        return MappingProfile.mapToDto(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) throws TaskNotFoundException {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(ExceptionsMessage.TASK_NOT_FOUND.getMessage()));
        taskRepository.delete(task);
    }

}
