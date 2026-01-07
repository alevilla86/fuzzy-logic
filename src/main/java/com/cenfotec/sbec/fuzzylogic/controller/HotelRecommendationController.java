package com.cenfotec.sbec.fuzzylogic.controller;

import com.cenfotec.sbec.fuzzylogic.FuzzyLogicSystemRecommendation;
import com.cenfotec.sbec.fuzzylogic.dto.HotelRecommendationRequest;
import com.cenfotec.sbec.fuzzylogic.dto.HotelRecommendationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/hotel")
public class HotelRecommendationController {

    private final FuzzyLogicSystemRecommendation fuzzyLogicSystem;

    public HotelRecommendationController(FuzzyLogicSystemRecommendation fuzzyLogicSystem) {
        this.fuzzyLogicSystem = fuzzyLogicSystem;
    }

    @PostMapping("/recommend")
    public ResponseEntity<?> getRecommendation(@RequestBody HotelRecommendationRequest request) {
        log.info("Received hotel recommendation request: budget={}, rating={}", 
                request.getBudget(), request.getRating());
        
        // Validate input
        if (request.getBudget() < 0) {
            return ResponseEntity.badRequest().body("Budget must be non-negative");
        }
        if (request.getRating() < 0 || request.getRating() > 10) {
            return ResponseEntity.badRequest().body("Rating must be between 0 and 10");
        }
        
        double recommendation = fuzzyLogicSystem.getRecommendation(
                request.getBudget(), 
                request.getRating()
        );
        
        log.info("Recommendation calculated: {}", recommendation);
        
        return ResponseEntity.ok(new HotelRecommendationResponse(
                recommendation,
                request.getBudget(),
                request.getRating()
        ));
    }
}
