package com.cenfotec.sbec.fuzzylogic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemperatureRiskResponse {
    private double risk;
    private int temperature;
    private int humidity;
}
