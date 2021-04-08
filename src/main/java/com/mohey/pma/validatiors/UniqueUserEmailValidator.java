package com.mohey.pma.validatiors;

import com.mohey.pma.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    @Autowired
    UserAccountService userAccountService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userAccountService.findByEmail(value) == null;
    }
}
