package com.inventrymanagementsystem.ramzan.controller;

import com.inventrymanagementsystem.ramzan.dto.UserDTO;
import com.inventrymanagementsystem.ramzan.model.User;
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
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.addUserWithEncodedPassword(userDTO));
    }

    // Update Existing User
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updatedUser(userId, userDTO), HttpStatus.OK);
    }

    // Get User by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.FOUND);
    }

    // deleting user only with ADMIN access
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        UserDTO userDTO = userService.getUserById(userId);
        userService.deleteUser(userId);
        return new ResponseEntity<>("User: "+userDTO.getUsername()+" is deleted from Database.", HttpStatus.OK);
    }

    // getting list of user with ADMIN Access
    @GetMapping("/allUsers")
    public Iterable<User> getAllUser(){
        return userService.findAllUser();
    }
}
