package com.cenfotec.sbec.fuzzylogic;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.jFuzzyLogic.FIS;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FuzzyLogicSystemRisk {

    private static final String RULES_FILE = "risk_rules.fcl";

    private FIS fis;

    public FuzzyLogicSystemRisk() {
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
        try {
            // Try to load from file system first (for development)
            java.net.URL resource = FuzzyLogicSystemRisk.class.getClassLoader().getResource(RULES_FILE);
            if (resource == null) {
                log.error("Rules file not found: {}", RULES_FILE);
                throw new IllegalStateException("Rules file not found: " + RULES_FILE);
            }
            
            String protocol = resource.getProtocol();
            String fileName;
            
            if ("jar".equals(protocol) || "nested".equals(protocol)) {
                // Running from JAR - extract to temp file
                InputStream inputStream = FuzzyLogicSystemRisk.class.getClassLoader().getResourceAsStream(RULES_FILE);
                if (inputStream == null) {
                    log.error("Cannot read rules file from JAR: {}", RULES_FILE);
                    throw new IllegalStateException("Cannot read rules file from JAR: " + RULES_FILE);
                }
                
                File tempFile = File.createTempFile("risk_rules", ".fcl");
                tempFile.deleteOnExit();
                Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                inputStream.close();
                fileName = tempFile.getAbsolutePath();
                log.info("Extracted FCL file to temporary location: {}", fileName);
            } else {
                // Running from file system
                fileName = resource.toURI().getPath();
            }
            
            fis = FIS.load(fileName, false);
            if (fis == null) {
                log.error("Error loading the fuzzy logic rules file.");
                throw new IllegalStateException("Error loading the fuzzy logic rules file.");
            } else {
                log.info("Successfully loaded fuzzy logic rules from: {}", fileName);
            }
        } catch (Exception e) {
            log.error("Error loading the fuzzy logic rules file: {}", e.getMessage(), e);
            throw new IllegalStateException("Failed to load fuzzy logic rules", e);
        }
    }
} 
