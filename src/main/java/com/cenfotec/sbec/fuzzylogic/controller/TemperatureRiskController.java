package com.cenfotec.sbec.fuzzylogic.controller;

import com.cenfotec.sbec.fuzzylogic.FuzzyLogicSystemRisk;
import com.cenfotec.sbec.fuzzylogic.dto.TemperatureRiskRequest;
import com.cenfotec.sbec.fuzzylogic.dto.TemperatureRiskResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/temperature")
public class TemperatureRiskController {

    private final FuzzyLogicSystemRisk fuzzyLogicSystem;

    public TemperatureRiskController() {
        this.fuzzyLogicSystem = new FuzzyLogicSystemRisk();
    }

    @PostMapping("/risk")
    public TemperatureRiskResponse getRisk(@RequestBody TemperatureRiskRequest request) {
        log.info("Received temperature risk request: temperature={}C, humidity={}%", 
                request.getTemperature(), request.getHumidity());
        
        double risk = fuzzyLogicSystem.getRecommendation(
                request.getTemperature(), 
                request.getHumidity()
        );
        
        log.info("Risk calculated: {}", risk);
        
        return new TemperatureRiskResponse(
                risk,
                request.getTemperature(),
                request.getHumidity()
        );
    }
}
