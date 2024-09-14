/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

import org.ppsaas.core.system.component.EventType;
import org.ppsaas.core.system.component.IEventHandler;
import org.ppsaas.core.system.model.Employee;
import org.ppsaas.core.system.model.Event;
import org.ppsaas.core.system.repo.EmployeeRepositoryCustom;
import org.ppsaas.core.system.repo.EventRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileProcessingService {

    @Autowired
    private EventRepositoryCustom eventRepository;

    @Autowired
    private EmployeeRepositoryCustom employeeRepository;

    public CompletableFuture<String> processFile(String orgId, MultipartFile file) {
        return CompletableFuture.supplyAsync(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    processLine(orgId, line);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Error occurred while processing file";
            }
            return "File processed successfully";
        });
    }

    private void processLine(String orgId, String line) {
        // Check if the line contains a valid event type from the enum
        EventType eventType = EventType.findMatchingEventType(line);
        
        if (eventType != null) {
            String[] data = line.split(",");

            if (eventType == EventType.ONBOARD) {
                // Check if the data matches the required number of fields for onboarding
                if (data.length == EventType.ONBOARD.getRequiredFields()) {
                    // Process employee onboarding
                    processOnboardEvent(orgId, data);
                } else {
                    System.err.println("Invalid format for ONBOARD event");
                }
            }
            if (data.length == eventType.getRequiredFields()) {
                // Process other events using the correct event handler
                IEventHandler handler = eventType.getHandler();
                Event event = handler.handleEvent(line);
                eventRepository.saveEvent(orgId, event);
            } else {
                System.err.println("Invalid format for event type: " + eventType.getEventType());
            }
        } else {
            System.err.println("No matching event type found for line: " + line);
        }
    }

    private void processOnboardEvent(String orgId, String[] data) {
        try {
            // Assuming the onboarding format is: SequenceNo, EmpID, EmpFName, EmpLName, Designation, Event, JoiningDate, EventDate, Notes
            String empId = data[1].trim();
            String firstName = data[2].trim();
            String lastName = data[3].trim();
            String designation = data[4].trim();
            String joiningDate = data[6].trim();  // Assuming the 7th field is joining date
            
            // Create and save Employee entity
            Employee employee = new Employee();
            employee.setId(empId);
            employee.setOrgId(orgId);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setDesignation(designation);
            employee.setJoiningDate(joiningDate);  // Set other employee fields as needed
            
            employeeRepository.saveEmployee(orgId, employee);
            


        } catch (Exception e) {
            System.err.println("Error occurred while processing ONBOARD event: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
