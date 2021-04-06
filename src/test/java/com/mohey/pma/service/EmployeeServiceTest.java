package com.mohey.pma.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mohey.pma.dao.EmployeeRepository;
import com.mohey.pma.dto.EmployeeProject;
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

@ContextConfiguration(classes = {EmployeeService.class})
@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testSave() {
        Employee employee = new Employee();
        employee.setLastName("Doe");
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setProjects(new ArrayList<Project>());
        employee.setEmployeeId(123L);
        when(this.employeeRepository.save((Employee) any())).thenReturn(employee);
        assertSame(employee, this.employeeService.save(new Employee("Jane", "Doe", "jane.doe@example.org")));
        verify(this.employeeRepository).save((Employee) any());
    }

    @Test
    public void testGetAll() {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        when(this.employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> actualAll = this.employeeService.getAll();
        assertSame(employeeList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(this.employeeRepository).findAll();
    }

    @Test
    public void testEmployeeProjects() {
        ArrayList<EmployeeProject> employeeProjectList = new ArrayList<EmployeeProject>();
        when(this.employeeRepository.employeeProjects()).thenReturn(employeeProjectList);
        List<EmployeeProject> actualEmployeeProjectsResult = this.employeeService.employeeProjects();
        assertSame(employeeProjectList, actualEmployeeProjectsResult);
        assertTrue(actualEmployeeProjectsResult.isEmpty());
        verify(this.employeeRepository).employeeProjects();
    }
}

