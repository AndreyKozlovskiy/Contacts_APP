package com.example.service;


import com.example.aspect.Loggable;
import com.example.database.dto.RegisterUserDto;
import com.example.database.entity.Role;
import com.example.database.entity.User;
import com.example.database.repository.RoleRepository;
import com.example.database.repository.UserRepository;

import com.example.service.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Loggable
    public User register(RegisterUserDto userDto) throws Exception {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new Exception("User has already registered");
        }

        if(!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            throw new Exception("Passwords are not equal");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        Role roleUser = roleRepository.findByName("user");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        return userRepository.save(user);
    }

    @Override
    @Loggable
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Loggable
    public User findByName(String name) { return userRepository.findByUsername(name); }

    @Override
    @Loggable
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Loggable
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
