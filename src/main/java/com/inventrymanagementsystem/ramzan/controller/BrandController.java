package com.inventrymanagementsystem.ramzan.controller;

import com.inventrymanagementsystem.ramzan.dto.BrandDTO;
import com.inventrymanagementsystem.ramzan.exception.PortNotFound;
import com.inventrymanagementsystem.ramzan.exception.UserNotFoundException;
import com.inventrymanagementsystem.ramzan.model.Brand;
import com.inventrymanagementsystem.ramzan.model.Port;
import com.inventrymanagementsystem.ramzan.repository.BrandRepository;
import com.inventrymanagementsystem.ramzan.repository.PortRepository;
import com.inventrymanagementsystem.ramzan.resource.BrandResource;
import com.inventrymanagementsystem.ramzan.service.BrandService;
import com.inventrymanagementsystem.ramzan.service.PortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PortService portService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private PortRepository portRepository;  // tutorial = brand, tab = port


    // Add New Brand
    @PostMapping("/add/{portId}")
    public ResponseEntity<Brand> addNewBrand(@RequestBody BrandDTO brandDTO, @PathVariable Long portId){
        // first get the port with given portId
        Port port = portService.findPort(portId);

        if(ObjectUtils.isEmpty(port)){

            // todo with throwing an exception if port not found with message
            throw new PortNotFound("Port with "+portId+" not found.");
        }

        // second set that port inside the brandDTO object
        brandDTO.setPort(port);

        double numberOfBags = brandDTO.getNumberOfBags();

        double bagsKg = brandDTO.getBagKg();

        double qty = bagsKg * numberOfBags;
        brandDTO.setQuantity(qty);

        // convert brandDTO to Brand Entity
        Brand newBrand = Brand.toBrandEntity(brandDTO);
        newBrand = brandService.addBrand(newBrand);

        BrandDTO savedBrandDTO = Brand.toBrandDTO(newBrand);

        return new ResponseEntity<>(newBrand, HttpStatus.CREATED);
    }

    // list all Brand
    @GetMapping("/listbrand")
    public Iterable<BrandDTO> listAllBrand(){
        Iterable<BrandDTO> brandList = new ArrayList<>();
        brandList = brandService.listAllBrand();
        return brandList;
    }

    // outgoing Stock
    @PutMapping("/outgoing/{portId}")
    public ResponseEntity<BrandDTO> outgoingBrand(@RequestBody BrandDTO brandDTO, @PathVariable Long portId){
        // taking number of bags from brandDTO
        double outgoingBags = brandDTO.getNumberOfBags();
        Long brandId = brandDTO.getBrandId();

        Brand brand = brandService.findById(brandId);

        double dbBrandBags = brand.getNumberOfBags();
        double availableBags = dbBrandBags - outgoingBags;
        brand.setNumberOfBags(availableBags);

        double bagKg = brand.getBagKg();
        double qty = bagKg * availableBags;

        brand.setQuantity(qty);

        Port port = portService.findPort(portId);
         brand.setPort(port);

        // save db_Brand again in DB
        brand = brandService.addBrand(brand);
        brandDTO = Brand.toBrandDTO(brand);
        return new ResponseEntity<>(brandDTO,HttpStatus.OK);
    }

    // adding incoming stock
    // todo with adding incoming stocks
}
