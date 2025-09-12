// njt.mavenproject2.repository.impl.OrderRepository.java
package njt.mavenproject2.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import njt.mavenproject2.entity.impl.Order;
import njt.mavenproject2.repository.MyAppRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository implements MyAppRepository<Order, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    @Override
    public Order findById(Long id) throws Exception {
        Order o = em.find(Order.class, id);
        if (o == null) throw new Exception("Order not found: " + id);
        return o;
    }

    @Override @Transactional
    public void save(Order entity) {
        if (entity.getId() == null) em.persist(entity);
        else em.merge(entity);
    }

    @Override @Transactional
    public void deleteById(Long id) {
        Order o = em.find(Order.class, id);
        if (o != null) em.remove(o); // orphanRemoval briše i stavke
    }
}
