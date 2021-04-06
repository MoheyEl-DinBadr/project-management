package com.mohey.pma.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mohey.pma.dao.ProjectRepository;
import com.mohey.pma.dto.ChartData;
import com.mohey.pma.entities.Employee;
import com.mohey.pma.entities.Project;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProjectService.class})
@ExtendWith(SpringExtension.class)
public class ProjectServiceTest {
    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Test
    public void testSave() {
        Project project = new Project();
        project.setEmployees(new ArrayList<Employee>());
        project.setProjectId(123L);
        project.setName("Name");
        project.setStage("Stage");
        project.setDescription("The characteristics of someone or something");
        when(this.projectRepository.save((Project) any())).thenReturn(project);
        assertSame(project,
                this.projectService.save(new Project("Name", "Stage", "The characteristics of someone or something")));
        verify(this.projectRepository).save((Project) any());
    }

    @Test
    public void testGetAll() {
        ArrayList<Project> projectList = new ArrayList<Project>();
        when(this.projectRepository.findAll()).thenReturn(projectList);
        List<Project> actualAll = this.projectService.getAll();
        assertSame(projectList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(this.projectRepository).findAll();
    }

    @Test
    public void testGetProjectStatus() {
        ArrayList<ChartData> chartDataList = new ArrayList<ChartData>();
        when(this.projectRepository.getProjectStatus()).thenReturn(chartDataList);
        List<ChartData> actualProjectStatus = this.projectService.getProjectStatus();
        assertSame(chartDataList, actualProjectStatus);
        assertTrue(actualProjectStatus.isEmpty());
        verify(this.projectRepository).getProjectStatus();
    }
}

