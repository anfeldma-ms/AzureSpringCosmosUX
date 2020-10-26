// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.spring.data.cosmostutorial;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "ProjectRecords", ru = "10000")
public class Project {
    private String id;
    private String projectManager;

    @PartitionKey
    private String projectStartDate;

    public Project() {
    }

    public Project(String id, String projectManager, String projectStartDate) {
        this.id = id;
        this.projectManager = projectManager;
        this.projectStartDate = projectStartDate;
    }

    @Override
    public String toString() {
        return String.format("com.azure.spring.data.cosmostutorial.Project: %s %s, %s", id, projectManager, projectStartDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectManager() {
        return this.projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }
}
