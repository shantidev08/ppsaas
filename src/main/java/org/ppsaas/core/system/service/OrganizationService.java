/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.service;

import java.util.List;

import org.ppsaas.core.system.model.Organization;
import org.ppsaas.core.system.repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization registerOrganization(Organization organization) {
        // Generate ID in the format PPS-NAME
        String id = "PPS-" + organization.getName().toUpperCase().replaceAll("\\s+", "");
        organization.setId(id);
        return organizationRepository.save(organization);
    }
    
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}
