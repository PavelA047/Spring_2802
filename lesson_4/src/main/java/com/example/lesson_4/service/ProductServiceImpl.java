package com.example.lesson_4.service;

import com.example.lesson_4.controller.ProductSpec;
import com.example.lesson_4.dto.ProductDto;
import com.example.lesson_4.persist.Product;
import com.example.lesson_4.persist.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findProductsByFilter(String productFilter,
                                                 String minProductFilter,
                                                 String maxProductFilter,
                                                 int page,
                                                 int size,
                                                 String sort) {
        Specification<Product> spec = Specification.where(null);
        if (productFilter != null) {
            spec = spec.and(ProductSpec.titleContaining(productFilter));
        }
        if (minProductFilter != null && Pattern.matches("\\d+", minProductFilter)) {
            spec = spec.and(ProductSpec.minCost(new BigDecimal(minProductFilter)));
        }
        if (maxProductFilter != null && Pattern.matches("\\d+", maxProductFilter)) {
            spec = spec.and(ProductSpec.maxCost(new BigDecimal(maxProductFilter)));
        }
        return productRepository
                .findAll(spec, PageRequest.of(page, size, Sort.by(sort)))
                .map(product -> new ProductDto(product.getId(), product.getTitle(), product.getCost()));
    }

    @Override
    public Optional<ProductDto> findProductById(long id) {
        return productRepository
                .findById(id)
                .map(product -> new ProductDto(product.getId(), product.getTitle(), product.getCost()));
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        productRepository.save(new Product(productDto.getId(), productDto.getTitle(), productDto.getCost()));
        return productDto;
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}
