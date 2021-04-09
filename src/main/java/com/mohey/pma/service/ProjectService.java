package com.mohey.pma.service;

import com.mohey.pma.dao.ProjectRepository;
import com.mohey.pma.dto.ChartData;
import com.mohey.pma.dto.ProjectDto;
import com.mohey.pma.entities.Employee;
import com.mohey.pma.entities.Project;
import org.hibernate.criterion.ProjectionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 27, 2021
 */
@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepo;

    public Project save(Project project){
        return projectRepo.save(project);
    }

    public Project findById(Long id){
        Optional<Project> optionalProject = projectRepo.findById(id);
        if(optionalProject.isPresent()){
            return optionalProject.get();
        }
        return null;
    }

    public Project saveOrUpdate(Project project){
        Optional<Project> optionalProject = projectRepo.findById(project.getProjectId());
        if(optionalProject.isPresent()){
            Project presentProject = optionalProject.get();
            if(project.getDescription() != null){
                presentProject.setDescription(project.getDescription());
            }

            if(project.getName() != null){
                presentProject.setName(project.getName());
            }

            if(project.getStage() != null){
                presentProject.setStage(project.getStage());
            }
        }
        return projectRepo.save(project);
    }

    public void deleteById(Long id){
        projectRepo.deleteById(id);
    }

    public List<Project> getAll(){
        return projectRepo.findAll();
    }

    public List<ChartData> getProjectStatus(){
        return projectRepo.getProjectStatus();
    }
}
