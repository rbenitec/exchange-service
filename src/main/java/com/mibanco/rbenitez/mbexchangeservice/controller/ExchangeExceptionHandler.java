package com.mibanco.rbenitez.mbexchangeservice.controller;

import com.mibanco.rbenitez.mbexchangeservice.dto.ErrorDto;
import com.mibanco.rbenitez.mbexchangeservice.exception.BusinessException;
import com.mibanco.rbenitez.mbexchangeservice.exception.ExchangeRateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExchangeExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorDto> businessExceptionHandler(BusinessException ex){
        ErrorDto error = ErrorDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        log.error("[businessExceptionHandler] - {}", error);
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(value = ExchangeRateNotFoundException.class)
    public ResponseEntity<ErrorDto> businessExceptionHandler(ExchangeRateNotFoundException ex){
        ErrorDto error = ErrorDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        log.error("[businessExceptionHandler] - {}", error);
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }
}
