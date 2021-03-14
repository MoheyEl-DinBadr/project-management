package com.mohey.pma.controllers;

import com.mohey.pma.dao.ProjectRepository;
import com.mohey.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 15, 2021
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping("/")
    public String displayProjects(){
        //Get projects from database to display
        return "";
    }

    @GetMapping(value = "/new")
    public String displayProjectForm(Model model){
        Project aProject = new Project();
        model.addAttribute("project", aProject);
        return "new-project";
    }

    /*@RequestMapping("/createProject")
    public String createProject(Model model){
        model.addAttribute("project", new Project());
        return "new-project";
    }*/

    @PostMapping(value = "/save")
    public String createProject(Project project/*, RedirectAttributes attr*/){
        //Save Project to Database
        projectRepository.save(project);
        //attr.addFlashAttribute("project", project);
        //use a redirect to prevent duplicate submissions
        return "redirect:/projects/new";
    }

    @GetMapping(value = "/displayProject")
    public String displayProject(Project project){
        return "view-project";
    }
}
