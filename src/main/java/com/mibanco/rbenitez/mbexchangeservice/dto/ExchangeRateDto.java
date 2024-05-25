package com.mibanco.rbenitez.mbexchangeservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExchangeRateDto {
    private String origin;
    private String destine;
    private Double rate;
}
