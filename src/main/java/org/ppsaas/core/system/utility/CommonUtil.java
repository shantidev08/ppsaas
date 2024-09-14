/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.utility;

import java.util.UUID;

import org.ppsaas.core.system.model.Event;

public class CommonUtil {
	public static Event translateCommonEvent(String record) {
		// TODO Auto-generated method stub
	    // Split the record string by commas
	    String[] parts = record.split(",");

	    // Assuming that the parts array follows the format given
	    String empId = parts[1].trim();
	    String event = parts[2].trim();
	    String value = parts[3].trim(); // The salary amount in this case
	    String eventDate = parts[4].trim();
	    String notes = parts[5].trim(); // Removing any quotes from the notes
	    String eventId = UUID.randomUUID().toString().split("-")[0];
	    // Create and return the Event object
	    return new Event(eventId, empId, event, value, eventDate, notes);
	}
}
