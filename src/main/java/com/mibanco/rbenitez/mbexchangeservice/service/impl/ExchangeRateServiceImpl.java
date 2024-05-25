package com.mibanco.rbenitez.mbexchangeservice.service.impl;

import com.mibanco.rbenitez.mbexchangeservice.dto.ExchangeRateRequestDto;
import com.mibanco.rbenitez.mbexchangeservice.dto.ExchangeRateResponseDto;
import com.mibanco.rbenitez.mbexchangeservice.entities.Exchange;
import com.mibanco.rbenitez.mbexchangeservice.repository.ExchangeRateRepository;
import com.mibanco.rbenitez.mbexchangeservice.service.ExchangeRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Service
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public Flux<Exchange> getAllExchange() {
        return exchangeRateRepository.finAll();
    }

    @Override
    public Mono<ExchangeRateResponseDto> applyExchangeRate(ExchangeRateRequestDto exchangeRateRequestDto) {
        return exchangeRateRepository.findExchangeRateType(exchangeRateRequestDto.getMoneyOrigin(), exchangeRateRequestDto.getMoneyDestine())
                .map(exchange -> new ExchangeRateResponseDto(
                        exchangeRateRequestDto.getAmount(),
                        exchangeRateRequestDto.getMoneyDestine(),
                        exchangeRateRequestDto.getMoneyOrigin(),
                        roundTwoDecimal(exchange.getRate()*exchangeRateRequestDto.getAmount()),
                        exchange.getMoneyOrigin().concat(" --> ").concat(exchange.getMoneyDestine())
                )).doOnSuccess(response -> log.info("[Exchange Rate successful] -> {}", response.toString()));
    }

    public double roundTwoDecimal(double value){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        String newValue = df.format(value);
        return Double.parseDouble(newValue);
    }
}
