package com.mibanco.rbenitez.mbexchangeservice.controller;

import com.mibanco.rbenitez.mbexchangeservice.dto.ExchangeRateDto;
import com.mibanco.rbenitez.mbexchangeservice.dto.ExchangeRateRequestDto;
import com.mibanco.rbenitez.mbexchangeservice.dto.ExchangeRateResponseDto;
import com.mibanco.rbenitez.mbexchangeservice.entities.Exchange;
import com.mibanco.rbenitez.mbexchangeservice.enums.Money;
import com.mibanco.rbenitez.mbexchangeservice.exception.BusinessException;
import com.mibanco.rbenitez.mbexchangeservice.service.ExchangeRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/all")
    public Flux<Exchange> getAllExchange(){
        return exchangeRateService.getAllExchange();
    }


    @PostMapping("/apply")
    public Mono<ResponseEntity<ExchangeRateResponseDto>> applyExchangeRate(@Valid @RequestBody ExchangeRateRequestDto exchangeRateRequestDto){
        validateExchangeRequest(exchangeRateRequestDto);
        return exchangeRateService.applyExchangeRate(exchangeRateRequestDto)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<Exchange>> registerChanges(@Valid @RequestBody ExchangeRateDto exchangeRateDto){
        Exchange exchange =  Exchange.builder()
                .origin(exchangeRateDto.getOrigin())
                .destine(exchangeRateDto.getDestine())
                .rate(exchangeRateDto.getRate())
                .build();
        return exchangeRateService.saveExchange(exchange)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    private void validateExchangeRequest(ExchangeRateRequestDto exchangeRateRequestDto) {
        if (Objects.isNull(exchangeRateRequestDto)) {
            throw new BusinessException("P-300", "ExchangeRateRequestDto has to be different of null", HttpStatus.BAD_REQUEST);
        }
        if (Objects.isNull(exchangeRateRequestDto.getMoneyDestine())) {
            throw new BusinessException("P-301", "MoneyDestine Field is required", HttpStatus.BAD_REQUEST);
        }
        if (Objects.isNull(exchangeRateRequestDto.getMoneyOrigin())) {
            throw new BusinessException("P-302", "MoneyOrigin Field value is required", HttpStatus.BAD_REQUEST);
        }
        if(Objects.isNull(validatedMoney(exchangeRateRequestDto.getMoneyDestine()))){
            throw new BusinessException("P-303", "MoneyDestine Field value not validated", HttpStatus.NOT_FOUND);
        }
        if(Objects.isNull(validatedMoney(exchangeRateRequestDto.getMoneyOrigin()))){
            throw new BusinessException("P-304", "MoneyOrigin Field value not validated", HttpStatus.NOT_FOUND);
        }
        if (exchangeRateRequestDto.getAmount()==null) {
            throw new BusinessException("P-305", "Amount Field is required", HttpStatus.BAD_REQUEST);
        }
        if (exchangeRateRequestDto.getAmount()<=0) {
            throw new BusinessException("P-306", "Amount Field greater to zero", HttpStatus.BAD_REQUEST);
        }
        /*String regex = "^[-+]?\\d*\\.?\\d+$";
        if (!exchangeRateRequestDto.getAmount().toString().matches(regex)) {
            throw new BusinessException("P-302","number invalid incorrect format",HttpStatus.NOT_ACCEPTABLE);
        }*/
    }

    public Money validatedMoney(String value){
        for(Money money : Money.values()){
            if (money.name().contains(value)){
                return money;
            }
        }
        return null;
    }
}
