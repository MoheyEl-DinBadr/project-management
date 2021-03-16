package com.mohey.pma.controllers;

import com.mohey.pma.dao.EmployeeRepository;
import com.mohey.pma.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 16, 2021
 */
@Controller
public class HomeController {

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping
    public String displayHome(Model model){
        model.addAttribute("projects", projectRepo.findAll());
        model.addAttribute("employees", employeeRepo.findAll());
        return "main/home";
    }

}
