// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.spring.data.cosmostutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;

@SpringBootApplication
public class ContosoClient implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ContosoClient.class);

    @Autowired
    private ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(ContosoClient.class, args);
    }

    public void run(String... var1) {

        // Test point-reads
        String projMan = viewProject("SannyRicssonEntertainmentProject","2020-05-05T19:21:27.0000000Z");
        if (!projMan.equals("Leif Carlson")) {
            logger.error("Error: project manager {} does not match expected value {}.", projMan, "Leif Carlson");
        } else {
            logger.info("Test: point-read successful!");
        }

        // Test insert/create
        createProject("ContosoInternal","Carl James","2018-01-06T19:21:27.0000000Z");
        logger.info("Test: insert/create successful!");

        // Test update
        createProject("ContosoInternal","Jamison Carlton","2018-01-06T19:21:27.0000000Z");
        logger.info("Test: update successful!");

        // Test delete
        deleteProject("ContosoInternal", "Jamison Carlton", "2018-01-06T19:21:27.0000000Z");
        logger.info("Test: delete successful!");
    }

    private void createProject(String id, String projectManager, String projectStartDate) {
        // Your code here
    }

    private String viewProject(String id, String projectStartDate) {
        // Your code here
        return "";
    }

    private void deleteProject(String id, String projectManager, String projectStartDate) {
        // Your code here
    }
}
