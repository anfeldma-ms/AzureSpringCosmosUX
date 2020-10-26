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
        projMan = viewProject("ContosoInternal","2018-01-06T19:21:27.0000000Z");
        if (!projMan.equals("Carl James")) {
            logger.error("Error: project manager {} does not match expected value {}.", projMan, "Carl James");
        } else {
            logger.info("Test: insert/create successful!");
        }


        // Test update
        createProject("ContosoInternal","Jamison Carlton","2018-01-06T19:21:27.0000000Z");
        projMan = viewProject("ContosoInternal","2018-01-06T19:21:27.0000000Z");
        if (!projMan.equals("Jamison Carlton")) {
            logger.error("Error: project manager {} does not match expected value {}.", projMan, "Jamison Carlton");
        } else {
            logger.info("Test: update successful!");
        }

        // Test query
        int projCount = projectSearch("Project","2017-01-02T18:21:27.0000000Z");
        if (projCount != 3) {
            logger.error("Error: project count {} does not match desired value {}", projCount, 3);
        } else {
            logger.info("Test: query successful!");
        }
    }

    private void createProject(String id, String projectManager, String projectStartDate) {
        // Your code here
        projectRepository.save(new Project(id, projectManager, projectStartDate));
    }

    private String viewProject(String id, String projectStartDate) {
        // Your code here
        Project project = null;
        Iterator<Project> project_iterator = projectRepository.findByIdAndProjectStartDate(id, projectStartDate).iterator();
        if (project_iterator.hasNext()) {
            project = project_iterator.next();
            logger.info("viewProject: {}", project);
        } else {
            logger.error("Failed to find project.");
        }
        return project.getProjectManager();
    }

    private void deleteProject(String id, String projectManager, String projectStartDate) {
        // Your code here
        projectRepository.delete(new Project(id, projectManager, projectStartDate));
    }

    private int projectSearch(String searchTerm, String startDate) {
        // Your code here

        Iterator<Project> project_iterator = projectRepository.getProjectsBySearchTermAndEarliestDate(searchTerm, startDate).iterator();

        int num_proj = 0;

        while (project_iterator.hasNext()) {
            Project project = project_iterator.next();
            num_proj++;
            logger.info("projectSeach: {}", project);
        }

        return num_proj;
    }

}
