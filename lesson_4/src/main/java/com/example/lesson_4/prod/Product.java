package com.example.lesson_4.prod;

import javax.validation.constraints.*;

public class Product {

    private Long id;

    @NotBlank
    private String title;

    @Min(value = 100L)
    @Max(value = 10000L)
    @NotNull
    private Long cost;

    public Product(String title, Long cost) {
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
