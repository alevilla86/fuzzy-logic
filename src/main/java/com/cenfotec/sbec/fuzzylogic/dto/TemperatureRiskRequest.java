package com.cenfotec.sbec.fuzzylogic.dto;

import lombok.Data;

@Data
public class TemperatureRiskRequest {
    private int temperature;
    private int humidity;
}
