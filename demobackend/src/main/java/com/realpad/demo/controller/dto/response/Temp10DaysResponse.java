package com.realpad.demo.controller.dto.response;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class Temp10DaysResponse {

    List<LocalDateTime> labels;
    List<Temp10DaysDataset> datasets;

}
