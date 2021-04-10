package com.mohey.pma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mohey.pma.validatiors.DateValidation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 15, 2021
 */

@Entity @DateValidation
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
    private long projectId;

    @NotEmpty @Column(unique = true, nullable = false)
    private String name;

    @NotEmpty
    private String stage; //NOT_STARTED, COMPLETED, IN_PROGRESS

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
                fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
                joinColumns = @JoinColumn(name = "project_id"),
                 inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @JsonIgnore
    private List<Employee> employees;


    @Transient
    public static String NOT_STARTED = "NOTSTARTED";
    @Transient
    public static String COMPLETED = "COMPLETED";
    @Transient
    public static String IN_PROGRESS = "INPROGRESS";

    public Project() {
    }

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public Project(String name, String stage, String description, List<Employee> employees) {
        this.name = name;
        this.stage = stage;
        this.description = description;
        this.employees = employees;
    }

    public Project(String name, String stage, String description, Date startDate, Date endDate, List<Employee> employees) {
        this.name = name;
        this.stage = stage;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employees = employees;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){
        if(this.employees == null){
            employees = new ArrayList<>();
        }
        employees.add(employee);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
