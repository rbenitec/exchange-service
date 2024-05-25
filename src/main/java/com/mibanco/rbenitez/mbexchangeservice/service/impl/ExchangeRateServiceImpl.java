package com.mibanco.rbenitez.mbexchangeservice.service.impl;

import com.mibanco.rbenitez.mbexchangeservice.dto.ExchangeRateRequestDto;
import com.mibanco.rbenitez.mbexchangeservice.dto.ExchangeRateResponseDto;
import com.mibanco.rbenitez.mbexchangeservice.entities.Exchange;
import com.mibanco.rbenitez.mbexchangeservice.exception.ExchangeRateNotFoundException;
import com.mibanco.rbenitez.mbexchangeservice.repository.ChangeRepository;
import com.mibanco.rbenitez.mbexchangeservice.repository.ExchangeRateRepository;
import com.mibanco.rbenitez.mbexchangeservice.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ChangeRepository changeRepository;

//    private final ExchangeRateRepository exchangeRateRepository;
//
//    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
//        this.exchangeRateRepository = exchangeRateRepository;
//    }

    @Override
    public Flux<Exchange> getAllExchange() {
        return changeRepository.findAll();
    }

    @Override
    public Mono<ExchangeRateResponseDto> applyExchangeRate(ExchangeRateRequestDto exchangeRateRequestDto) {
        return changeRepository.findByMoneyOriginAndDestiny(exchangeRateRequestDto.getMoneyDestine(), exchangeRateRequestDto.getMoneyOrigin())
                .map(exchange -> new ExchangeRateResponseDto(
                        exchangeRateRequestDto.getAmount(),
                        exchangeRateRequestDto.getMoneyDestine(),
                        exchangeRateRequestDto.getMoneyOrigin(),
                        roundTwoDecimal(exchange.getRate()*exchangeRateRequestDto.getAmount()),
                        exchange.getOrigin().concat(" --> ").concat(exchange.getDestine())
                ))
                .switchIfEmpty(Mono.error(new ExchangeRateNotFoundException(exchangeRateRequestDto.getMoneyOrigin(), exchangeRateRequestDto.getMoneyDestine(), HttpStatus.ACCEPTED, "F-509")))
                .doOnSuccess(response -> log.info("[Exchange Rate successful] -> {}", response.toString()));
    }

    @Override
    public Mono<Exchange> saveExchange(Exchange exchange) {
        return changeRepository.save(exchange);
    }

    public double roundTwoDecimal(double value){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        String newValue = df.format(value);
        return Double.parseDouble(newValue);
    }


}
