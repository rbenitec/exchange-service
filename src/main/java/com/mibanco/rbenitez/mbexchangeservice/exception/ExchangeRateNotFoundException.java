package com.mibanco.rbenitez.mbexchangeservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ExchangeRateNotFoundException extends RuntimeException {

    private String code;
    private HttpStatus httpStatus;
    public ExchangeRateNotFoundException(String moneyOrigin, String moneyDestine, HttpStatus httpStatus, String code) {
        super(String.format("Exchange rate not found for %s to %s", moneyOrigin, moneyDestine));
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
