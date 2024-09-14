/*
 * Copyright (c) 2024 Shantidev Samantaray
 * 
 * All rights reserved. Unauthorized copying, modification, or distribution
 * of this software, via any medium, is strictly prohibited.
 * 
 * This software is provided "as is", without warranty of any kind.
 */
package org.ppsaas.core.system.interceptor;



import org.ppsaas.core.system.component.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestContextInterceptor implements HandlerInterceptor {

    @Autowired
    private RequestContext requestContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Extract orgId from header or URI and set it in the RequestContext
        String orgId = request.getHeader("org-id");
        if (orgId == null) {
            orgId = request.getRequestURI().split("/")[2]; 
        }
        requestContext.setOrgId(orgId);
        return true;
    }
}

