package com.realpad.demo.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TempPast10Days {

    BigDecimal latitude;
    BigDecimal longitude;
    BigDecimal generationtime_ms;
    Integer utc_offset_seconds;
    String timezone;
    String timezone_abbreviation;
    BigDecimal elevation;
    HourlyUnits hourly_units;
    Hourly hourly;

}
