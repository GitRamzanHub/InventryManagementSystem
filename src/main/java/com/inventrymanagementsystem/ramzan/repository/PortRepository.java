package com.inventrymanagementsystem.ramzan.repository;

import com.inventrymanagementsystem.ramzan.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends JpaRepository<Port, Long> {
}
