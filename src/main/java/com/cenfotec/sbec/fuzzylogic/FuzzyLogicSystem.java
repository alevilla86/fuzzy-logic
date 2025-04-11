package com.cenfotec.sbec.fuzzylogic;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.jFuzzyLogic.FIS;

@Slf4j
public class FuzzyLogicSystem {

    private static final String RULES_FILE = "rules2.fcl";

    private FIS fis;

    public FuzzyLogicSystem() {
        // Load the fuzzy logic rules.
        loadRules();
    }

    public double getRecommendation(int temperature, int humidity) {
        // Set the input values for the fuzzy logic system.
        fis.setVariable("temperature", temperature);
        fis.setVariable("humidity", humidity);

        // Evaluate the fuzzy logic system.
        fis.evaluate();

        // Get the output value from the fuzzy logic system.
        double fanSpeed = fis.getVariable("fan_speed").getValue();

        return fanSpeed;
    }

    private void loadRules() {
        String fileName = FuzzyLogicSystem.class.getClassLoader().getResource(RULES_FILE).getPath();
        fis = FIS.load(fileName, false);
        if (fis == null) {
            log.error("Error loading the fuzzy logic rules file.");
        }
    }
} 
