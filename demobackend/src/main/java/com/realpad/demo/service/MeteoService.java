package com.realpad.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.realpad.demo.service.dto.TempPast10Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class MeteoService implements IsMeteoService {

    private static final Logger logger = LoggerFactory.getLogger(MeteoService.class);

    @Value("${meteo.api.url.past-10-days}")
    String meteoApiUrl;

    @Override
    public TempPast10Days getTempPast10Days() {
        return getMeteoDataTemp10Days(meteoApiUrl, TempPast10Days.class);
    }

    static <T> T getMeteoDataTemp10Days(String url, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        try {
            return mapper.readValue(new URI(url).toURL(), type);
        } catch (IOException e) { //TODO better (central) exc. handling for production release (this is demo)
            logger.error("Failed reading Meteo API data from: " + url);
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            logger.error("Invalid URI syntax: " + url);
            throw new RuntimeException(e);
        }
    }

}
