package com.mohey.pma.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ProjectTest {
    @Test
    public void testSetProjectId() {
        Project project = new Project("Name", "Stage", "The characteristics of someone or something");
        project.setProjectId(123L);
        assertEquals(123L, project.getProjectId());
    }

    @Test
    public void testSetName() {
        Project project = new Project("Name", "Stage", "The characteristics of someone or something");
        project.setName("Name");
        assertEquals("Name", project.toString());
    }

    @Test
    public void testSetStage() {
        Project project = new Project("Name", "Stage", "The characteristics of someone or something");
        project.setStage("Stage");
        assertEquals("Stage", project.getStage());
    }

    @Test
    public void testSetDescription() {
        Project project = new Project("Name", "Stage", "The characteristics of someone or something");
        project.setDescription("The characteristics of someone or something");
        assertEquals("The characteristics of someone or something", project.getDescription());
    }

    @Test
    public void testSetEmployees() {
        Project project = new Project("Name", "Stage", "The characteristics of someone or something");
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        project.setEmployees(employeeList);
        assertSame(employeeList, project.getEmployees());
    }

    @Test
    public void testAddEmployee() {
        Project project = new Project("Name", "Stage", "The characteristics of someone or something");
        project.addEmployee(new Employee("Jane", "Doe", "jane.doe@example.org"));
        assertEquals(1, project.getEmployees().size());
    }

    @Test
    public void testAddEmployee2() {
        Project project = new Project();
        project.setEmployees(new ArrayList<Employee>());
        project.setProjectId(123L);
        project.setName(null);
        project.setStage(null);
        project.setDescription("The characteristics of someone or something");
        project.addEmployee(new Employee("Jane", "Doe", "jane.doe@example.org"));
        assertEquals(1, project.getEmployees().size());
    }
}

