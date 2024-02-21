package com.realpad.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.realpad.demo.controller.DemoResponseCreator;
import com.realpad.demo.controller.dto.response.Temp10DaysResponse;
import com.realpad.demo.service.IsMeteoService;
import com.realpad.demo.service.dto.TempPast10Days;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    @MockBean
    private IsMeteoService meteoService;

    @Autowired
    private MockMvc mockMvc;

    @Value("classpath:meteodata.json")
    Resource meteoDataJsonResource;

    @Autowired
    ObjectMapper springObjectMapper;

    @Autowired
    DemoResponseCreator demoResponseCreator;


    @Test
    void tempPast10Days() throws Exception {

        ObjectMapper meteoObjectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        TempPast10Days meteoData = meteoObjectMapper.readValue(meteoDataJsonResource.getFile(), TempPast10Days.class);
        assertNotNull(meteoData);

        Temp10DaysResponse expectedResponse = demoResponseCreator.temp10Days(meteoData);
        String expectedJson = springObjectMapper.writeValueAsString(expectedResponse);

        when(meteoService.getTempPast10Days()).thenReturn(meteoData);

        mockMvc.perform(get("/api/v1/tempPast10Days"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
        ;
    }

}
