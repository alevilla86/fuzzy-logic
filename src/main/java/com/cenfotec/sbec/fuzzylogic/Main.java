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

        boolean continueLoop = true;

        while (continueLoop) {
            // Prompt the user for input values.
            log.info("Enter the temperature in Celsius (e.g. 25): ");
            int temperature = scanner.nextInt();

            log.info("Enter the humidity percentage (e.g. 60): ");
            int humidity = scanner.nextInt();

            // Get the fan speed recommendation based on the input values.
            double fanSpeed = fuzzyLogicSystem.getRecommendation(temperature, humidity);

            log.info("The risk for temperature {}C and humidity {}% is: {} (0 to 10 scale)", 
                    temperature, humidity, fanSpeed);

            log.info("Do you want to continue? (y/n)");
            String response = scanner.next();
            if (response.equalsIgnoreCase("n")) {
                continueLoop = false;
                scanner.close();
                log.info("Exiting...");
            }
        }
    }
} 