package com.mibanco.rbenitez.mbexchangeservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Exchange {
    @Id
    private Integer id;
    private String origin;
    private String destine;
    private Double rate;
}
