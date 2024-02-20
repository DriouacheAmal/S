package com.example.To.Do.App.BackEnd.Services;

import com.example.To.Do.App.BackEnd.Entities.Dtos.TaskRequestDto;
import com.example.To.Do.App.BackEnd.Entities.Dtos.TaskResponseDto;
import com.example.To.Do.App.BackEnd.ExceptionHandler.TaskNotFoundException;

import java.util.List;

public interface TaskService {
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto createTask(TaskRequestDto taskDto);
    TaskResponseDto getTaskById(Long id) throws TaskNotFoundException;
    TaskResponseDto updateTask(Long id, TaskRequestDto taskDto) throws TaskNotFoundException;
    void deleteTask(Long id) throws TaskNotFoundException;


}
