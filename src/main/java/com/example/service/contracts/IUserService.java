package com.example.service.contracts;

import com.example.database.dto.UserDto;
import com.example.database.entity.User;
import com.example.exceptions.IncorrectPasswordException;
import com.example.exceptions.UserNameNotFoundException;

public interface IUserService {
    User login(UserDto userDto) throws UserNameNotFoundException, IncorrectPasswordException;
    void register(UserDto userDto) throws Exception;
    boolean isAdmin(String username);
    User getUserByUsername(String username);
}