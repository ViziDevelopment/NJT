 
package njt.mavenproject2.mapper.impl;

import njt.mavenproject2.dto.impl.UserDto;
import njt.mavenproject2.entity.impl.User;
import njt.mavenproject2.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements DtoEntityMapper<UserDto, User> {

    @Override
    public UserDto toDto(User e) {
        if (e == null) return null;
        return new UserDto(e.getId(), e.getUsername(), e.getEmail(), e.getRole());
        // password NIKAD ne punimo u DTO iz entiteta
    }

    @Override
    public User toEntity(UserDto t) {
        if (t == null) return null;
        User u = new User();
        u.setId(t.getId());
        u.setUsername(t.getUsername());
        u.setEmail(t.getEmail());
        // passwordHash se postavlja u servisu (posle encoder-a), ne ovde
        u.setRole(t.getRole());
        return u;
    }
}
