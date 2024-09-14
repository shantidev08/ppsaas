/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.repo;



import org.ppsaas.core.system.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryCustomImpl implements EventRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

	/*
	 * @Autowired private RequestContext requestContext;
	 */

    @Override
    public void saveEvent(String orgId, Event event) {
        //String orgId = requestContext.getOrgId();
        String collectionName = orgId + "-events";
        mongoTemplate.save(event, collectionName);
    }
}

