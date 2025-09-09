 
package njt.mavenproject2.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import njt.mavenproject2.entity.impl.PasswordResetToken;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordResetTokenRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(PasswordResetToken prt) {
        em.persist(prt);
    }

    public PasswordResetToken find(String token) {
        return em.find(PasswordResetToken.class, token);
    }

    @Transactional
    public void delete(PasswordResetToken prt) {
        em.remove(em.contains(prt) ? prt : em.merge(prt));
    }

    
}
