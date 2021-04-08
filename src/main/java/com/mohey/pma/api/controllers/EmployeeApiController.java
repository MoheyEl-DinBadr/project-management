package com.mohey.pma.api.controllers;

import com.mohey.pma.entities.Employee;
import com.mohey.pma.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id){
        return employeeService.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@Valid @RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee partialUpdate(@RequestBody Employee patchEmployee, @PathVariable Long id){
        patchEmployee.setEmployeeId(id);
        return employeeService.saveOrUpdate(patchEmployee);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        try{
            employeeService.delete(id);
        }catch (EmptyResultDataAccessException e){

        }

    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmploy(@RequestParam(value = "page") int page,
                                                  @RequestParam(value = "size") int size){
        Pageable pageAndSize = PageRequest.of(page, size);
        return employeeService.findAll(pageAndSize);
    }

}
