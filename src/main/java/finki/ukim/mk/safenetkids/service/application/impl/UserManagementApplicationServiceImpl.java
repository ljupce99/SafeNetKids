package finki.ukim.mk.safenetkids.service.application.impl;

import finki.ukim.mk.safenetkids.models.AppUser;
import finki.ukim.mk.safenetkids.models.Role;
import finki.ukim.mk.safenetkids.repository.AppUserRepository;
import finki.ukim.mk.safenetkids.service.application.UserManagementApplicationService;
import finki.ukim.mk.safenetkids.web.dto.UserDisplayDto;
import finki.ukim.mk.safenetkids.web.dto.UserRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserManagementApplicationServiceImpl implements UserManagementApplicationService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserManagementApplicationServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDisplayDto register(UserRegistrationDto registrationDto, String creatorUsername) {
        AppUser creator = this.appUserRepository.findByUsername(creatorUsername)
                .orElseThrow(() -> new IllegalArgumentException("Creator not found"));

        Role requestedRole = Role.valueOf(registrationDto.role().toUpperCase());
        validateRoleCreation(creator.getRole(), requestedRole);

        if (this.appUserRepository.findByUsername(registrationDto.username()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        AppUser user = new AppUser();
        user.setUsername(registrationDto.username());
        user.setPassword(this.passwordEncoder.encode(registrationDto.password()));
        user.setRole(requestedRole);
        user.setTown(registrationDto.town());
        user.setSchool(registrationDto.school());

        AppUser saved = this.appUserRepository.save(user);
        return map(saved);
    }

    @Override
    public List<Role> availableRolesForCreator(String creatorUsername) {
        AppUser creator = this.appUserRepository.findByUsername(creatorUsername)
                .orElseThrow(() -> new IllegalArgumentException("Creator not found"));

        if (creator.getRole() == Role.ADMIN) {
            return List.of(Role.TEACHER, Role.STUDENT);
        }

        if (creator.getRole() == Role.TEACHER) {
            return List.of(Role.STUDENT);
        }

        return List.of();
    }

    private void validateRoleCreation(Role creatorRole, Role requestedRole) {
        if (creatorRole == Role.ADMIN && (requestedRole == Role.TEACHER || requestedRole == Role.STUDENT)) {
            return;
        }

        if (creatorRole == Role.TEACHER && requestedRole == Role.STUDENT) {
            return;
        }

        throw new IllegalArgumentException("You do not have permission to create this role");
    }

    private UserDisplayDto map(AppUser user) {
        return new UserDisplayDto(user.getId(), user.getUsername(), user.getRole(), user.getTown(), user.getSchool());
    }
}

