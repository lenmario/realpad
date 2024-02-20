package com.realpad.demo.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Hourly {

    List<LocalDateTime> time;
    List<BigDecimal> temperature_2m;

}
