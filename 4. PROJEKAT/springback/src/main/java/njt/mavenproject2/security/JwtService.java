package njt.mavenproject2.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-ms}")
    private long expirationMs;

    private Key key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generate(UserDetails user, Map<String,Object> extra){
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .addClaims(extra)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    public boolean isValid(String token, UserDetails user){
        try {
            final String un = extractUsername(token);
            return un.equals(user.getUsername()) &&
                   !isExpired(token);
        } catch (JwtException e) {
            return false;
        }
    }
    private boolean isExpired(String token){
        Date exp = Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getExpiration();
        return exp.before(new Date());
    }
}
