package com.realpad.demo.controller;

import com.realpad.demo.controller.dto.response.Temp10DaysDataset;
import com.realpad.demo.controller.dto.response.Temp10DaysResponse;
import com.realpad.demo.service.MeteoService;
import com.realpad.demo.service.dto.TempPast10Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DemoController {

    @Autowired
    MeteoService meteoService;

    @GetMapping(value = "/tempPast10Days", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Temp10DaysResponse tempPast10Days() {
        TempPast10Days meteoResult = meteoService.getTempPast10Days();

        List<LocalDateTime> labels = meteoResult.getHourly().getTime();
        List<Temp10DaysDataset> datasets = List.of(
            new Temp10DaysDataset("Temperature Past 10 Days", "#f87979", meteoResult.getHourly().getTemperature_2m())
        );

        return new Temp10DaysResponse(labels, datasets);
    }

}
