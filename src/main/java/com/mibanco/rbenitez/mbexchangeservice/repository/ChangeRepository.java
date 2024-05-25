package com.mibanco.rbenitez.mbexchangeservice.repository;

import com.mibanco.rbenitez.mbexchangeservice.entities.Exchange;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface ChangeRepository extends ReactiveCrudRepository<Exchange, Integer> {

    @Query(value = "SELECT * FROM exchange c WHERE c.destine=:destine and c.origin=:origin")
    Mono<Exchange> findByMoneyOriginAndDestiny (
            @Param("destine") String moneyDestine,
            @Param("origin") String moneyOrigin
    );
}
