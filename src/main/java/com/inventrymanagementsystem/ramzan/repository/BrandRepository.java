package com.inventrymanagementsystem.ramzan.repository;

import com.inventrymanagementsystem.ramzan.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
