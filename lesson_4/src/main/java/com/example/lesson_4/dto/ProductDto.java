package com.example.lesson_4.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductDto {

    private Long id;

    @NotBlank
    private String title;

    @Min(value = 100)
    @Max(value = 10000)
    @NotNull
    private BigDecimal cost;

    public ProductDto(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }

    public ProductDto(Long id, String title, BigDecimal cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public ProductDto() {
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
