package com.ecommerce.ecommerceApp.model;

public class Categories {
    private int id;
    private String name;

//     Default constructor (required for Spring BeanPropertyRowMapper)
    public Categories() {
    }

    // Parameterized constructor
    public Categories(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

