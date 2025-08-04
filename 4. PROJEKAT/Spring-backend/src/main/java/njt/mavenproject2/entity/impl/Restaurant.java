package njt.mavenproject2.entity.impl;

import java.util.List;
import jakarta.persistence.*;
import njt.mavenproject2.entity.MyEntity;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author vanja
 */

@Entity
@Table(name="restaurants")
public class Restaurant implements MyEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    
    @OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL)
    private List<Product> products;

    public Restaurant() {
    }
    public Restaurant(Long restaurantId) {
            this.id=restaurantId;
    }
 

    public Restaurant(Long id, String name, String address, List<Product> products) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + id + ", name=" + name + ", address=" + address + ", products=" + products + '}';
    }
    
    
}
