/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njt_jpa_primer_1.repository;

import com.mycompany.njt_jpa_primer_1.entity.BusinessPartner;
import com.mycompany.njt_jpa_primer_1.entity.City;
import com.mycompany.njt_jpa_primer_1.entity.Manufacturer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author vanja
 */
public class Repository {
    private EntityManagerFactory emf;

    public Repository() {
         emf = Persistence.createEntityManagerFactory("njt2025_PU");
    }
    
    
    public void saveCity(City city) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(city);
        em.getTransaction().commit();
        System.out.println("Grad je sacuvan.");

        em.close();
    }

    public void removeCity(City city) {
         EntityManager em = emf.createEntityManager();

        City dbCity = em.find(City.class, city.getZipCode());
        if (dbCity!=null){
            em.getTransaction().begin();
            em.remove(dbCity);
            em.getTransaction().commit();
            System.out.println("Obrisan!!!");
        }
        em.close();
      }

    public List<City> findByName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<City> query = em.createNamedQuery("City.findByName", City.class);
        query.setParameter("name", name);
        List<City> result = query.getResultList();
        em.close();
        return result;
    }

    public void saveManufacturer(Manufacturer manufacturer) {
         EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        /* City cityDb = em.find(City.class, manufacturer.getCity().getZipCode());
          if (cityDb == null){
              em.persist(manufacturer.getCity());
          }else{
              em.merge(manufacturer.getCity());
          }
          em.persist(manufacturer);*/
         em.merge(manufacturer);
         
        em.getTransaction().commit();
        System.out.println("Proizvodjac je sacuvan.");

        em.close();
    }

    public void save(BusinessPartner businessPartner) {
         EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.merge(businessPartner);
         em.getTransaction().commit();
        System.out.println("businessPartner je sacuvan.");

        em.close();
        
    }
}
    
    
 
