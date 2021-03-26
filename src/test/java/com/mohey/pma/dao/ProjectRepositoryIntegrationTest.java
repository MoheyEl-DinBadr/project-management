package com.mohey.pma.dao;

import com.mohey.pma.entities.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 26, 2021
 */
//@ContextConfiguration(classes = ProjectManagementApplication.class)
//@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:drop.sql"})})
public class ProjectRepositoryIntegrationTest {

    @Autowired
    ProjectRepository projectRepo;

    @Test
    public void ifNewProjectSaved_thenSuccess(){

        Project newProject = new Project();
        newProject.setName("New Test Project");
        newProject.setStage(Project.COMPLETED);
        newProject.setDescription("Test Description");

        projectRepo.save(newProject);

        assertEquals(5, projectRepo.findAll().size());
    }

}
