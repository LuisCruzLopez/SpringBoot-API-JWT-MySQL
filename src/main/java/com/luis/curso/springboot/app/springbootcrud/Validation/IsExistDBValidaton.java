package com.luis.curso.springboot.app.springbootcrud.Validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.luis.curso.springboot.app.springbootcrud.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsExistDBValidaton implements ConstraintValidator<IsExistDB, String> {

    @Autowired
    private ProductService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (!service.existsBySku(value));
    }

}
