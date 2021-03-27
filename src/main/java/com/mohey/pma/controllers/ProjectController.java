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
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/")
    public String displayProjects(Model model){
        model.addAttribute("projects", projectService.getAll());
        return "projects/list-projects";
    }

    @GetMapping(value = "/new")
    public String displayProjectForm(Model model){
        Project aProject = new Project();
        model.addAttribute("project", aProject);
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("allEmployees", employees);
        return "projects/new-project";
    }

    @PostMapping(value = "/save")
    public String createProject(Project project){
        //Save Project to Database
        projectService.save(project);

        return "redirect:/projects/";
    }

}
