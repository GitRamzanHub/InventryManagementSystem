package com.inventrymanagementsystem.ramzan.controller;

import com.inventrymanagementsystem.ramzan.model.User;
import com.inventrymanagementsystem.ramzan.service.CustomUserDetails;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/home")
    public String adminUser(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String myRole = userDetails.getAuthorities().toString();
        return "Welcome "+userDetails.getUsername().toUpperCase()+"!"+" Role: "+myRole;

    }
}
