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
public class SampleApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(SampleApplication.class);

    @Autowired
    private projectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    public void run(String... var1) {

        viewProject("","");

        createProject("","","");
        String projMan = viewProject("","");
        if (projMan != "") {
            logger.error("Error: project manager {} does not match expected value {}.", "", "");
        }

        createProject("","","");
        projMan = viewProject("","");
        if (projMan != "") {
            logger.error("Error: project manager {} does not match expected value {}.", "", "");
        }
        int projCount = projectSearch("","");
        if (projCount != 3) {
            logger.error("Error: project count {} does not match desired value {}",projCount, 3);
        }
    }

    private void createProject(String id, String projectManager, String projectStartDate) {
        // Your code here
        projectRepository.save(new Project(id, projectManager, projectStartDate));
    }

    private String viewProject(String id, String projectStartDate) {
        // Your code here
        return "";
    }

    private void deleteProject(String id, String projectManager, String projectStartDate) {
        // Your code here
        projectRepository.delete(new Project(id, projectManager, projectStartDate));
    }

    private int projectSearch(String searchTerm, String startDate) {
        // Your code here
        return projectRepository.getProjectsBySearchTermAndEarliestDate(searchTerm, startDate).size();
    }

}
