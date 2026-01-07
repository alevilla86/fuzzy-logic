package com.cenfotec.sbec.fuzzylogic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelRecommendationResponse {
    private double recommendation;
    private int budget;
    private int rating;
}
