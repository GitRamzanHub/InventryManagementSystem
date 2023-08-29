package com.inventrymanagementsystem.ramzan.service;

import com.inventrymanagementsystem.ramzan.dto.UserDTO;
import com.inventrymanagementsystem.ramzan.exception.UserNotFoundException;
import com.inventrymanagementsystem.ramzan.model.User;
import com.inventrymanagementsystem.ramzan.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    // save user
    public UserDTO addUserWithEncodedPassword(UserDTO userDTO){
        // check if user is already exist in DB with username;
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return userDTO;
        }

        User user = User.toUserEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        log.info("Added New User"+user.toString());
        return User.toUserDTO(user);
    }


    // update user
    public UserDTO updatedUser(Long userId, UserDTO userDTO) throws UserNotFoundException{
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with userId: "+userId));
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRole(userDTO.getRole());

        User user = userRepository.save(existingUser);

        return User.toUserDTO(user);
    }

    // get user by user id
    public UserDTO getUserById(Long userId) throws UserNotFoundException{
        Optional<User> dbUser = userRepository.findById(userId);
        if (dbUser.isPresent()){
            User user = dbUser.get();
            return User.toUserDTO(user);
        }else{
            throw new UserNotFoundException("User not found with userId: "+userId);
        }
    }

    // delete user by user id
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    // Get all the user from DB
    public Iterable<User> findAllUser(){
        return userRepository.findAll();
    }
}
