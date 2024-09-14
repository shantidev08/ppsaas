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

@Document(collection = "#{@requestContext.getOrgId() + '-events'}")
public class Event {

    @Id
    private String id;
    private String empId;
    private String event;
    private String value; // This could be a date, salary amount, etc.
    private String eventDate;
    private String notes;

    // Constructors
    public Event() {}

    public Event(String id, String empId, String event, String value, String eventDate, String notes) {
        this.id = id;
        this.empId = empId;
        this.event = event;
        this.value = value;
        this.eventDate = eventDate;
        this.notes = notes;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", empId='" + empId + '\'' +
                ", event='" + event + '\'' +
                ", value='" + value + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}

