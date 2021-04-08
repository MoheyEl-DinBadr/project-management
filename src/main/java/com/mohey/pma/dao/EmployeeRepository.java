package com.mohey.pma.dao;

import com.mohey.pma.dto.EmployeeProject;
import com.mohey.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 15, 2021
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    List<Employee> findAll();

    @Query(nativeQuery = true, value = "SELECT e.first_name as firstName," +
            " e.last_name as lastName, COUNT(pe.employee_id) as " +
            "projectCount FROM employee e left join project_employee pe " +
            "ON pe.employee_id = e.employee_id GROUP BY e.first_name, " +
            "e.last_name ORDER BY 3 DESC")
    public List<EmployeeProject> employeeProjects();

    public Employee findByEmail(@Param("email") String email);

}
