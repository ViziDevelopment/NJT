// njt.mavenproject2.repository.impl.VerificationTokenRepository
package njt.mavenproject2.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import njt.mavenproject2.entity.impl.VerificationToken;
import org.springframework.stereotype.Repository;

@Repository
public class VerificationTokenRepository {
    @PersistenceContext private EntityManager em;

    @Transactional
    public void save(VerificationToken vt){ 
        em.persist(vt); 
    }

    public VerificationToken find(String token){ 
        return em.find(VerificationToken.class, token); 
    }

    @Transactional
    public void delete(VerificationToken vt){ 
        em.remove(em.contains(vt)? vt : em.merge(vt)); 
    }
}
