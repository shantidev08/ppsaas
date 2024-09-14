/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.model;

public class EmployeeFinancialReport {

    private String employeeId;
    private String name;
    private String surname;
    private double totalAmountPaid;

    // Default constructor
    public EmployeeFinancialReport() {
    }

    // Parameterized constructor
    public EmployeeFinancialReport(String employeeId, String name, String surname, double totalAmountPaid) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.totalAmountPaid = totalAmountPaid;
    }

    // Getters and setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(double totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }
}
