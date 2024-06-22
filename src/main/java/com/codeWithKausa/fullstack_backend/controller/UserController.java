package com.codeWithKausa.fullstack_backend.controller;

import com.codeWithKausa.fullstack_backend.model.User;
import com.codeWithKausa.fullstack_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User updatedUser){
        //Find existing user by id
        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("user not found with id: "+ id));
        //Update the existing user's fields with new data
        existingUser.setName(updatedUser.getName());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());

        //Save the updated user object
        User  savedUser = userRepository.save(existingUser);

        //Return ResponseEntity with updated user and HTTP status OK
        return ResponseEntity.ok(savedUser);

    }

}
