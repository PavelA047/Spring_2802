package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;
import ru.geekbrains.prod.Product;
import ru.geekbrains.prod.ProductRepository;

import javax.annotation.PostConstruct;

@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @PostConstruct
    public void init() {
        this.save(new Product("Product 1", 1000L));
        this.save(new Product("Product 2", 1500L));
        this.save(new Product("Product 3", 2000L));
        this.save(new Product("Product 4", 2500L));
        this.save(new Product("Product 5", 3000L));
    }

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("products", productRepository.getProductList());
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.getProductByID(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String form(Model model) {
        Product product = new Product(null, null);
        model.addAttribute("product", product);
        return "product_add_form";
    }

    @PostMapping
    public String save(Product product) {
        productRepository.add(product);
        return "redirect:/product";
    }
}
