package com.realpad.demo.controller;

import com.realpad.demo.controller.dto.response.Temp10DaysDataset;
import com.realpad.demo.controller.dto.response.Temp10DaysResponse;
import com.realpad.demo.service.dto.TempPast10Days;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DemoResponseCreator {

    public Temp10DaysResponse temp10Days(TempPast10Days meteoData) {
        List<LocalDateTime> labels = meteoData.getHourly().getTime();
        List<Temp10DaysDataset> datasets = List.of(
                new Temp10DaysDataset("Temperature Past 10 Days", "#f87979", meteoData.getHourly().getTemperature_2m())
        );

        return new Temp10DaysResponse(labels, datasets);
    }

}
