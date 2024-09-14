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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

	/*
	 * @Autowired private RequestContext requestContext;
	 */

    @Override
    public void saveEmployee(String orgId,Employee employee) {
        //String orgId = requestContext.getOrgId();
        String collectionName = orgId + "-employees";
        mongoTemplate.save(employee, collectionName);
    }

    // Implement other custom methods if needed
}


