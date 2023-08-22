package com.inventrymanagementsystem.ramzan.controller;

import com.inventrymanagementsystem.ramzan.repository.UserRepository;
import com.inventrymanagementsystem.ramzan.resource.UserResource;
import com.inventrymanagementsystem.ramzan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // Create New Users
    @PostMapping("/signup")
    public ResponseEntity<UserResource> addUser(@RequestBody UserResource userResource) {
        return ResponseEntity.ok(userService.saveUser(userResource));
    }

    // Update Existing User
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long userId, @RequestBody UserResource updatedUser) {
        return new ResponseEntity<>(userService.updatedUser(userId, updatedUser), HttpStatus.OK);
    }

    // Get User by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.FOUND);
    }
}
