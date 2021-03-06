/**
 * Copyright (C) 2015 Jeeva Kandasamy (jkandasa@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mycontroller.standalone.api.jaxrs;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.spi.HttpRequest;
import org.mycontroller.standalone.api.jaxrs.mapper.AuthenticationJson;
import org.mycontroller.standalone.api.jaxrs.mapper.UserCredential;
import org.mycontroller.standalone.api.jaxrs.utils.RestUtils;
import org.mycontroller.standalone.auth.BasicAthenticationSecurityDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 0.0.1
 */

@Path("/rest/authentication")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@PermitAll
public class AuthenticationHandler {
    private static final Logger _logger = LoggerFactory.getLogger(AuthenticationHandler.class);
    @Context HttpRequest request;
    
    @POST
    @Path("/login")
    public Response login(UserCredential userCredential) {
        _logger.debug("User Detail:{}", RestUtils.getUser(request));
        _logger.debug("Login user: " + userCredential.getUsername());
        if (BasicAthenticationSecurityDomain.login(userCredential.getUsername(), userCredential.getPassword())) {
            return RestUtils.getResponse(Status.OK, new AuthenticationJson(true));
        } else {
            return RestUtils.getResponse(Status.UNAUTHORIZED, new AuthenticationJson(false,
                    "Username or password is incorrect"));
        }
    }
}
