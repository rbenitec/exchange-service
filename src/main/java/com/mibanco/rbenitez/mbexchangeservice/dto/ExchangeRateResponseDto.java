package com.mibanco.rbenitez.mbexchangeservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExchangeRateResponseDto extends  ExchangeRateRequestDto{
    private Double amountWithTypeChange;
    private String typeChange;

    public ExchangeRateResponseDto(Double amount, String moneyDestine, String moneyOrigin, Double amountWithTypeChange, String typeChange) {
        super(amount, moneyDestine, moneyOrigin);
        this.amountWithTypeChange = amountWithTypeChange;
        this.typeChange = typeChange;
    }
}
