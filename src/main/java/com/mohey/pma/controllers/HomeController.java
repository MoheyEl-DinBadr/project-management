package com.mohey.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohey.pma.dto.EmployeeProject;
import com.mohey.pma.dto.ChartData;
import com.mohey.pma.dto.ProjectTimelineDto;
import com.mohey.pma.entities.Project;
import com.mohey.pma.service.EmployeeService;
import com.mohey.pma.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

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
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public String displayHome(Model model) {


        model.addAttribute("versionNumber", version);

        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);

        List<ChartData> projectData = projectService.getProjectStatus();
        //Lets convert projectsData object to JSON format to use in javascript


        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(projectData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("projectStatusCnt", jsonString);

        List<EmployeeProject> employeeProjectCount = employeeService.employeeProjects();
        model.addAttribute("employeesListProjectsCount", employeeProjectCount);
        return "main/home";
    }

    @GetMapping("/timeline")
    public String displayTimeline(Model model){

        List<ProjectTimelineDto> projectTimeline = projectService.getProjectsTimeline();
        Field[] fields = projectTimeline.get(0).getClass().getFields();
        System.out.println("Arrays.asList(fields) = " + Arrays.asList(fields));

        String projectsJson = null;
        try {
            projectsJson = objectMapper.writeValueAsString(projectTimeline);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("projectsJson", projectsJson);

        return "projects/projects-timeline";
    }

}
