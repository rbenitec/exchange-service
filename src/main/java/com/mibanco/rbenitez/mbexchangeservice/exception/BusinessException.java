package com.mibanco.rbenitez.mbexchangeservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BusinessException extends RuntimeException{

    private String code;
    private HttpStatus httpStatus;

    public BusinessException(String code, String message, HttpStatus httpStatus){
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
