package com.example.service.contracts;

import com.example.database.dto.RegisterUserDto;
import com.example.database.entity.User;

import java.util.List;

public interface IUserService {
    User register(RegisterUserDto userDto) throws Exception;
    List<User> getAllUsers();
    User findByName(String name);
    User findById(Long id);
    void deleteById(Long id);
}
