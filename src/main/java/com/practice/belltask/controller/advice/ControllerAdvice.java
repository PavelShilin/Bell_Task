package com.practice.belltask.controller.advice;


import com.practice.belltask.view.Data;
import com.practice.belltask.view.ErrorView;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;


@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler implements ResponseBodyAdvice<Object> {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorView> exception(Exception ex) {

        if (ex.getCause().getClass() == ConstraintViolationException.class) {
            String constraintViolationException = "Entity with provided ID not found.";
            return new ResponseEntity<>(new ErrorView(constraintViolationException), HttpStatus.NOT_FOUND);
        } else if (ex.getCause().getClass() == EntityNotFoundException.class || ex.getCause().getClass() == NoResultException.class) {
            String noEntity = "There is no such entity in database.";
            return new ResponseEntity<>(new ErrorView(noEntity), HttpStatus.NOT_FOUND);
        } else if (ex.getCause().getClass() == MethodArgumentNotValidException.class) {
            String invalidArg = "No valid arguments.";
            return new ResponseEntity<>(new ErrorView(invalidArg), HttpStatus.BAD_REQUEST);
        } else {
            String internalError = "There is a problem. Try again.";
            return new ResponseEntity<>(new ErrorView(internalError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o == null) {
            o = "success";
        }
        if (o.getClass() == ErrorView.class) {
            return o;
        }
        return new Data<>(o);
    }

}
