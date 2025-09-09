package njt.mavenproject2.servis;

import njt.mavenproject2.dto.impl.*;
import njt.mavenproject2.entity.impl.Role;
import njt.mavenproject2.entity.impl.User;
import njt.mavenproject2.mapper.impl.UserMapper;
import njt.mavenproject2.repository.impl.UserRepository;
import njt.mavenproject2.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import njt.mavenproject2.entity.impl.VerificationToken;
import njt.mavenproject2.repository.impl.VerificationTokenRepository;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwt;
    private final UserRepository users;
    private final VerificationTokenRepository tokens;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    
    private final MailService mail;

    public AuthService(AuthenticationManager authManager, JwtService jwt, UserRepository users, VerificationTokenRepository tokens, PasswordEncoder encoder, UserMapper userMapper, MailService mail) {
        this.authManager = authManager;
        this.jwt = jwt;
        this.users = users;
        this.tokens = tokens;
        this.encoder = encoder;
        this.userMapper = userMapper;
        this.mail = mail;
    }

 

    public UserDto register(RegisterRequest req) throws Exception {
        if (users.existsByUsername(req.getUsername()))
            throw new Exception("Username already taken");
        if (users.existsByEmail(req.getEmail()))
            throw new Exception("Email already taken");

        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPasswordHash(encoder.encode(req.getPassword()));
        u.setRole(Role.USER);

        users.save(u);
        var vt = VerificationToken.of(u, 86400);
        tokens.save(vt);
        
        String verifyUrl = "http://localhost:8080/api/auth/verify?token=" + vt.getToken();
        String body = """
                Zdravo %s,

                Hvala na registraciji. Molimo potvrdite email klikom na link:

                %s

                Link važi 24h.
                """.formatted(u.getUsername(), verifyUrl);

        mail.send(u.getEmail(), "Potvrda naloga", body);
        
        return userMapper.toDto(u);
    }

    public AuthResponse login(LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        // ako ne baci Exception, autentikacija je prošla
        String token = jwt.generate((org.springframework.security.core.userdetails.User) auth.getPrincipal(),
                Map.of("role", "USER")); // možeš dodati i stvarnu rolu
        User me = users.findByUsername(req.getUsername());
        return new AuthResponse(token, userMapper.toDto(me));
    }
}
