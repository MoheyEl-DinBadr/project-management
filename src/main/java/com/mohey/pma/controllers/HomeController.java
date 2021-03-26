package com.mohey.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohey.pma.dao.EmployeeRepository;
import com.mohey.pma.dao.ProjectRepository;
import com.mohey.pma.dto.EmployeeProject;
import com.mohey.pma.dto.ChartData;
import com.mohey.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 16, 2021
 */
@Controller
public class HomeController {

    @Value("${version:0.0.1}")
    String version;

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("versionNumber", version);

        //List<Project> projects = projectRepo.findAll();
        model.addAttribute("projects", projectRepo.findAll());

        List<ChartData> projectData = projectRepo.getProjectStatus();
        //Lets convert projectsData object to JSON format to use in javascript

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);

        model.addAttribute("projectStatusCnt", jsonString);

        List<EmployeeProject> employeeProjectCount = employeeRepo.employeeProjects();
        model.addAttribute("employeesListProjectsCount", employeeProjectCount);
        return "main/home";
    }

}
