/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Reference - Payroll Processing System As A Service(PPSAAS)")                // Custom title
                .version("1.0.0")               // Custom version
                .description("PPC is a Payroll solution provider company who manages the payroll of the various companies from\r\n"
                		+ "small scale to large scale company.\r\n"
                		+ "PPC accepts the employees data from the client in either plain text format (.txt) or csv (.csv) format to\r\n"
                		+ "manage the employee life cycle starting from date of onboarding to date of exit.\r\n"
                		+ "Following are the data points of employees which the client has to send to PPC in either text format or\r\n"
                		+ "csv format. One record is a comma (,) separate collection of these data points and one file can contain\r\n"
                		+ "thousands of such records of any employees for various events.")); // Custom description
    }
}


