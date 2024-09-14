/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.ppsaas.core.system.model.Event;
import org.ppsaas.core.system.utility.CommonUtil;

public class OnBoardEventhandler implements IEventHandler{

	@Override
	public Event handleEvent(String input) {
		
		// First, split the input string into an array
        String[] parts = input.split(",");



        // Now filter out the 2nd, 3rd, and 4th partitions
        String result = IntStream.range(0, parts.length)
                .filter(i -> i!=2 && i != 3 && i != 4) // Skip 3rd and 4th partitions (Gates, Software Engineer)
                .mapToObj(i -> parts[i]) // Get the remaining partitions
                .collect(Collectors.joining(", ")); // Join them back into a string
		return CommonUtil.translateCommonEvent(result);
	}

}
