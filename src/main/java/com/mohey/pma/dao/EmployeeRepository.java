package com.mohey.pma.dao;

import com.mohey.pma.entities.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 15, 2021
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    List<Employee> findAll();
}
