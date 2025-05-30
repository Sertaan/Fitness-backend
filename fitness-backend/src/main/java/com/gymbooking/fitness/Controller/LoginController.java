package com.gymbooking.fitness.Controller;

import com.gymbooking.fitness.User.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = "http://localhost:8080",
        allowedHeaders = "*",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})

public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        boolean authenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (!authenticated) {
            return ResponseEntity.status(401).body("Fel användarnamn eller lösenord");
        }
        session.setAttribute("username", loginRequest.getUsername());
        return ResponseEntity.ok("Inloggning lyckades");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Utloggad");
    }

    @GetMapping("/current-user")
    public ResponseEntity<String> currentUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return ResponseEntity.ok(username);
        } else {
            return ResponseEntity.status(401).body("Ej inloggad");
        }
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
