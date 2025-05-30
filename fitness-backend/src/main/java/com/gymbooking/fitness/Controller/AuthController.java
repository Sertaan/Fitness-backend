/*package com.gymbooking.fitness.Controller;

import com.gymbooking.fitness.User.User;
import com.gymbooking.fitness.Repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Registrera en ny användare")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // Kontrollera om användarnamnet redan finns
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Användarnamnet är redan upptaget");
        }

        // Kryptera lösenordet innan sparande
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("Användare registrerad!");
    }

    @Operation(summary = "Logga in med användarnamn och lösenord")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(401).body("Felaktigt användarnamn eller lösenord");
        }

        User user = optionalUser.get();

        // Kontrollera lösenordet med PasswordEncoder
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Felaktigt användarnamn eller lösenord");
        }

        // Här kan du generera en JWT-token eller hantera session
        return ResponseEntity.ok("Inloggning lyckades!");
    }

    // En enkel inre klass för login-förfrågan
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }
}
*/