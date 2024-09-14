/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "organizations")
public class Organization {

    @Id
    private String id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String email;

    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}


