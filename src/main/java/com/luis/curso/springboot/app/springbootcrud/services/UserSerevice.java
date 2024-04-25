package com.luis.curso.springboot.app.springbootcrud.services;

import java.util.List;

import com.luis.curso.springboot.app.springbootcrud.entities.User;

public interface UserSerevice {
    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);

}
