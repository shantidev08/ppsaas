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
import java.util.Map;
import java.util.stream.Collectors;

import org.ppsaas.core.system.model.Employee;
import org.ppsaas.core.system.model.EmployeeFinancialReport;
import org.ppsaas.core.system.model.Event;
import org.ppsaas.core.system.model.MonthlyAmountReleased;
import org.ppsaas.core.system.model.MonthlySalaryReport;
import org.ppsaas.core.system.model.YearlyFinancialReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public long getTotalNumberOfEmployees(String orgId) {
        String collectionName = orgId + "-employees";
        return mongoTemplate.count(new Query(), collectionName);
    }

    public Map<String, List<Employee>> getEmployeesJoinedPerMonth(String orgId) {
        String collectionName = orgId + "-employees";
        List<Employee> employees = mongoTemplate.findAll(Employee.class, collectionName);
        return employees.stream()
            .collect(Collectors.groupingBy(e -> e.getJoiningDate().substring(3, 10)));
    }

    public Map<String, List<Employee>> getEmployeesExitedPerMonth(String orgId) {
        String eventsCollection = orgId + "-events";
        String employeesCollection = orgId + "-employees";

        // Create query to fetch events with type EXIT
        Query exitEventsQuery = new Query();
        exitEventsQuery.addCriteria(Criteria.where("event").is("EXIT"));

        // Fetch events with type EXIT
        List<Event> exitEvents = mongoTemplate.find(exitEventsQuery, Event.class, eventsCollection);

        // Get distinct employee IDs from exit events
        List<String> employeeIds = exitEvents.stream()
                .map(Event::getEmpId)
                .distinct()
                .collect(Collectors.toList());

        // Create query to fetch employee details by IDs
        Query employeeQuery = new Query();
        employeeQuery.addCriteria(Criteria.where("employeeId").in(employeeIds));

        // Fetch employee details for each ID
        List<Employee> employees = mongoTemplate.find(employeeQuery, Employee.class, employeesCollection);

        // Group employees by exit date
        return exitEvents.stream()
                .collect(Collectors.groupingBy(
                        Event::getEventDate,
                        Collectors.mapping(
                                e -> employees.stream()
                                        .filter(emp -> emp.getId().equals(e.getEmpId()))
                                        .findFirst()
                                        .orElse(null),
                                Collectors.toList()
                        )
                ));
    }

    public Map<String, MonthlySalaryReport> getMonthlySalaryReport(String orgId) {
        String collectionName = orgId + "-events";
        List<Event> salaryEvents = mongoTemplate.find(
            new Query(Criteria.where("event").is("SALARY")),
            Event.class,
            collectionName
        );
        return salaryEvents.stream()
            .collect(Collectors.groupingBy(
                e -> e.getEventDate().substring(3, 10),
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> {
                        double totalSalary = list.stream()
                            .mapToDouble(e -> Double.parseDouble(e.getValue()))
                            .sum();
                        long totalEmployees = list.size();
                        return new MonthlySalaryReport(totalSalary, totalEmployees);
                    }
                )
            ));
    }

    public Map<String, EmployeeFinancialReport> getEmployeeFinancialReport(String orgId) {
        String eventsCollectionName = orgId + "-events";
        String employeesCollectionName = orgId + "-employees";

        // Fetch all events for the given orgId
        List<Event> events = mongoTemplate.findAll(Event.class, eventsCollectionName);

        // Group events by employee ID
        Map<String, List<Event>> eventsByEmpId = events.stream()
            .collect(Collectors.groupingBy(Event::getEmpId));

        // Create a map to hold EmployeeFinancialReport
        return eventsByEmpId.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,  // Employee ID
                entry -> {
                    String empId = entry.getKey();
                    List<Event> employeeEvents = entry.getValue();

                    double totalAmountPaid = employeeEvents.stream()
                        .mapToDouble(event -> {
                            try {
                                return Double.parseDouble(event.getValue());
                            } catch (NumberFormatException ex) {
                                return 0.0; // Handle the case where value is not a number
                            }
                        })
                        .sum();

                    // Fetch employee details using a query on employeeId
                    Employee employee = mongoTemplate.findById(empId, Employee.class, employeesCollectionName);

                    // Handle null employee case if needed
                    if (employee == null) {
                        return new EmployeeFinancialReport(empId, "Unknown", "Unknown", totalAmountPaid);
                    }

                    return new EmployeeFinancialReport(
                        empId,
                        employee.getFirstName(),
                        employee.getLastName(),
                        totalAmountPaid
                    );
                }
            ));
    }

    public Map<String, MonthlyAmountReleased> getMonthlyAmountReleased(String orgId) {
        String collectionName = orgId + "-events";
        
        // Fetch all events for the given orgId
        List<Event> events = mongoTemplate.findAll(Event.class, collectionName);

        // Filter events to include only SALARY, BONUS, and REIMBURSEMENT types
        List<Event> filteredEvents = events.stream()
            .filter(e -> "SALARY".equals(e.getEvent()) || 
                         "BONUS".equals(e.getEvent()) || 
                         "REIMBURSEMENT".equals(e.getEvent()))
            .collect(Collectors.toList());

        // Group events by month (format: MM-yyyy)
        return filteredEvents.stream()
            .collect(Collectors.groupingBy(
                e -> e.getEventDate().substring(3, 10), // Extract MM-yyyy from eventDate
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> {
                        double totalAmount = list.stream()
                            .mapToDouble(e -> {
                                try {
                                    return Double.parseDouble(e.getValue());
                                } catch (NumberFormatException ex) {
                                    return 0.0; // Handle the case where value is not a number
                                }
                            })
                            .sum();
                        long totalEmployees = list.stream()
                            .map(Event::getEmpId)
                            .distinct()
                            .count(); // Count unique employees
                        return new MonthlyAmountReleased(totalAmount, totalEmployees);
                    }
                )
            ));
    }


    public Map<String, List<YearlyFinancialReport>> getYearlyFinancialReport(String orgId) {
        String collectionName = orgId + "-events";

        // Fetch all events for the given orgId
        List<Event> events = mongoTemplate.findAll(Event.class, collectionName);

        // Filter events to include only SALARY, BONUS, and REIMBURSEMENT types
        List<Event> filteredEvents = events.stream()
            .filter(e -> "SALARY".equals(e.getEvent()) || 
                         "BONUS".equals(e.getEvent()) || 
                         "REIMBURSEMENT".equals(e.getEvent()))
            .collect(Collectors.toList());

        // Group events by year and convert to list of YearlyFinancialReport
        return filteredEvents.stream()
            .collect(Collectors.groupingBy(
                e -> e.getEventDate().substring(6, 10), // Extract year from eventDate
                Collectors.mapping(
                    e -> new YearlyFinancialReport(
                        e.getEvent(),
                        e.getEmpId(),
                        e.getEventDate(),
                        Double.parseDouble(e.getValue())
                    ),
                    Collectors.toList()
                )
            ));
    }


}

