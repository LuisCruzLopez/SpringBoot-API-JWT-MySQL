package com.luis.curso.springboot.app.springbootcrud.entities;

import com.luis.curso.springboot.app.springbootcrud.Validation.IsExistDB;
import com.luis.curso.springboot.app.springbootcrud.Validation.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // @NotEmpty(message = "{NotEmpty.Product.name}")
    @IsRequired(message = "{IsRequired.Product.name}")
    @Size(min = 3, max = 20)
    private String name;
    @Min(value = 500, message = "{Min.Product.price}")
    @NotNull(message = "{NotNull.Product.price}")
    private Integer price;
    // @NotBlank(message = "{NotBlank.Product.description}")
    @IsRequired
    private String description;
    @IsExistDB
    private String sku;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

}
