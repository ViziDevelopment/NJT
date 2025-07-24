/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 package com.mycompany.njt_jpa_primer_1.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Dules
 */
@Entity
@DiscriminatorValue(value = "FL")
public class NaturalEntity extends BusinessPartner{
    @Column(name = "personal_identity_number")
    private String personalIdentityNumber;
    private String firstname;
    private String lastname;

    public NaturalEntity() {
    }

    public NaturalEntity(String personalIdentityNumber, String firstname, String lastname) {
        super();
        this.personalIdentityNumber = personalIdentityNumber;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getPersonalIdentityNumber() {
        return personalIdentityNumber;
    }

    public void setPersonalIdentityNumber(String personalIdentityNumber) {
        this.personalIdentityNumber = personalIdentityNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NaturalEntity{");
        sb.append("personalIdentityNumber=").append(personalIdentityNumber);
        sb.append(", firstname=").append(firstname);
        sb.append(", lastname=").append(lastname);
        sb.append('}');
        return sb.toString();
    }
    
    
}
