package com.example.project.service;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.entity.Employee;
import com.example.project.repository.EmployeesRepository;


@Service
public class EmployeesService {
    
    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public boolean saveEmployee(Employee employee) {
        if (employee.getFathername().equals("")) {
            employee.setFathername("Undefined");
        }
        if (employee.getJobTitle().equals("")) {
            employee.setJobTitle("Undefined");
        }
        employeesRepository.save(employee);
        return true;
    }

    public boolean deleteEmployee(Long id) {
        employeesRepository.deleteById(id);
        return true;
    }

    public Iterable<Employee> getAll() {
        return employeesRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Employee> getSingle(Long id) {
        return employeesRepository.findById(id);
    }

}
