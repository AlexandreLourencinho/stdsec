package org.stdsec.domain.user;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.stdsec.domain.user.entity.UserProfileEntity;
import org.stdsec.domain.user.service.UserProfileService;
import org.stdsec.domain.user.web.dto.CreateUserProfileRequestDTO;
import org.stdsec.domain.user.web.dto.CreateUserProfileResponseDTO;

import java.util.List;

import static org.stdsec.controller.config.ControllerConstants.APPLICATION_JSON;

@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UserProfileController {

    @Inject
    UserProfileService service;

    @GET
    public List<UserProfileEntity> list() {
        return service.listAll();
    }

    @POST
    public CreateUserProfileResponseDTO create(CreateUserProfileRequestDTO user) {
        return service.create(user);
    }

}
