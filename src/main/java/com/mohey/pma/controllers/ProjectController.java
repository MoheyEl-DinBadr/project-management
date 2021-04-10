package com.mohey.pma.controllers;

import com.mohey.pma.entities.Employee;
import com.mohey.pma.entities.Project;
import com.mohey.pma.service.EmployeeService;
import com.mohey.pma.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String createProject(@Valid Project project, Model model, Errors errors){
        //Save Project to Database
        if(errors.hasErrors()){
            model.addAttribute("project", project);
            List<Employee> employees = employeeService.getAll();
            model.addAttribute("allEmployees", employees);
        }
        projectService.saveOrUpdate(project);

        return "redirect:/projects/";
    }

    @GetMapping(value = "/delete")
    public String deleteProject(@Param("id") Long id){
        projectService.deleteById(id);
        return "redirect:/projects/";
    }

    @GetMapping(value = "/update")
    public String updateEmployee(@Param("id") Long id, Model model){
        //Save Employee to Database
        Project aProject = projectService.findById(id);
        model.addAttribute("project", aProject);
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }

}
