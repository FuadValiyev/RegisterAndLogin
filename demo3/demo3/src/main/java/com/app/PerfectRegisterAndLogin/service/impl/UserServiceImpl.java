package com.app.PerfectRegisterAndLogin.service.impl;

import com.app.PerfectRegisterAndLogin.dto.UserDto;
import com.app.PerfectRegisterAndLogin.entity.Role;
import com.app.PerfectRegisterAndLogin.entity.User;
import com.app.PerfectRegisterAndLogin.repository.RoleRepositoryInter;
import com.app.PerfectRegisterAndLogin.repository.UserRepositoryInter;
import com.app.PerfectRegisterAndLogin.service.UserServiceInter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServiceInter {

    private UserRepositoryInter userRepositoryInter;
    private RoleRepositoryInter roleRepositoryInter;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryInter userRepository, RoleRepositoryInter roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepositoryInter = userRepository;
        this.roleRepositoryInter = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepositoryInter.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepositoryInter.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepositoryInter.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepositoryInter.findAll();
        return users.stream().map((user) -> convertEntityToDto(user)).collect(Collectors.toList());
    }
    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepositoryInter.save(role);
    }
}
