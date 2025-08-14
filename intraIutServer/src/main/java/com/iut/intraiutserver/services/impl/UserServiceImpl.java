package com.iut.intraiutserver.services.impl;

import com.iut.intraiutserver.entities.User;
import com.iut.intraiutserver.payloads.UserDto;
import com.iut.intraiutserver.repositories.UserRepo;
import com.iut.intraiutserver.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Create user
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        User savedUser = userRepo.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    // Update user
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // update fields
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        // add other fields here...

        User updatedUser = userRepo.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    // Get user by ID
    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return this.modelMapper.map(user, UserDto.class);
    }

    // Get all users
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream()
                .map(user -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    // Delete user
    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        userRepo.delete(user);
    }
}
