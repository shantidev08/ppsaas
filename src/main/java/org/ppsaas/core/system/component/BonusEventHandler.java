/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.component;

import org.ppsaas.core.system.model.Event;
import org.ppsaas.core.system.utility.CommonUtil;

public class BonusEventHandler implements IEventHandler{

	@Override
	public Event handleEvent(String record) {
		return CommonUtil.translateCommonEvent(record);
	}

}
