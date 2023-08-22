package com.inventrymanagementsystem.ramzan.repository;

import com.inventrymanagementsystem.ramzan.model.Bags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagsRepository extends JpaRepository<Bags, Long> {
}
