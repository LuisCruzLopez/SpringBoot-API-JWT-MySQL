package com.luis.curso.springboot.app.springbootcrud.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistByNameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistByUsername {
    String message() default "ya existe en la BD, elija otro";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
