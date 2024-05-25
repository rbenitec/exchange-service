package com.mibanco.rbenitez.mbexchangeservice.service;

import com.mibanco.rbenitez.mbexchangeservice.dto.ExchangeRateRequestDto;
import com.mibanco.rbenitez.mbexchangeservice.dto.ExchangeRateResponseDto;
import com.mibanco.rbenitez.mbexchangeservice.entities.Exchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeRateService  {
    Flux<Exchange> getAllExchange();
    Mono<ExchangeRateResponseDto> applyExchangeRate(ExchangeRateRequestDto exchangeRateRequestDto);
    Mono<Exchange> saveExchange(Exchange exchange);
}
