package com.example.lesson_4.controller;

import com.example.lesson_4.dto.UserDto;
import com.example.lesson_4.service.RoleService;
import com.example.lesson_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String listPage(@RequestParam Optional<String> usernameFilter,
                           @RequestParam Optional<String> emailFilter,
                           @RequestParam Optional<Integer> page,
                           @RequestParam Optional<Integer> size,
                           @RequestParam Optional<String> sortField,
                           Model model) {
        String usernameFilterValue = usernameFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        String emailFilterValue = emailFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");
        model.addAttribute("users", userService.findUsersByFilter(
                usernameFilterValue,
                emailFilterValue,
                pageValue,
                sizeValue,
                sortFieldValue));
        return "user";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable long id, Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", userService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
        return "user_form";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", new UserDto());
        return "user_form";
    }

    @Secured("ROLE_SUPER_ADMIN")
    @PostMapping
    public String save(@Valid @ModelAttribute("user") UserDto user, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "user_form";
        }
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            binding.rejectValue("password", "", "Password not match");
            model.addAttribute("roles", roleService.findAll());
            return "user_form";
        }
        userService.save(user);
        return "redirect:/user";
    }

    @Secured("ROLE_SUPER_ADMIN")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/user";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundExceptionHandler(Model model, NotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
