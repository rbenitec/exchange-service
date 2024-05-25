package com.mibanco.rbenitez.mbexchangeservice.entities;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Exchange {
    private Integer id;
    private String moneyOrigin;
    private String moneyDestine;
    private Double rate;
    private String date;
}
