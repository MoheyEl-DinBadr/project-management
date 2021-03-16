package com.mohey.pma.controllers;

import com.mohey.pma.dao.ProjectRepository;
import com.mohey.pma.entities.Project;
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
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepo;

    @GetMapping(value = "/")
    public String displayProjects(Model model){
        model.addAttribute("projects", projectRepo.findAll());
        return "projects/list-projects";
    }

    @GetMapping(value = "/new")
    public String displayProjectForm(Model model){
        Project aProject = new Project();
        model.addAttribute("project", aProject);
        return "projects/new-project";
    }

    @PostMapping(value = "/save")
    public String createProject(Project project/*, RedirectAttributes attr*/){
        //Save Project to Database
        projectRepo.save(project);

        return "redirect:/projects/";
    }

}
