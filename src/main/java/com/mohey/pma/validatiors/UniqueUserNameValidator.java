package com.mohey.pma.validatiors;

import com.mohey.pma.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    @Autowired
    UserAccountService userAccountService;

    public boolean isValid(String userName, ConstraintValidatorContext context) {
        return userAccountService.findByUserName(userName) == null;
    }
}
