/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.njt_jpa_primer_1;

import com.mycompany.njt_jpa_primer_1.entity.BusinessPartner;
import com.mycompany.njt_jpa_primer_1.entity.City;
import com.mycompany.njt_jpa_primer_1.entity.LegalEntity;
import com.mycompany.njt_jpa_primer_1.entity.Manufacturer;
import com.mycompany.njt_jpa_primer_1.repository.Repository;
import java.util.List;

/**
 *
 * @author vanja
 */
public class Njt_jpa_primer_1 {
    private Repository repository;

    public Njt_jpa_primer_1() {
          repository = new Repository();
    }
    
    
    public static void main(String[] args) {
        Njt_jpa_primer_1 obj = new Njt_jpa_primer_1();
       
        obj.saveBusinessPartner();
        
        
       // obj.saveManufacturer();
        
        
     //   obj.vratiGradove();
        
        // obj.saveCity();
       
        //obj.removeCity();
         
    }

    private void saveCity() {
         City city = new City((long)33000,"Beograd3");
         repository.saveCity(city);
    }

    private void removeCity() {
         City city = new City((long)11000,"Beograd");
          repository.removeCity(city);
    }

    private void vratiGradove() {
         List<City> cities = repository.findByName("Beograd");
         for (City city : cities) {
             System.out.println(city);
        }
    }

    private void saveManufacturer() {
        Manufacturer manufacturer = new Manufacturer("Proizvodjac - 1", new City(28000L, "Grad  28000"));
        repository.saveManufacturer(manufacturer);
    }

    private void saveBusinessPartner() {
         BusinessPartner businessPartner = new LegalEntity(
                                "mb-1x",
                                        "tx-1x", "PL - 1x", 
                                new City(28888L, "Grad  28888") );
       // businessPartner.setId(1l);
        repository.save(businessPartner);
        
        
        
    }
}
