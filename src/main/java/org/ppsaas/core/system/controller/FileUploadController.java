/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.controller;



import org.ppsaas.core.system.service.FileProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class FileUploadController {


    @Autowired
    FileProcessingService fileProcessingService;
    @Operation(
            summary = "Organizations Payroll Data ",
            description = "Upload payroll information as CSV, in a format as  \n\n "
            		+ "1, emp101, Bill, Gates, Software Engineer, ONBOARD, 1-11-2022, 10-10-2022, “Bill Gates is going to joinDataOrb on 1st November as a SE.”\r\n"
            		+ "2, emp102, Steve, Jobs, Architect, ONBOARD, 1-10-2022, 10-10-2022, “Steve Jobs joined DataOrb on 1stOctober as an Architect.”\r\n"
            		+ "3, emp103, Martin, Fowler, Software Engineer, ONBOARD, 15-10-2022, 10-10-2022, “Martin has joinedDataOrb as a SE.”\r\n"
            		+ "4, emp102, SALARY, 3000, 10-10-2022, “Oct Salary of Steve.”")
    @PostMapping("/upload")
    public String uploadFile(@RequestParam String orgId, @RequestParam("file") MultipartFile file) {
        // Handle file processing
    	fileProcessingService.processFile(orgId, file);
        return "File uploaded successfully";
    }
}






