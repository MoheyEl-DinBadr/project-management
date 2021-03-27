package com.mohey.pma.controllers;

import com.mohey.pma.entities.Employee;
import com.mohey.pma.entities.Project;
import com.mohey.pma.service.EmployeeService;
import com.mohey.pma.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 15, 2021
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProjectService projectService;

    @GetMapping(value = "/")
    public String displayEmployees(Model model){
        model.addAttribute("employees", employeeService.getAll());
        return "employees/list-employees";
    }

    @GetMapping(value = "/new")
    public String displayEmployeeForm(Model model){
        Employee anEmployee = new Employee();
        model.addAttribute("employee", anEmployee);
        List<Project> projects = projectService.getAll();
        model.addAttribute("allProjects", projects);
        return "employees/new-employee";
    }

    @PostMapping(value = "/save")
    public String createEmployee(Employee employee){
        //Save Employee to Database
        employeeService.save(employee);

        return "redirect:/employees/";
    }
}
