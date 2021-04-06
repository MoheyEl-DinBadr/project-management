package com.mohey.pma.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EmployeeTest {
    @Test
    public void testSetEmployeeId() {
        Employee employee = new Employee("Jane", "Doe", "jane.doe@example.org");
        employee.setEmployeeId(123L);
        assertEquals(123L, employee.getEmployeeId());
    }

    @Test
    public void testSetFirstName() {
        Employee employee = new Employee("Jane", "Doe", "jane.doe@example.org");
        employee.setFirstName("Jane");
        assertEquals("Jane", employee.getFirstName());
    }

    @Test
    public void testSetLastName() {
        Employee employee = new Employee("Jane", "Doe", "jane.doe@example.org");
        employee.setLastName("Doe");
        assertEquals("Doe", employee.getLastName());
    }

    @Test
    public void testSetEmail() {
        Employee employee = new Employee("Jane", "Doe", "jane.doe@example.org");
        employee.setEmail("jane.doe@example.org");
        assertEquals("jane.doe@example.org", employee.getEmail());
    }

    @Test
    public void testSetProjects() {
        Employee employee = new Employee("Jane", "Doe", "jane.doe@example.org");
        employee.setProjects(new ArrayList<Project>());
        assertEquals("Employee{firstName='Jane', lastName='Doe', email='jane.doe@example.org', projects=[]}",
                employee.toString());
    }

    @Test
    public void testToString() {
        assertEquals("Employee{firstName='Jane', lastName='Doe', email='jane.doe@example.org', projects=null}",
                (new Employee("Jane", "Doe", "jane.doe@example.org")).toString());
    }
}

