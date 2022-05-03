package com.example.lesson_4.rest;

import com.example.lesson_4.controller.NotFoundException;
import com.example.lesson_4.dto.ProductDto;
import com.example.lesson_4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RequestMapping("/rest/v1/product")
@RestController
public class ProductResource {

    private ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public Page<ProductDto> findAll(@RequestParam Optional<String> productFilter,
                                    @RequestParam Optional<String> productMinFilter,
                                    @RequestParam Optional<String> productMaxFilter,
                                    @RequestParam Optional<Integer> page,
                                    @RequestParam Optional<Integer> size,
                                    @RequestParam Optional<String> sort) {

        String productFilterValue = productFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        String productMinFilterValue = productMinFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        String productMaxFilterValue = productMaxFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        int pageValue = page.orElse(1) - 1;
        int sizeValue = size.orElse(5);
        String sortValue = sort
                .filter(s -> !s.isBlank())
                .orElse("id");
        return productService.findProductsByFilter(productFilterValue,
                productMinFilterValue,
                productMaxFilterValue,
                pageValue,
                sizeValue,
                sortValue);
    }

    @GetMapping("/{id}/id")
    public ProductDto findById(@PathVariable("id") long id) {
        return productService.findProductById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Secured({"ROLE_SUPER_ADMIN", "RILE_ADMIN"})
    @PostMapping
    public ProductDto create(@RequestBody ProductDto product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("product shouldn't have id");
        }
        return productService.saveProduct(product);
    }

    @Secured({"ROLE_SUPER_ADMIN", "RILE_ADMIN"})
    @PutMapping
    public ProductDto update(@RequestBody ProductDto product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("product should have id");
        }
        return productService.saveProduct(product);
    }

//    @ExceptionHandler
//    public ResponseEntity<ProductResponseError> handleException(NotFoundException exc) {
//        ProductResponseError productResponseError = new ProductResponseError();
//        productResponseError.setStatus(HttpStatus.NOT_FOUND.value());
//        productResponseError.setMessage(exc.getMessage());
//        productResponseError.setTimestamp(System.currentTimeMillis());
//        return new ResponseEntity<>(productResponseError, HttpStatus.NOT_FOUND);
//    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String illegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public String sqlException(SQLException ex) {
        return ex.getMessage();
    }
}
