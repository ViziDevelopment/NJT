// njt.mavenproject2.entity.impl.PasswordResetToken
package njt.mavenproject2.entity.impl;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {
    @Id
    private String token; // čuvamo UUID kao PK

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(nullable=false)
    private Instant expiresAt;

    @Column(nullable=false)
    private boolean used = false;

    public static PasswordResetToken of(User u, long ttlSeconds){
        PasswordResetToken t = new PasswordResetToken();
        t.token = UUID.randomUUID().toString();
        t.user = u;
        t.expiresAt = Instant.now().plusSeconds(ttlSeconds);
        t.used = false;
        return t;
    }

    public boolean isExpired(){ return Instant.now().isAfter(expiresAt); }

    // getters/setters
    public String getToken() { return token; }
    public User getUser() { return user; }
    public Instant getExpiresAt() { return expiresAt; }
    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }
}
