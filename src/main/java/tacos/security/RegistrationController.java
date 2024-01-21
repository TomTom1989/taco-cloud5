package tacos.security;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import tacos.data.UserRepository;
import tacos.data.UserRepository2;


@Controller
@RequestMapping("/register")
public class RegistrationController {
 private UserRepository2 userRepo;
 private PasswordEncoder passwordEncoder;
 
 public RegistrationController(
 UserRepository2 userRepo, PasswordEncoder passwordEncoder) {
 this.userRepo = userRepo;
 this.passwordEncoder = passwordEncoder;
 }
 @GetMapping
 public String registerForm() {
 return "registration";
 }
 @PostMapping
 public String processRegistration(RegistrationForm form) {
 userRepo.save(form.toUser(passwordEncoder));
 return "redirect:/login";
 }
}
