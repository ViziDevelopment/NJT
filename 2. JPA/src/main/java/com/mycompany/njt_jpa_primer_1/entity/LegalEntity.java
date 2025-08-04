/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njt_jpa_primer_1.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 
 
/**
 *
 * @author Dules
 */
@Entity
@DiscriminatorValue(value = "PL")
public class LegalEntity extends BusinessPartner{
    @Column(name = "company_identity_number")
    private String companyIdentityNumber;//maticni broj
    @Column(name = "tax_number")
    private String taxNumber;
    //ukoliko je ime kolone identi;no kao i ime atributa ne mora se koristiti anotacija @Column
    private String name;
    //mesto registracije kompanije
    @ManyToOne
    @JoinColumn(name = "zip_code_fk")
    private City city;

    public LegalEntity() {
        super();
    }

    public LegalEntity(String companyIdentityNumber, String taxNumber, String name, City city) {
        super();
        this.companyIdentityNumber = companyIdentityNumber;
        this.taxNumber = taxNumber;
        this.name = name;
        this.city = city;
    }

    public String getCompanyIdentityNumber() {
        return companyIdentityNumber;
    }

    public void setCompanyIdentityNumber(String companyIdentityNumber) {
        this.companyIdentityNumber = companyIdentityNumber;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
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
        sb.append("LegalEntity{");
        sb.append("companyIdentityNumber=").append(companyIdentityNumber);
        sb.append(", taxNumber=").append(taxNumber);
        sb.append(", name=").append(name);
        sb.append(", city=").append(city);
        sb.append('}');
        return sb.toString();
    }
    
    
}
