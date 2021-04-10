package com.mohey.pma.validatiors;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateCheckValidator.class)
public @interface DateValidation {
    String message() default "End Date Should be After Start Date";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
