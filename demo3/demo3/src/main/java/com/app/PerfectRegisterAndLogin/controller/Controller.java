package com.app.PerfectRegisterAndLogin.controller;

import com.app.PerfectRegisterAndLogin.dto.UserDto;
import com.app.PerfectRegisterAndLogin.entity.User;
import com.app.PerfectRegisterAndLogin.service.UserServiceInter;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    private UserServiceInter userServiceInter;
    public Controller(UserServiceInter userService) {
        this.userServiceInter = userService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model){
        User existing = userServiceInter.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Already an account registered with that email");
        } if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userServiceInter.saveUser(user);
        return "redirect:/register?success";
    }
    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userServiceInter.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
