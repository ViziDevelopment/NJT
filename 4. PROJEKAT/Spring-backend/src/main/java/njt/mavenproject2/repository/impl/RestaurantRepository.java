/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import njt.mavenproject2.entity.impl.Restaurant;
import njt.mavenproject2.repository.MyAppRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vanja
 */
@Repository
public class RestaurantRepository implements MyAppRepository<Restaurant,Long> {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Restaurant> findAll() {
         return entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant findById(Long id) throws Exception {
        Restaurant restaurant = entityManager.find(Restaurant.class, id);
        if (restaurant == null) {
            throw new Exception("Restoran nije pronađen!");
        }
        return restaurant;
    }

    @Override 
    @Transactional
    public void save(Restaurant entity) {
        
        if (entity.getId() == null) {
            entityManager.persist(entity);
             
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
         Restaurant restaurant = entityManager.find(Restaurant.class, id);
        if (restaurant != null) {
            entityManager.remove(restaurant);
        }
    }
    
    
    
}
