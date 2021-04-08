package com.mohey.pma.api.controllers;

import com.mohey.pma.entities.Project;
import com.mohey.pma.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectService projectService;

    @GetMapping
    public List<Project> getProjects(){
        return projectService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Project getProjectById(@PathVariable Long id){
        return projectService.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@Valid @RequestBody Project project){
        return projectService.save(project);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project updateProject(@Valid @RequestBody Project project){
        return projectService.save(project);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project partialUpdateProject(@PathVariable Long id, @RequestBody Project project){
        project.setProjectId(id);
        return projectService.saveOrUpdate(project);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProjectById(@PathVariable Long id){
        try{
            projectService.deleteById(id);
        }catch (EmptyResultDataAccessException e){

        }

    }
}
