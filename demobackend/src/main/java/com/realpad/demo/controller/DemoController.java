package com.realpad.demo.controller;

import com.realpad.demo.controller.dto.response.Temp10DaysResponse;
import com.realpad.demo.service.IsMeteoService;
import com.realpad.demo.service.dto.TempPast10Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DemoController {

    private final IsMeteoService meteoService;
    private final DemoResponseCreator demoResponseCreator;

    @Autowired
    public DemoController(IsMeteoService meteoService, DemoResponseCreator demoResponseCreator) {
        this.meteoService = meteoService;
        this.demoResponseCreator = demoResponseCreator;
    }

    @GetMapping(value = "/tempPast10Days", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Temp10DaysResponse tempPast10Days() {
        TempPast10Days meteoResult = meteoService.getTempPast10Days();
        return demoResponseCreator.temp10Days(meteoResult);
    }

}
