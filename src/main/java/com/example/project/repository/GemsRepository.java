package com.example.project.repository;

import com.example.project.entity.Gem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GemsRepository extends JpaRepository<Gem, Long> {

}
