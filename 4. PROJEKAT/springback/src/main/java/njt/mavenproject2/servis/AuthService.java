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

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwt;
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public AuthService(AuthenticationManager authManager, JwtService jwt,
                       UserRepository users, PasswordEncoder encoder, UserMapper userMapper) {
        this.authManager = authManager;
        this.jwt = jwt;
        this.users = users;
        this.encoder = encoder;
        this.userMapper = userMapper;
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
