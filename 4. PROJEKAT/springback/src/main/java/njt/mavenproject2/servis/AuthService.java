package njt.mavenproject2.servis;

import jakarta.transaction.Transactional;
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
import njt.mavenproject2.entity.impl.PasswordResetToken;
import njt.mavenproject2.entity.impl.VerificationToken;
import njt.mavenproject2.repository.impl.PasswordResetTokenRepository;
import njt.mavenproject2.repository.impl.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Value;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwt;
    private final UserRepository users;
    private final VerificationTokenRepository tokens;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    
    private final PasswordResetTokenRepository resetTokens;
    
    
    private final MailService mail;

   @Value("${app.frontend.url}")
    private String frontendUrl; 
    
    
    public AuthService(AuthenticationManager authManager, JwtService jwt, UserRepository users, VerificationTokenRepository tokens, PasswordEncoder encoder, UserMapper userMapper, PasswordResetTokenRepository resetTokens, MailService mail) {
        this.authManager = authManager;
        this.jwt = jwt;
        this.users = users;
        this.tokens = tokens;
        this.encoder = encoder;
        this.userMapper = userMapper;
        this.resetTokens = resetTokens;
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
    
    
    
    @Transactional
    public void requestPasswordReset(String email){
        User u = users.findByEmail(email);  
        if (u == null) {
            // ne otkrivamo da li email postoji
            return;
        }

        // napravi token (30min = 1800s)
        PasswordResetToken t = PasswordResetToken.of(u, 1800);
        resetTokens.save(t);

        String link = frontendUrl + "/reset?token=" + t.getToken();
        String html = buildResetEmailHtml(u.getUsername(), link);

        mail.sendHtml(u.getEmail(), "Reset lozinke", html);
    }

    private String buildResetEmailHtml(String username, String link){
        return """
        <div style="font-family: Inter,Segoe UI,Arial,sans-serif; max-width: 560px; margin: 0 auto; padding: 24px; background:#f7f8fb;">
          <div style="background:#fff; border:1px solid #e6e8ef; border-radius:14px; padding:24px;">
            <h2 style="margin:0 0 8px; color:#0f172a;">Pozdrav %s,</h2>
            <p style="margin:0 0 16px; color:#475569;">Dobili smo zahtev za reset lozinke. Klikni na dugme ispod da postaviš novu lozinku.</p>
            <div style="text-align:center; margin:24px 0;">
              <a href="%s" style="display:inline-block; padding:12px 18px; background:#2563eb; color:#fff; text-decoration:none; border-radius:10px; font-weight:700;">
                Postavi novu lozinku
              </a>
            </div>
            <p style="margin:0 0 6px; color:#64748b; font-size:14px;">Ako dugme ne radi, otvori ovaj link u pregledaču:</p>
            <p style="margin:0; word-break:break-all; color:#0f172a; font-size:13px;">%s</p>
            <hr style="border:none; border-top:1px solid #e6e8ef; margin:20px 0;">
            <p style="margin:0; color:#94a3b8; font-size:12px;">Link važi 30 minuta. Ako nisi tražio reset, ignoriši ovaj mejl.</p>
          </div>
        </div>
        """.formatted(username, link, link);
    }

    public void resetPassword(String token, String password) {
        PasswordResetToken t = resetTokens.find(token);
        if (t == null || t.isUsed() || t.isExpired()) {
            throw new RuntimeException("Neispravan ili istekao token.");
        }
        User u = t.getUser();
        u.setPasswordHash(encoder.encode(password));
        users.save(u);

        t.setUsed(true);              // označi potrošenim
        resetTokens.save(t);          // ili resetTokens.delete(t);
    }
    
    
    
    
    
    
    
    
    
    
}
