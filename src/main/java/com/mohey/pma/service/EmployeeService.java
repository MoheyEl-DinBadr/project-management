package com.mohey.pma.service;

import com.mohey.pma.dao.EmployeeRepository;
import com.mohey.pma.dto.EmployeeProject;
import com.mohey.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 24, 2021
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

   public Employee save(Employee employee){
       return employeeRepo.save(employee);
   }

   public List<Employee> getAll(){
       return employeeRepo.findAll();
   }

   public List<EmployeeProject> employeeProjects(){
       return employeeRepo.employeeProjects();
   }
}
