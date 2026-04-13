package finki.ukim.mk.safenetkids.config;

import finki.ukim.mk.safenetkids.models.AppUser;
import finki.ukim.mk.safenetkids.models.Role;
import finki.ukim.mk.safenetkids.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            createUserIfMissing(appUserRepository, passwordEncoder, "admin", "admin123", Role.ADMIN, "Skopje", "SafeNet School");
            createUserIfMissing(appUserRepository, passwordEncoder, "teacher", "teacher123", Role.TEACHER, "Bitola", "SafeNet School");
            createUserIfMissing(appUserRepository, passwordEncoder, "student", "student123", Role.STUDENT, "Tetovo", "SafeNet School");
        };
    }

    private void createUserIfMissing(AppUserRepository repo,
                                     PasswordEncoder encoder,
                                     String username,
                                     String password,
                                     Role role,
                                     String town,
                                     String school) {
        if (repo.findByUsername(username).isPresent()) {
            return;
        }

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        user.setTown(town);
        user.setSchool(school);
        repo.save(user);
    }
}

