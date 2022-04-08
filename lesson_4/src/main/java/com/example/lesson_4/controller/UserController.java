package com.example.lesson_4.controller;

import com.example.lesson_4.persist.User;
import com.example.lesson_4.persist.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
        return "user_form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        userRepository.delete(id);
        return "redirect:/user";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("user", new User(""));
        return "user_form";
    }

    @PostMapping
    public String save(@Valid User user, BindingResult binding) {
        if (binding.hasErrors()) {
            return "user_form";
        }
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            binding.rejectValue("password", "", "Password not match");
            return "user_form";
        }
        userRepository.save(user);
        return "redirect:/user";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundExceptionHandler(Model model, NotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
