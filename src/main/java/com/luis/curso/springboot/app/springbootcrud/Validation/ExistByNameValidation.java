package com.luis.curso.springboot.app.springbootcrud.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luis.curso.springboot.app.springbootcrud.services.UserSerevice;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByNameValidation implements ConstraintValidator<ExistByUsername, String> {

    @Autowired
    private UserSerevice service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return (!service.existsByUsername(value));
    }

}
