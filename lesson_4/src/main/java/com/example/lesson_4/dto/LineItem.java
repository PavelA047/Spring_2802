package com.example.lesson_4.dto;

import java.util.Objects;

public class LineItem {
    private ProductDto productDto;
    private UserDto userDto;
    private Long countOfProducts;

    public LineItem(ProductDto productDto, UserDto userDto) {
        this.productDto = productDto;
        this.userDto = userDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Long getCountOfProducts() {
        return countOfProducts;
    }

    public void setCountOfProducts(Long countOfProducts) {
        this.countOfProducts = countOfProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return Objects.equals(productDto, lineItem.productDto) && Objects.equals(userDto, lineItem.userDto) && Objects.equals(countOfProducts, lineItem.countOfProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productDto, userDto, countOfProducts);
    }
}
