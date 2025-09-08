package njt.mavenproject2.entity.impl;

import jakarta.persistence.*;
import njt.mavenproject2.entity.MyEntity;

@Entity
@Table(name="users",
       uniqueConstraints = {
         @UniqueConstraint(name="uk_user_username", columnNames="username"),
         @UniqueConstraint(name="uk_user_email",    columnNames="email")
       })
public class User implements MyEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=50)
    private String username;

    @Column(nullable=false, length=120)
    private String email;

    @Column(nullable=false) // čuvamo hash, ne plain lozinku
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private Role role = Role.USER;

    public User() {}
    public User(Long id){ this.id=id; }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getUsername(){ return username; }
    public void setUsername(String username){ this.username = username; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }
    public String getPasswordHash(){ return passwordHash; }
    public void setPasswordHash(String passwordHash){ this.passwordHash = passwordHash; }
    public Role getRole(){ return role; }
    public void setRole(Role role){ this.role = role; }
}
