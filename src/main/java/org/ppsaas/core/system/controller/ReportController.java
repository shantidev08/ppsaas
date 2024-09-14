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
import java.util.Map;

import org.ppsaas.core.system.model.Employee;
import org.ppsaas.core.system.model.EmployeeFinancialReport;
import org.ppsaas.core.system.model.MonthlyAmountReleased;
import org.ppsaas.core.system.model.MonthlySalaryReport;
import org.ppsaas.core.system.model.YearlyFinancialReport;
import org.ppsaas.core.system.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/total-employees")
    @Operation(
            summary = "Total Employ Count",
            description = "Count total number of employees")
    public long getTotalNumberOfEmployees(@RequestParam String orgId) {
        return reportService.getTotalNumberOfEmployees(orgId);
    }

    @GetMapping("/employees-joined")
    @Operation(
            summary = "Month wise joining",
            description = "Month wise total number of employees joined the organization with employee details like emp id,\r\n"
            		+ "designation, name, surname.")
    public Map<String, List<Employee>> getEmployeesJoinedPerMonth(@RequestParam String orgId) {
        return reportService.getEmployeesJoinedPerMonth(orgId);
    }


    @GetMapping("/employees-exited") 
    @Operation(
            summary = "Month wise exiting",
            description = "Month wise Total number of employees exit organization with employee details like name, surname.")
    public Map<String, List<Employee>> getEmployeesExitedPerMonth(@RequestParam String orgId) { 
    	return reportService.getEmployeesExitedPerMonth(orgId); 
    }


    @GetMapping("/monthly-salary-report")
    @Operation(
            summary = "Monthly salary report",
            description = "Monthly Salary report based on organisation in Month, Total Salary, Total employees")
    public Map<String, MonthlySalaryReport> getMonthlySalaryReport(@RequestParam String orgId) {
        return reportService.getMonthlySalaryReport(orgId);
    }

    @GetMapping("/employee-financial-report")
    @Operation(
            summary = "Employee wise financial report",
            description = "Employee wise financial report as Employee Id, Name, Surname, Total amount paid")
    public Map<String, EmployeeFinancialReport> getEmployeeFinancialReport(@RequestParam String orgId) {
        return reportService.getEmployeeFinancialReport(orgId);
    }

    @GetMapping("/monthly-amount-released")
    @Operation(
            summary = "Monthly amount released",
            description = "Monthly amount released as Month, Total Amount (Salary + Bonus + REIMBURSEMENT), Total employees")
    public Map<String, MonthlyAmountReleased> getMonthlyAmountReleased(@RequestParam String orgId) {
        return reportService.getMonthlyAmountReleased(orgId);
    }

    @GetMapping("/yearly-financial-report")
    @Operation(
            summary = "Yearly financial report",
            description = "Event, Emp Id, Event Date, Event value")
    public Map<String, List<YearlyFinancialReport>> getYearlyFinancialReport(@RequestParam String orgId) {
        return reportService.getYearlyFinancialReport(orgId);
    }
}

