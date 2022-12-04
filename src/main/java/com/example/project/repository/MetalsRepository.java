package com.example.project.repository;

import com.example.project.entity.Metal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetalsRepository extends JpaRepository<Metal, Long> {
    
}
