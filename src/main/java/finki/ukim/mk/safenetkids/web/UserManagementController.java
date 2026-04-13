package finki.ukim.mk.safenetkids.web;

import finki.ukim.mk.safenetkids.service.application.UserManagementApplicationService;
import finki.ukim.mk.safenetkids.web.dto.UserRegistrationDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/management/users")
public class UserManagementController {

    private final UserManagementApplicationService userManagementApplicationService;

    public UserManagementController(UserManagementApplicationService userManagementApplicationService) {
        this.userManagementApplicationService = userManagementApplicationService;
    }

    @GetMapping("/new")
    public String registrationPanel(Authentication authentication, Model model) {
        model.addAttribute("availableRoles",
                this.userManagementApplicationService.availableRolesForCreator(authentication.getName()));
        return "user-registration";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String role,
                           @RequestParam String town,
                           @RequestParam String school,
                           Authentication authentication,
                           RedirectAttributes redirectAttributes) {
        try {
            this.userManagementApplicationService.register(
                    new UserRegistrationDto(username, password, role, town, school),
                    authentication.getName()
            );
            redirectAttributes.addFlashAttribute("message", "User created successfully");
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }

        return "redirect:/management/users/new";
    }
}

