/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.repo;



import org.ppsaas.core.system.model.Employee;

public interface EmployeeRepositoryCustom {
    void saveEmployee(String orgId, Employee employee);
    // Add other custom methods if needed
}

