package njt.mavenproject2.security;

import njt.mavenproject2.entity.impl.User;
import njt.mavenproject2.repository.impl.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository users;

    public AppUserDetailsService(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = users.findByUsername(username);
        if (u == null) throw new UsernameNotFoundException("Not found");
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPasswordHash(),
                u.isEnabled(),
                true, // accountNonExpired
                true,// credentialsNonExpired
                true,// accountNonLocked
                List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole().name()))
        );
    }
}
