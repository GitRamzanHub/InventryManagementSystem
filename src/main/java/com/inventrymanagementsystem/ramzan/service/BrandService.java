package com.inventrymanagementsystem.ramzan.service;

import com.inventrymanagementsystem.ramzan.dto.BrandDTO;
import com.inventrymanagementsystem.ramzan.model.Brand;
import com.inventrymanagementsystem.ramzan.repository.BrandRepository;
import com.inventrymanagementsystem.ramzan.resource.BrandResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

//    public BrandResource addBrandToPort(BrandResource brandResource){
//        Brand brand = Brand.toBrandEntity(brandResource);
//        brand = brandRepository.save(brand);
//        return Brand.toBrandResources(brand);
//    }

    // list all available brand at port
    public Iterable<BrandDTO> listAllBrand() {
        Iterable<Brand> brands = brandRepository.findAll();

        return StreamSupport.stream(brands.spliterator(), false)
                .map(this::convertToBrandDTO)
                .collect(Collectors.toList());
    }

    private BrandDTO convertToBrandDTO(Brand brand){
        return Brand.toBrandDTO(brand);
    }

    // saving brand object to DB
    public Brand addBrand(Brand newBrand) {
        return brandRepository.save(newBrand);
    }

    // find brand by id
    public Brand findById(Long brandId) {
        return brandRepository.findById(brandId).get();
    }
}
