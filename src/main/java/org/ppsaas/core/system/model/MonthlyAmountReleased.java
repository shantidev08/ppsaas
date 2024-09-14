/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.model;

public class MonthlyAmountReleased {

    private double totalAmount;
    private long totalEmployees;

    // Default constructor
    public MonthlyAmountReleased() {
    }

    // Parameterized constructor
    public MonthlyAmountReleased(double totalAmount, long totalEmployees) {
        this.totalAmount = totalAmount;
        this.totalEmployees = totalEmployees;
    }

    // Getters and setters
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }
}
