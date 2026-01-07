package com.cenfotec.sbec.fuzzylogic.controller;

import com.cenfotec.sbec.fuzzylogic.FuzzyLogicSystemRisk;
import com.cenfotec.sbec.fuzzylogic.dto.TemperatureRiskRequest;
import com.cenfotec.sbec.fuzzylogic.dto.TemperatureRiskResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/temperature")
public class TemperatureRiskController {

    private final FuzzyLogicSystemRisk fuzzyLogicSystem;

    public TemperatureRiskController(FuzzyLogicSystemRisk fuzzyLogicSystem) {
        this.fuzzyLogicSystem = fuzzyLogicSystem;
    }

    @PostMapping("/risk")
    public ResponseEntity<?> getRisk(@RequestBody TemperatureRiskRequest request) {
        log.info("Received temperature risk request: temperature={}C, humidity={}%", 
                request.getTemperature(), request.getHumidity());
        
        // Validate input
        if (request.getHumidity() < 0 || request.getHumidity() > 100) {
            return ResponseEntity.badRequest().body("Humidity must be between 0 and 100");
        }
        
        double risk = fuzzyLogicSystem.getRecommendation(
                request.getTemperature(), 
                request.getHumidity()
        );
        
        log.info("Risk calculated: {}", risk);
        
        return ResponseEntity.ok(new TemperatureRiskResponse(
                risk,
                request.getTemperature(),
                request.getHumidity()
        ));
    }
}
