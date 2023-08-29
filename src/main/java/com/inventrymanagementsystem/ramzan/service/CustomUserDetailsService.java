package com.inventrymanagementsystem.ramzan.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.inventrymanagementsystem.ramzan.model.User;
import com.inventrymanagementsystem.ramzan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User with "+username+" not found");
        }
        System.out.println("User"+user.toString());
        return new CustomUserDetails(user);
    }
}
