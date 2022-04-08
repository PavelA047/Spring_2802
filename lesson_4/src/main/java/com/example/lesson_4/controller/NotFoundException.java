package com.example.lesson_4.controller;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String mes) {
        super(mes);
    }
}
