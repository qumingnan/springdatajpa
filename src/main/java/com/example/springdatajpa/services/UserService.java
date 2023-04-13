package com.example.springdatajpa.services;

import com.example.springdatajpa.entities.Classes;
import com.example.springdatajpa.entities.User;
import com.example.springdatajpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void test() {
        System.out.println(this.userRepository.getClass().getCanonicalName());
    }
    public User getUserById(int id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return User.builder().build();
        }
    }

    public void createUser(User user) {
        this.userRepository.save(user);
    }

    public User getUserByName(String name) {
        return this.userRepository.findByName(name);
    }
}
