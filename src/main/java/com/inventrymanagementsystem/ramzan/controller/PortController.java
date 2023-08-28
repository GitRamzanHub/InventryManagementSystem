package com.inventrymanagementsystem.ramzan.controller;

import com.inventrymanagementsystem.ramzan.dto.PortDTO;
import com.inventrymanagementsystem.ramzan.model.Port;
import com.inventrymanagementsystem.ramzan.resource.PortResource;
import com.inventrymanagementsystem.ramzan.service.PortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/port")
public class PortController {
    @Autowired
    private PortService portService;

    // Add new port
    @PostMapping("/add")
    public ResponseEntity<PortDTO> addNewPort(@RequestBody PortDTO portDTO){
        return new ResponseEntity<>(portService.addNewPort(portDTO), HttpStatus.OK);
    }
}
