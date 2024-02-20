package com.realpad.demo.controller.dto.response;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class Temp10DaysDataset {

    String label;
    String backgroundColor;
    List<BigDecimal> data;

}
