package com.example.lesson_4.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasketDto {
    private final List<LineItem> list;

    public BasketDto() {
        this.list = new ArrayList<>();
    }

    public List<LineItem> getList() {
        return list;
    }
}
