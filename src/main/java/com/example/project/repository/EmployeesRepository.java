package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Employee;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long> {
    
}
