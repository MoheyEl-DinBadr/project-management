package com.mohey.pma.dao;

import com.mohey.pma.dto.EmployeeProject;
import com.mohey.pma.dto.ProjectDto;
import com.mohey.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 15, 2021
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "apiemployees", path = "apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    @Override
    List<Employee> findAll();

    @Query(nativeQuery = true, value = "SELECT e.first_name as firstName," +
            " e.last_name as lastName, COUNT(pe.employee_id) as " +
            "projectCount FROM employee e left join project_employee pe " +
            "ON pe.employee_id = e.employee_id GROUP BY e.first_name, " +
            "e.last_name ORDER BY 3 DESC")
    public List<EmployeeProject> employeeProjects();

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM project p WHERE " +
            "p.project_id IN(" +
            "SELECT project_id " +
            "FROM project_employee " +
            "WHERE employee_id = ?)")
    public List<ProjectDto> findProjectsByEmployeeId(Long id);

    public Employee findByEmail(@Param("email") String email);

}
