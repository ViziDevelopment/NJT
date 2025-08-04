/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package  com.mycompany.njt_jpa_primer_1.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author Dules
 */
@Entity
public class Manufacturer implements Serializable{  //AUTO, IDENTITY, TABLE, SEQUENCE
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_MANUFACTURE_ID")
    @TableGenerator(
            name = "GEN_MANUFACTURE_ID",
            table = "GEN_ID", 
            pkColumnName = "PK_GEN", pkColumnValue = "MANUFACTURE_GEN_VALUE",
            initialValue = 0, allocationSize = 5)
    private Long id;
    private String name;
    //definisi vezu ka gradu
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "zip_code_fk")
    private City city;

    public Manufacturer() {
    }

    public Manufacturer(String name, City city) {
        this.name = name;
        this.city = city;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Manufacturer{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", city=").append(city);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
