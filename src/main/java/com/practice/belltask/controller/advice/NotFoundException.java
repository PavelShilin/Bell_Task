package com.practice.belltask.controller.advice;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String s) {
        super(s);
    }
}
