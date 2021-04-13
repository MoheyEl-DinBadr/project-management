package com.mohey.pma.service;

import com.mohey.pma.dao.ProjectRepository;
import com.mohey.pma.dto.ChartData;
import com.mohey.pma.dto.ProjectTimelineDto;
import com.mohey.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Project> findAll(){
        return projectRepo.findAll();
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

            if(project.getStartDate() != null){
                presentProject.setStartDate(project.getStartDate());
            }

            if(project.getEndDate() != null){
                presentProject.setEndDate(project.getEndDate());
            }

            return projectRepo.save(presentProject);
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

    public List<ProjectTimelineDto> getProjectsTimeline(){
        return projectRepo.getProjectsTimeline();
    }

    /*protected static synchronized Project mapDtoToEntity(ProjectDto projectDto){
        Project project = new Project();
        if(projectDto.getProjectId() != null){
            project.setProjectId(projectDto.getProjectId().longValue());
        }

        project.setStage(projectDto.getStage());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());

        return project;
    }*/
}
