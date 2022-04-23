package com.example.lesson_4.controller;

import com.example.lesson_4.dto.ProductDto;
import com.example.lesson_4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(@RequestParam Optional<String> productFilter,
                           @RequestParam Optional<String> productMinFilter,
                           @RequestParam Optional<String> productMaxFilter,
                           @RequestParam Optional<Integer> page,
                           @RequestParam Optional<Integer> size,
                           @RequestParam Optional<String> sort,
                           Model model) {

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

        model.addAttribute("products", productService.findProductsByFilter(productFilterValue,
                productMinFilterValue,
                productMaxFilterValue,
                pageValue,
                sizeValue,
                sortValue));

//        if ((productFilter.isEmpty() || productFilter.get().isBlank())
//                && productMinFilter.isEmpty()
//                && productMaxFilter.isEmpty()) {
//            model.addAttribute("products", productRepository.findAll());
//        } else if (!(productFilter.isEmpty() || productFilter.get().isBlank())
//                && productMinFilter.isPresent()
//                && productMaxFilter.isPresent()) {
//            model.addAttribute("products", productRepository.findByTitleAndCostBetweenMinAndMax("%" + productFilter.get() + "%", productMinFilter.get(), productMaxFilter.get()));
//        } else if (!(productFilter.isEmpty() || productFilter.get().isBlank())
//                && productMinFilter.isEmpty()
//                && productMaxFilter.isEmpty()) {
//            model.addAttribute("products", productRepository.findUserByTitleLike("%" + productFilter.get() + "%"));
//        } else if (!(productFilter.isEmpty() || productFilter.get().isBlank())
//                && productMinFilter.isPresent()
//                && productMaxFilter.isEmpty()) {
//            model.addAttribute("products", productRepository.findByTitleAndCostGreaterThenEqual("%" + productFilter.get() + "%", productMinFilter.get()));
//        } else if (!(productFilter.isEmpty() || productFilter.get().isBlank())
//                && productMinFilter.isEmpty()
//                && productMaxFilter.isPresent()) {
//            model.addAttribute("products", productRepository.findByTitleAndCostLessThenEqual("%" + productFilter.get() + "%", productMaxFilter.get()));
//        } else if ((productFilter.isEmpty() || productFilter.get().isBlank())
//                && productMinFilter.isPresent()
//                && productMaxFilter.isPresent()) {
//            model.addAttribute("products", productRepository.findByCostBetween(productMinFilter.get(), productMaxFilter.get()));
//        } else if ((productFilter.isEmpty() || productFilter.get().isBlank())
//                && productMinFilter.isPresent()
//                && productMaxFilter.isEmpty()) {
//            model.addAttribute("products", productRepository.findByCostGreaterThanEqual(productMinFilter.get()));
//        } else if ((productFilter.isEmpty() || productFilter.get().isBlank())
//                && productMinFilter.isEmpty()
//                && productMaxFilter.isPresent()) {
//            model.addAttribute("products", productRepository.findByCostLessThanEqual(productMaxFilter.get()));
//        }
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("productDto", productService.findProductById(id));
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute(new ProductDto("", null));
        return "product_form";
    }

    @PostMapping
    public String save(@Valid /*@ModelAttribute("product")*/ ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.saveProduct(productDto);
        return "redirect:/product";
    }
}
