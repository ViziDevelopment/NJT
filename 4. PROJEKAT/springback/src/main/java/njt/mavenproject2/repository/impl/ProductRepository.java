/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import njt.mavenproject2.entity.impl.Product;
import njt.mavenproject2.repository.MyAppRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vanja
 */
@Repository
public class ProductRepository implements MyAppRepository<Product, Long>{
    @PersistenceContext
    private EntityManager entityManager;
    
    
    @Override
    public List<Product> findAll() {
         return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) throws Exception {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            throw new Exception("Proizvod nije pronađen!");
        }
        return product;
    }

    @Override
    @Transactional
    public void save(Product entity) {
         if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
     @Transactional
    public void deleteById(Long id) {
         Product product = entityManager.find(Product.class, id);
        if (product != null) {
              entityManager.remove(product);
        }
    }
    
    
 
 
    

    public List<Product> findByRestaurant(Long restaurantId) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.restaurant.id = :rid", Product.class)
                 .setParameter("rid", restaurantId)
                 .getResultList();
    }
 

    
}
