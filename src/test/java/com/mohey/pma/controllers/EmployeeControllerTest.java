package com.mohey.pma.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.mohey.pma.entities.Employee;
import com.mohey.pma.entities.Project;
import com.mohey.pma.service.EmployeeService;
import com.mohey.pma.service.ProjectService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private ProjectService projectService;

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setLastName("Doe");
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setProjects(new ArrayList<Project>());
        employee.setEmployeeId(123L);
        when(this.employeeService.save((Employee) any())).thenReturn(employee);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees/save");
        MockMvcBuilders.standaloneSetup(this.employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("employee"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/employees/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/employees/"));
    }

    @Test
    public void testDisplayEmployeeForm() throws Exception {
        when(this.projectService.getAll()).thenReturn(new ArrayList<Project>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/new");
        MockMvcBuilders.standaloneSetup(this.employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("allProjects", "employee"))
                .andExpect(MockMvcResultMatchers.view().name("employees/new-employee"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("employees/new-employee"));
    }

    @Test
    public void testDisplayEmployeeForm2() throws Exception {
        when(this.projectService.getAll()).thenReturn(new ArrayList<Project>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/employees/new");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.employeeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("allProjects", "employee"))
                .andExpect(MockMvcResultMatchers.view().name("employees/new-employee"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("employees/new-employee"));
    }

    @Test
    public void testDisplayEmployees() throws Exception {
        when(this.employeeService.getAll()).thenReturn(new ArrayList<Employee>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/");
        MockMvcBuilders.standaloneSetup(this.employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("employees"))
                .andExpect(MockMvcResultMatchers.view().name("employees/list-employees"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("employees/list-employees"));
    }

    @Test
    public void testDisplayEmployees2() throws Exception {
        when(this.employeeService.getAll()).thenReturn(new ArrayList<Employee>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/employees/");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.employeeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("employees"))
                .andExpect(MockMvcResultMatchers.view().name("employees/list-employees"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("employees/list-employees"));
    }
}

