package com.iut.intraiutserver.services;

import com.iut.intraiutserver.payloads.UserDto;

import java.util.List;

public interface UserService {


    UserDto registerNewUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}