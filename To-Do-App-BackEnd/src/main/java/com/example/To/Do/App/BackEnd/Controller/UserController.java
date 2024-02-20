package com.example.To.Do.App.BackEnd.Controller;

import com.example.To.Do.App.BackEnd.Entities.Dtos.UserRequestDto;
import com.example.To.Do.App.BackEnd.Entities.Dtos.UserResponseDto;
import com.example.To.Do.App.BackEnd.ExceptionHandler.UserInputNotValidException;
import com.example.To.Do.App.BackEnd.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserService userService;
    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userDto) {
        try{
            UserResponseDto userResponseDto = userService.createUser(userDto);
            return ResponseEntity.ok(userResponseDto);
        }catch (UserInputNotValidException e){
            return ResponseEntity.notFound().build();
        }
    }

}
