package Controllers;

import Entities.DTOs.TaskRequestDto;
import Entities.DTOs.TaskResponseDto;
import Services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.oracle.graal.compiler.enterprise.phases.FrameStateLivenessStatisticsPhase.ex;

@RestController
@Validated
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private Object NotFoundException;
    // add CRUD methods

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            throw new NotFoundException("Task not found with id: " + id);
        }
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@Validated @RequestBody TaskRequestDto taskDto) {
        Task task = taskService.createTask(taskDto);
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            TaskService.deleteTask(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.ok("Task with ID " + id + " deleted successfully");
        }
    }

    //Update
    @PutMapping("/update")
    public ResponseEntity<?> updateTask(@RequestBody TaskRequestDto taskDto) {
        try {
            TaskResponseDto updatedTask = taskService.updateTask(taskDto);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        // Exception handling

        @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
        public ResponseEntity<String> handleNotFoundException (NotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<String> handleValidationException (MethodArgumentNotValidException ex){
            BindingResult result = ex.getBindingResult();
            List<FieldError> fieldErrors = result.getFieldErrors();
            String errorMessage = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        // Custom exceptions
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public class NotFoundException extends RuntimeException {
            public NotFoundException(String message) {
                super(message);
            }
        }
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        class DataNotValidException extends RuntimeException {
            public DataNotValidException(String message) {
                super(message);
            }

        }
    }
}

