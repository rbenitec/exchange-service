package com.mibanco.rbenitez.mbexchangeservice.Util;

import com.mibanco.rbenitez.mbexchangeservice.entities.Exchange;
import com.mibanco.rbenitez.mbexchangeservice.enums.Money;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class LoadInfoDB {

    private static final List<Exchange> exchangesRates;

    static {
        exchangesRates = Arrays.asList(
                new Exchange(1,"USD","EUR",0.85, "03-07-2023"),
                new Exchange(2,"EUR","USD",1.18, "03-07-2023"),
                new Exchange(3,"PEN","USD",3.74, "03-07-2023"),
                new Exchange(4,"PEN","EUR",4.09, "03-07-2023"),
                new Exchange(5,"USD","PEN",0.35, "03-07-2023"),
                new Exchange(6,"EUR","PEN",0.24, "03-07-2023")
        );
    }

    /**
     * Retornar todos los tipos de cambio.
     * @return
     */

    public static Flux<Exchange> getAll(){
        return Flux.fromIterable(exchangesRates);
    }

    /**
     * Metodo para obtener tipo de cambio a realizar.
     * @param moneyDestine
     * @param moneyOrigin
     * @return
     */
    public static Mono<Exchange> findByMoneyOriginAndDestiny (String moneyOrigin, String moneyDestine){
        return Flux.fromIterable(exchangesRates)
                .filter(exchange ->
                        exchange.getMoneyOrigin().contains(moneyOrigin) &&
                                exchange.getMoneyDestine().contains(moneyDestine))
                .next();
    }
}
