package com.practice.belltask.controller.advice;

import com.practice.belltask.dto.ResponseDto;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.practice.belltask.util.ResponseUtil.errorResponse;

@RestControllerAdvice("com.practice.belltask")
public class ControllersAdvice {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseDto<Object>> handleNotFound(NotFoundException e) {
        return errorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseDto<Object>> handleException(Exception e) {
        return errorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
