package com.cenfotec.sbec.fuzzylogic;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.jFuzzyLogic.FIS;

@Slf4j
public class FuzzyLogicSystem2 {

    private static final String RULES_FILE = "rules.fcl";

    private FIS fis;

    public FuzzyLogicSystem2() {
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
        String fileName = FuzzyLogicSystem2.class.getClassLoader().getResource(RULES_FILE).getPath();
        fis = FIS.load(fileName, false);
        if (fis == null) {
            log.error("Error loading the fuzzy logic rules file.");
            return;
        }
    }

}
