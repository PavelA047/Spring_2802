package com.example.lesson_4.controller;

import com.example.lesson_4.prod.Product;
import com.example.lesson_4.prod.ProductRepository;
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

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(@RequestParam Optional<String> productFilter,
                           @RequestParam Optional<Long> productMinFilter,
                           @RequestParam Optional<Long> productMaxFilter,
                           Model model) {
        if ((productFilter.isEmpty() || productFilter.get().isBlank())
                && productMinFilter.isEmpty()
                && productMaxFilter.isEmpty()) {
            model.addAttribute("products", productRepository.findAll());
        } else if (!(productFilter.isEmpty() || productFilter.get().isBlank())
                && productMinFilter.isPresent()
                && productMaxFilter.isPresent()) {
            model.addAttribute("products", productRepository.findByTitleAndCostBetweenMinAndMax("%" + productFilter.get() + "%", productMinFilter.get(), productMaxFilter.get()));
        } else if (!(productFilter.isEmpty() || productFilter.get().isBlank())
                && productMinFilter.isEmpty()
                && productMaxFilter.isEmpty()) {
            model.addAttribute("products", productRepository.findUserByTitleLike("%" + productFilter.get() + "%"));
        } else if (!(productFilter.isEmpty() || productFilter.get().isBlank())
                && productMinFilter.isPresent()
                && productMaxFilter.isEmpty()) {
            model.addAttribute("products", productRepository.findByTitleAndCostGreaterThenEqual("%" + productFilter.get() + "%", productMinFilter.get()));
        } else if (!(productFilter.isEmpty() || productFilter.get().isBlank())
                && productMinFilter.isEmpty()
                && productMaxFilter.isPresent()) {
            model.addAttribute("products", productRepository.findByTitleAndCostLessThenEqual("%" + productFilter.get() + "%", productMaxFilter.get()));
        } else if ((productFilter.isEmpty() || productFilter.get().isBlank())
                && productMinFilter.isPresent()
                && productMaxFilter.isPresent()) {
            model.addAttribute("products", productRepository.findByCostBetween(productMinFilter.get(), productMaxFilter.get()));
        } else if ((productFilter.isEmpty() || productFilter.get().isBlank())
                && productMinFilter.isPresent()
                && productMaxFilter.isEmpty()) {
            model.addAttribute("products", productRepository.findByCostGreaterThanEqual(productMinFilter.get()));
        } else if ((productFilter.isEmpty() || productFilter.get().isBlank())
                && productMinFilter.isEmpty()
                && productMaxFilter.isPresent()) {
            model.addAttribute("products", productRepository.findByCostLessThanEqual(productMaxFilter.get()));
        }
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute(new Product("", null));
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }
}
