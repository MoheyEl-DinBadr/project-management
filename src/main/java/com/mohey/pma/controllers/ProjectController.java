package com.mohey.pma.controllers;

import com.mohey.pma.dao.EmployeeRepository;
import com.mohey.pma.dao.ProjectRepository;
import com.mohey.pma.entities.Employee;
import com.mohey.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    ProjectRepository projectRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping(value = "/")
    public String displayProjects(Model model){
        model.addAttribute("projects", projectRepo.findAll());
        return "projects/list-projects";
    }

    @GetMapping(value = "/new")
    public String displayProjectForm(Model model){
        Project aProject = new Project();
        model.addAttribute("project", aProject);
        List<Employee> employees = employeeRepo.findAll();
        model.addAttribute("allEmployees", employees);
        return "projects/new-project";
    }

    @PostMapping(value = "/save")
    public String createProject(Project project, @RequestParam List<Long> _employees){
        //Save Project to Database
        projectRepo.save(project);

        //Here we get _empolyees fron inspect/network section
        Iterable<Employee> chosenEmployees = employeeRepo.findAllById(_employees);
        for (Employee employee: chosenEmployees) {
            employee.setProject(project);
            employeeRepo.save(employee);
        }

        // Also works but we use the upper if the field is not set at the objet
        /*project.getEmpolyees().stream().parallel().forEach(emp-> {
            emp.setProject(project);
            employeeRepo.save(emp);
        });*/

        return "redirect:/projects/";
    }

}
