/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.controller;


import java.util.List;

import org.ppsaas.core.system.model.Organization;
import org.ppsaas.core.system.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    @Operation(
            summary = "Organization Registry",
            description = "Register a unique organization, and import employee data to this")
    public Organization registerOrganization(@RequestBody Organization organization) {
        return organizationService.registerOrganization(organization);
    }
    
    @GetMapping
    @Operation(
            summary = "Organizations List",
            description = "Retrieve list of organizations who are registered")
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }
}

