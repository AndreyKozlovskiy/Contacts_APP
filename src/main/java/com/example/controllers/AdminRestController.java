package com.example.controllers;

import com.example.aspect.Loggable;
import com.example.database.dto.ContactDto;
import com.example.database.dto.RegisterUserDto;
import com.example.database.entity.User;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/admin/")
@AllArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @GetMapping(value = "users/{id}")
    public ResponseEntity<RegisterUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        RegisterUserDto result = RegisterUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Loggable
    @DeleteMapping(value = "{id}")
    public ResponseEntity<RegisterUserDto> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
