package com.cenfotec.sbec.fuzzylogic.controller;

import com.cenfotec.sbec.fuzzylogic.FuzzyLogicSystemRecommendation;
import com.cenfotec.sbec.fuzzylogic.dto.HotelRecommendationRequest;
import com.cenfotec.sbec.fuzzylogic.dto.HotelRecommendationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/hotel")
public class HotelRecommendationController {

    private final FuzzyLogicSystemRecommendation fuzzyLogicSystem;

    public HotelRecommendationController() {
        this.fuzzyLogicSystem = new FuzzyLogicSystemRecommendation();
    }

    @PostMapping("/recommend")
    public HotelRecommendationResponse getRecommendation(@RequestBody HotelRecommendationRequest request) {
        log.info("Received hotel recommendation request: budget={}, rating={}", 
                request.getBudget(), request.getRating());
        
        double recommendation = fuzzyLogicSystem.getRecommendation(
                request.getBudget(), 
                request.getRating()
        );
        
        log.info("Recommendation calculated: {}", recommendation);
        
        return new HotelRecommendationResponse(
                recommendation,
                request.getBudget(),
                request.getRating()
        );
    }
}
