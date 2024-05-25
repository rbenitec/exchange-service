package com.mibanco.rbenitez.mbexchangeservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExchangeRateRequestDto {

    private Double amount;

    @NotEmpty(message = "moneyDestine value is required")
    private String moneyDestine;

    @NotEmpty(message = "moneyOrigin value is required")
    private String moneyOrigin;
}
