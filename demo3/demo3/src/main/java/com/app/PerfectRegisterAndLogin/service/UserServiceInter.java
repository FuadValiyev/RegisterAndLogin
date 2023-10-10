package com.app.PerfectRegisterAndLogin.service;

import com.app.PerfectRegisterAndLogin.dto.UserDto;
import com.app.PerfectRegisterAndLogin.entity.User;

import java.util.List;

public interface UserServiceInter {
//    void saveUser(@Valid UserDto userDto);
    void saveUser(UserDto userDto);
    User findByEmail(String email);
    List<UserDto> findAllUsers();
}
