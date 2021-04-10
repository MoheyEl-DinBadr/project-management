package com.mohey.pma.validatiors;

import com.mohey.pma.entities.Project;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateCheckValidator implements ConstraintValidator<DateValidation, Project> {

    @Override
    public boolean isValid(Project project, ConstraintValidatorContext context) {
        if(project.getEndDate() != null && project.getStartDate()!= null){
            return project.getEndDate().after(project.getStartDate());
        }
        return true;
    }
}
