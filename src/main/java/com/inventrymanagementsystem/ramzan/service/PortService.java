package com.inventrymanagementsystem.ramzan.service;

import com.inventrymanagementsystem.ramzan.dto.PortDTO;
import com.inventrymanagementsystem.ramzan.model.Port;
import com.inventrymanagementsystem.ramzan.repository.PortRepository;
import com.inventrymanagementsystem.ramzan.resource.PortResource;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ToString
public class PortService {

    @Autowired
    private PortRepository portRepository;


    // create new port
    public PortDTO addNewPort(PortDTO portDTO){

        Port port = Port.toPortEntity(portDTO);
        port = portRepository.save(port);

        PortDTO savedPortDTO = Port.toPortDto(port);

        log.info("Added New Port"+port.toString());
        return savedPortDTO;

    }

    public Port findPort(Long portId) {
        return portRepository.findById(portId).get();
    }
}
