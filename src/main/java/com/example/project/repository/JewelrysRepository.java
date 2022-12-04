package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Jewelry;

@Repository
public interface JewelrysRepository extends JpaRepository <Jewelry, Long> {
    
}
