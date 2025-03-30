package com.cenfotec.sbec.fuzzylogic;

import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        
        log.info("Starting Fuzzy Logic System...");
        Scanner scanner = new Scanner(System.in);

        // Create an instance of the fuzzy logic system.
        FuzzyLogicSystem fuzzyLogicSystem = new FuzzyLogicSystem();

        log.info("Fuzzy Logic System Initialized.");

        var continueLoop = true;

        while(continueLoop == true) {
            // Prompt the user for input values.
            log.info("Enter the budget (>0): ");
            int budget = scanner.nextInt();
            log.info("Enter the rating (0-10): ");
            int rating = scanner.nextInt();

            // Get the recommendation based on the input values.
            double recommendation = fuzzyLogicSystem.getRecommendation(budget, rating);

            log.info("The recommendation score of a hotel with budget {} and rating of {} is: {} out of 10", 
            budget, rating, recommendation);

            log.info("Do you want to continue? (y/n)");
            String response = scanner.next();
            if(response.equalsIgnoreCase("n")) {
                continueLoop = false;
                scanner.close();
                log.info("Exiting...");
            }
        }
        
    }

}