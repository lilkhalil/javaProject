package com.example.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import com.example.project.entity.Employee;
import com.example.project.service.EmployeesService;


@Controller
public class EmployeeController {

    private final EmployeesService employeesService;

    public EmployeeController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }
    
    @GetMapping("/employees")
    public String getEmployees(Model model) {
        Iterable<Employee> employees = employeesService.getAll();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return "employees";
    }

    @PostMapping("/employees")
    public String addEmployee(@ModelAttribute("employee") Employee employee) {
        employeesService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeesService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @PutMapping("/employees/{id}")
    public String editEmployee(@PathVariable("id") Long id,
    @RequestParam String name,
    @RequestParam String surname,
    @RequestParam(required = false) String fathername,
    @RequestParam(required = false) String jobTitle
    ) {
        Optional<Employee> employeeOptional = employeesService.getSingle(id);
        Employee employee = employeeOptional.orElseThrow();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setFathername(fathername);
        employee.setJobTitle(jobTitle);
        employeesService.saveEmployee(employee);
        return "redirect:/employees";
    }
}
