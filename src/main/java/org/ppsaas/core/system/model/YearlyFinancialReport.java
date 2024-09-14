/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.model;

public class YearlyFinancialReport {

    private String event;
    private String empId;
    private String eventDate;
    private double eventValue;

    // Default constructor
    public YearlyFinancialReport() {
    }

    // Parameterized constructor
    public YearlyFinancialReport(String event, String empId, String eventDate, double eventValue) {
        this.event = event;
        this.empId = empId;
        this.eventDate = eventDate;
        this.eventValue = eventValue;
    }

    // Getters and Setters
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public double getEventValue() {
        return eventValue;
    }

    public void setEventValue(double eventValue) {
        this.eventValue = eventValue;
    }
}

