package com.mohey.pma.controllers;

import com.mohey.pma.entities.Employee;
import com.mohey.pma.entities.Project;
import com.mohey.pma.service.EmployeeService;
import com.mohey.pma.service.ProjectService;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
    public String createEmployee(@Valid Employee employee, Errors errors, Model model){
        //Save Employee to Database
        if(errors.hasErrors()){
            model.addAttribute("employee", employee);
            List<Project> projects = projectService.getAll();
            model.addAttribute("allProjects", projects);
            return "employees/new-employee";
        }
        employeeService.saveOrUpdate(employee);

        return "redirect:/employees/";
    }

    @GetMapping(value = "/delete")
    public String deleteEmployee(@Param("id") Long id){
        employeeService.delete(id);
        return "redirect:/employees/";
    }

    @GetMapping(value = "/update")
    public String updateEmployee(@Param("id") Long id, Model model){
        //Save Employee to Database
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        List<Project> projects = projectService.getAll();
        model.addAttribute("allProjects", projects);

        return "employees/new-employee";
    }
}
