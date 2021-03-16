package com.mohey.pma.controllers;

import com.mohey.pma.dao.EmployeeRepository;
import com.mohey.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 15, 2021
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping(value = "/")
    public String displayEmployees(Model model){
        model.addAttribute("employees", employeeRepo.findAll());
        return "employees/list-employees";
    }

    @GetMapping(value = "/new")
    public String displayEmployeeForm(Model model){
        Employee anEmployee = new Employee();
        model.addAttribute("employee", anEmployee);
        return "employees/new-employee";
    }

    @PostMapping(value = "/save")
    public String createEmployee(Employee employee){
        //Save Employee to Database
        employeeRepo.save(employee);

        return "redirect:/employees/";
    }
}
