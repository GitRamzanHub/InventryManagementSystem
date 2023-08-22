package com.inventrymanagementsystem.ramzan.repository;

import com.inventrymanagementsystem.ramzan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByUsername(String username);
}
