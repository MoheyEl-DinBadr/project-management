package com.mohey.pma.service;

import com.mohey.pma.dao.EmployeeRepository;
import com.mohey.pma.dto.EmployeeProject;
import com.mohey.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 24, 2021
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public Employee save(Employee employee){
        return employeeRepo.save(employee);
    }

    public List<Employee> getAll(){
        return employeeRepo.findAll();
    }

    public List<EmployeeProject> employeeProjects(){
        return employeeRepo.employeeProjects();
    }

    public Employee findById(Long id){
        if(employeeRepo.findById(id).isPresent())
        return employeeRepo.findById(id).get();

        return null;
    }

    public Employee saveOrUpdate(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepo.findById(employee.getEmployeeId());
        if(employeeOptional.isPresent()){
            Employee presentEmployee = employeeOptional.get();
            if(employee.getEmail() != null){
                presentEmployee.setEmail(employee.getEmail());
            }
            if(employee.getFirstName() != null){
                presentEmployee.setFirstName(employee.getFirstName());
            }
            if(employee.getLastName() != null){
                presentEmployee.setLastName(employee.getLastName());
            }
            if(employee.getProjects() != null && employee.getProjects().size() != 0){
                presentEmployee.setProjects(employee.getProjects());
            }
            employeeRepo.save(presentEmployee);
            return presentEmployee;
        }else{
            employeeRepo.save(employee);
            return employee;
        }
    }

    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }

    public Employee findByEmail(String email){
        return employeeRepo.findByEmail(email);
    }

    public Iterable<Employee> findAll(Pageable pageable){
        return employeeRepo.findAll(pageable);
    }

    /*public List<Project> findProjectsByEmployeeId(Long id){
        List<ProjectDto> projectDtos = employeeRepo.findProjectsByEmployeeId(id);
        List<Project> projects = new ArrayList<>();
        projectDtos.stream().parallel().forEach(projectDto ->{
            projects.add(ProjectService.mapDtoToEntity(projectDto));
        });

        return projects;
    }

    public static synchronized Employee mapDtoToEntity(EmployeeDto employeeDto){
        return null;
    }*/

}
