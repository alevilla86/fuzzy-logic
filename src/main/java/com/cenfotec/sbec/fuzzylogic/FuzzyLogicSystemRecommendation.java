package com.cenfotec.sbec.fuzzylogic;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.jFuzzyLogic.FIS;

@Slf4j
public class FuzzyLogicSystemRecommendation {

    private static final String RULES_FILE = "recommend_rules.fcl";

    private FIS fis;

    public FuzzyLogicSystemRecommendation() {
        //Load the fuzzy logic rules.
        loadRules();
    }

    public double getRecommendation(int budget, int rating) {
        
        // Set the input values for the fuzzy logic system.
        fis.setVariable("budget", budget);
        fis.setVariable("rating", rating);

        // Evaluate the fuzzy logic system.
        fis.evaluate();

        // Get the output value from the fuzzy logic system.
        double recommendation = fis.getVariable("recommendation").getValue();

        return recommendation;
    }

    private void loadRules() {
        String fileName = FuzzyLogicSystemRecommendation.class.getClassLoader().getResource(RULES_FILE).getPath();
        fis = FIS.load(fileName, false);
        if (fis == null) {
            log.error("Error loading the fuzzy logic rules file.");
            return;
        }
    }

}
