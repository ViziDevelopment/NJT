package njt.mavenproject2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Map;
import njt.mavenproject2.dto.impl.*;
import njt.mavenproject2.entity.impl.User;
import njt.mavenproject2.entity.impl.VerificationToken;
import njt.mavenproject2.repository.impl.UserRepository;
import njt.mavenproject2.repository.impl.VerificationTokenRepository;
import njt.mavenproject2.servis.AuthService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;
    private final VerificationTokenRepository tokens;
    private final UserRepository users;

    public AuthController(AuthService authService, VerificationTokenRepository tokens, UserRepository users) {
        this.authService = authService;
        this.tokens = tokens;
        this.users = users;
    }

 
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterRequest req) throws Exception {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // JWT je stateless -> "logout" na klijentu (obriši token).
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(Authentication auth) throws Exception {
        // auth.getName() je username iz SecurityContext-a
        User u = users.findByUsername(auth.getName());
        UserDto dto = new UserDto(u.getId(), u.getUsername(), u.getEmail(), u.getRole());
        return ResponseEntity.ok(dto);
    }
    
    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String token){
        VerificationToken vt = tokens.find(token);
        if (vt == null) return ResponseEntity.badRequest().body("Neispravan token.");
        if (vt.isExpired()){
            tokens.delete(vt);
            return ResponseEntity.badRequest().body("Token je istekao.");
        }
        User u = vt.getUser();
        u.setEnabled(true);
        users.save(u);      // merge/update po tvom repou
        tokens.delete(vt);  // potroši token

        // možeš vratiti plain tekst, ili redirect na frontend
          return ResponseEntity.ok("Nalog aktiviran. Sada se možete prijaviti.");
       /* return ResponseEntity.status(302)
            .header("Location", frontendUrl + "/login?verified=1")
            .build();*/
    }

    
    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody Map<String,String> body) {
        String email = body.get("email");
        // čak i ako je null – vrati 200 da ne otkrivamo ništa
        authService.requestPasswordReset(email);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String,String> body) {
        String token = body.get("token");
        String password = body.get("password");
        if (password == null || password.length() < 6) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lozinka mora imati bar 6 karaktera.");
        }
        authService.resetPassword(token, password);
        return ResponseEntity.ok("Lozinka je promenjena. Sada se možete prijaviti.");
    }
    
    
    
}
