package com.example.service;

import com.example.aspect.Loggable;
import com.example.database.dto.UserDto;
import com.example.database.entity.User;
import com.example.database.repository.RoleRepository;
import com.example.database.repository.UserRepository;
import com.example.exceptions.IncorrectPasswordException;
import com.example.exceptions.UserNameNotFoundException;
import com.example.service.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Loggable
    public User login(UserDto userDto) throws UserNameNotFoundException, IncorrectPasswordException {
        User user = Mapper.map(userDto, User.class);

        user.setUsername(user.getUsername().toLowerCase());
        User possibleUser = userRepository
                .findUserByUsername(userDto
                        .getUsername())
                .orElseThrow(()->new UserNameNotFoundException("User not found"));

        if(!possibleUser
                .getPassword()
                .equals(userDto
                        .getPassword())) throw new IncorrectPasswordException("Incorrect password");

        return user;
    }

    @Override
    @Loggable
    public void register(UserDto userDto) throws Exception {
        if(userRepository
                .findUserByUsername(userDto.getUsername())
                .isPresent()) throw new Exception("User has already registered");

        if(!userDto
                .getPassword()
                .equals(userDto
                        .getRepeatPassword())){
            throw new Exception("Passwords are not equal");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        user.setRole(roleRepository.getRoleByName("USER"));

        userRepository.save(user);
    }

    @Override
    @Loggable
    public boolean isAdmin(String username) {
        return userRepository.findUserByUsername(username)
                .get()
                .getRole()
                .getName()
                .equals("ADMIN");
    }

    @Override
    @Loggable
    public User getUserByUsername(String username) {
        return userRepository
                .findUserByUsername(username)
                .get();
    }
}
