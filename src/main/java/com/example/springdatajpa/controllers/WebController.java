package com.example.springdatajpa.controllers;

import com.example.springdatajpa.entities.Classes;
import com.example.springdatajpa.entities.User;
import com.example.springdatajpa.services.ClassesService;
import com.example.springdatajpa.services.UserService;
import com.example.springdatajpa.utils.ReturnData;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.*;

//@Data
@NoArgsConstructor
@RestController
public class WebController {
    private UserService userService;
    private ClassesService classesService;
    private final int pageNum = 5;
    public WebController(UserService userService, ClassesService classesService) {
        System.out.println("==================================");
        this.userService = userService;
        this.classesService = classesService;
    }
    @GetMapping("/classes/creation")
    public void createClasses(@RequestParam String name) {
        this.classesService.createClasses(Classes.builder().name(name).build());
    }
    @PostConstruct
    public void postConstruct() {
        System.out.println("--------------------------------");
    }
    @GetMapping("/user/info")
    public User getUserById(@RequestParam Integer id) {
        return this.userService.getUserById(id);
    }
    @GetMapping("/user/name/info")
    public User getUserByName(@RequestParam String name) {
        return this.userService.getUserByName(name);
    }
    @GetMapping("/user/creation")
    public void createUser(@RequestParam String name, @RequestParam int classes) {
        Classes classesInfo = this.classesService.getClassesById(classes);
        User user = User.builder().name(name).classes(classesInfo).build();
        this.userService.createUser(user);
    }
    @GetMapping("/classes/info")
    public Classes getClassesById(@RequestParam Integer id) {

        return this.classesService.getClassesById(id);
    }
    @GetMapping("/classes/all/info")
    public ReturnData getAllClasses(@RequestParam Integer page) {
        return this.classesService.getAllClasses(page, pageNum);
    }


}
