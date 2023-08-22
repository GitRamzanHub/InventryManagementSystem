package com.inventrymanagementsystem.ramzan.service;

import com.inventrymanagementsystem.ramzan.exception.UserNotFoundException;
import com.inventrymanagementsystem.ramzan.model.User;
import com.inventrymanagementsystem.ramzan.repository.UserRepository;
import com.inventrymanagementsystem.ramzan.resource.UserResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    // save user
    public UserResource saveUser(UserResource userResource){

        // check if user is already exist in DB with username;
        if (userRepository.existsByUsername(userResource.getUsername())) {
            return userResource;
        }

        User user = User.toUserEntity(userResource);
        user = userRepository.save(user);

        log.info("Added New User"+user.toString());

        return User.toUserResource(user);

    }

    // update user
    public UserResource updatedUser(Long userId, UserResource userResource) throws UserNotFoundException{
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with userId: "+userId));
        existingUser.setUsername(userResource.getUsername());
        existingUser.setEmail(userResource.getEmail());
        existingUser.setPassword(userResource.getPassword());
        existingUser.setRole(userResource.getRole());

        User user = userRepository.save(existingUser);

        return User.toUserResource(user);
    }

    // get user by user id
    public UserResource getUserById(Long userId) throws UserNotFoundException{
        Optional<User> dbUser = userRepository.findById(userId);
        if (dbUser.isPresent()){
            User user = dbUser.get();
            return User.toUserResource(user);
        }else{
            throw new UserNotFoundException("User not found with userId: "+userId);
        }
    }
}
