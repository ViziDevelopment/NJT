
package njt.mavenproject2.dto.impl;

import njt.mavenproject2.dto.Dto;
import njt.mavenproject2.entity.impl.Role;

public class UserDto implements Dto {
    private Long id;
    private String username;
    private String email;
    private Role role;

    public UserDto() {}
    public UserDto(Long id, String username, String email, Role role) {
        this.id = id; this.username = username; this.email = email; this.role = role;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}
}
