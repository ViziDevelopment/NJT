// njt.mavenproject2.entity.impl.VerificationToken
package njt.mavenproject2.entity.impl;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="verification_tokens")
public class VerificationToken {
    @Id
    private String token; // čuvamo UUID kao string primarni ključ

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false, unique=true)
    private User user;

    @Column(nullable=false)
    private Instant expiresAt;

    public static VerificationToken of(User u, long ttlSeconds){
        VerificationToken t = new VerificationToken();
        t.token = UUID.randomUUID().toString();
        t.user = u;
        t.expiresAt = Instant.now().plusSeconds(ttlSeconds);
        return t;
    }

    public boolean isExpired(){ return Instant.now().isAfter(expiresAt); }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

      
    
    
    
    
    
}
