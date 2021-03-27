package com.mohey.pma.service;

import com.mohey.pma.dao.ProjectRepository;
import com.mohey.pma.dto.ChartData;
import com.mohey.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Project> getAll(){
        return projectRepo.findAll();
    }

    public List<ChartData> getProjectStatus(){
        return projectRepo.getProjectStatus();
    }
}
