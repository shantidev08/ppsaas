/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.model;

public class MonthlySalaryReport {

    private double totalSalary;
    private long totalEmployees;

    // Default constructor
    public MonthlySalaryReport() {
    }

    // Parameterized constructor
    public MonthlySalaryReport(double totalSalary, long totalEmployees) {
        this.totalSalary = totalSalary;
        this.totalEmployees = totalEmployees;
    }

    // Getters and setters
    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }
}


