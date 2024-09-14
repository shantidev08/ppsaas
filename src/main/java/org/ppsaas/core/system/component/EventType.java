/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.component;

import java.util.HashMap;
import java.util.Map;

public enum EventType {

    ONBOARD("ONBOARD",9, new OnBoardEventhandler()),
    SALARY("SALARY",6, new SalaryEventHandler()),
	EXIT("EXIT",6, new ExitEventHandler()),
	REIMBURSEMENT("REIMBURSEMENT",6, new ReimbursementEventHandler()),
	BONUS("BONUS",6, new BonusEventHandler());

    private String eventType;
    private IEventHandler handler;
    private int requiredFields;
    
    private static final Map<String, EventType> eventTypeMap = new HashMap<>();

    // Static block to populate the map for quick lookup
    static {
        for (EventType type : EventType.values()) {
            eventTypeMap.put(type.eventType, type);
        }
    }

    EventType(String eventType,int requiredFields, IEventHandler handler) {
        this.eventType = eventType;
        this.handler = handler;
        this.requiredFields = requiredFields;
        	
    }

    public String getEventType() {
        return eventType;
    }

    public IEventHandler getHandler() {
        return handler;
    }
    
    public static IEventHandler getHandlerByEventType(String eventType) {
        for (EventType type : EventType.values()) {
            if (type.getEventType().equalsIgnoreCase(eventType)) {
                return type.getHandler();
            }
        }
        throw new IllegalArgumentException("No handler found for event type: " + eventType);
    }
    
    public static EventType findMatchingEventType(String line) {
        for (Map.Entry<String, EventType> entry : eventTypeMap.entrySet()) {
            if (line.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;  // Return null if no matching event type found
    }

	public int getRequiredFields() {
		return requiredFields;
	}

	public void setRequiredFields(int requiredFields) {
		this.requiredFields = requiredFields;
	}
    
    
}
