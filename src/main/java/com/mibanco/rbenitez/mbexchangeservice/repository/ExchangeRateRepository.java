package com.mibanco.rbenitez.mbexchangeservice.repository;

import com.mibanco.rbenitez.mbexchangeservice.Util.LoadInfoDB;
import com.mibanco.rbenitez.mbexchangeservice.entities.Exchange;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ExchangeRateRepository{

    public Flux<Exchange> finAll(){
        return LoadInfoDB.getAll();
    }

    public Mono<Exchange> findExchangeRateType (String moneyOrigin , String moneyDestine){
        return LoadInfoDB.findByMoneyOriginAndDestiny(moneyOrigin, moneyDestine);
    }
}
