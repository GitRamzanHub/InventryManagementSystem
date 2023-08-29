package com.inventrymanagementsystem.ramzan.service;

import com.inventrymanagementsystem.ramzan.dto.BrandDTO;
import com.inventrymanagementsystem.ramzan.model.Brand;
import com.inventrymanagementsystem.ramzan.repository.BrandRepository;
import com.inventrymanagementsystem.ramzan.repository.PortRepository;
import com.inventrymanagementsystem.ramzan.resource.BrandResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PortRepository portRepository;

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

    public BrandDTO addIncomingBrand(BrandDTO incomingBrand, Long portId) {
        // Task is to add the number of bags inside the given brand
        Brand brand = brandRepository.findById(incomingBrand.getBrandId()).get();
        double total_bags = incomingBrand.getNumberOfBags() + brand.getNumberOfBags();

        // set the total Bags inside the brand object
        brand.setNumberOfBags(total_bags);
        // calculate total qty
        double total_qty = total_bags * brand.getBagKg();

        // set the new quantity
        brand.setQuantity(total_qty);

        // set the port again
        brand.setPort(portRepository.findById(portId).get());

        // set the new incoming date
        brand.setIncomingStockDate(new Date());

        brand = brandRepository.save(brand);
        // update the available Qty after adding the bags

        return Brand.toBrandDTO(brand);

    }

    // Delete the Brand By its Id
    public String deleteBrandById(Long brandId) {
        // fetching the brand with given id
        Brand brand = brandRepository.findById(brandId).get();

        // checking if the brand has 0 number of Bags
        if(brand.getNumberOfBags() == 0){
            brandRepository.delete(brand);
            return new String(brand.getBrandName()+" Brand has been Deleted from the system.");
        }else{
            return new String(brand.getBrandName()+" Brand has the "+brand.getNumberOfBags()+" bags available in the system, can't delete");

        }


    }
}
