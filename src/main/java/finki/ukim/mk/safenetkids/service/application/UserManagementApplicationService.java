package finki.ukim.mk.safenetkids.service.application;

import finki.ukim.mk.safenetkids.models.Role;
import finki.ukim.mk.safenetkids.web.dto.UserDisplayDto;
import finki.ukim.mk.safenetkids.web.dto.UserRegistrationDto;

import java.util.List;

public interface UserManagementApplicationService {
    UserDisplayDto register(UserRegistrationDto registrationDto, String creatorUsername);

    List<Role> availableRolesForCreator(String creatorUsername);
}

