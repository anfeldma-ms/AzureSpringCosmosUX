// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.spring.data.cosmostutorial;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CosmosRepository<Project, String> {

    Iterable<Project> findByFirstName(String firstName);

    // Query by search term and earliest start date
    @Query(value = "SELECT * FROM c WHERE CONTAINS(c.id,@searchTerm,true) AND c.projectStartDate > @startDate")
    List<Project> getProjectsBySearchTermAndEarliestDate(@Param("searchTerm") String searchTerm, @Param("startDate") String startDate);

}
