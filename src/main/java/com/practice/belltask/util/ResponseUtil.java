package com.practice.belltask.util;

import com.practice.belltask.dto.ResponseDto;
import com.practice.belltask.dto.SuccessResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ResponseUtil {

    public static <T> ResponseEntity<ResponseDto<T>> errorResponse(String msg, HttpStatus code) {
        return new ResponseEntity<>(new ResponseDto<>(msg), code);
    }

    public static <T> ResponseEntity<ResponseDto<T>> errorResponse(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder("Binding error:\n");
        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(error.getObjectName())
                    .append(":")
                    .append(error.getDefaultMessage())
                    .append("\n");
        }
        return new ResponseEntity<>(new ResponseDto<>(msg.toString()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public static <T> ResponseEntity<ResponseDto<T>> dataResponse(T data) {
        return new ResponseEntity<>(new ResponseDto<>(data), HttpStatus.OK);
    }

    public static ResponseEntity<ResponseDto<SuccessResponseDto>> successResponse() {
        return dataResponse(new SuccessResponseDto());
    }

}
