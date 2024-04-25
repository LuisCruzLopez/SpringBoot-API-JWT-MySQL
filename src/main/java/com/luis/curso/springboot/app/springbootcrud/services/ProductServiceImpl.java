package com.luis.curso.springboot.app.springbootcrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luis.curso.springboot.app.springbootcrud.entities.Product;
import com.luis.curso.springboot.app.springbootcrud.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            Product productDB = productOptional.orElseThrow();

            productDB.setSku(product.getSku());
            productDB.setName(product.getName());
            productDB.setDescription(product.getDescription());
            productDB.setPrice(product.getPrice());
            // converit un objeto product en un Optional<Product>
            return Optional.of(repository.save(productDB));
        }
        return productOptional;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        productOptional.ifPresent(productDB -> {
            repository.delete(productDB);
        });
        return productOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }

}
