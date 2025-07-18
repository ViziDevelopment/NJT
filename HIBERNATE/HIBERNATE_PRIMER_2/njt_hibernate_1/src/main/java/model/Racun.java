/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vanja
 */
@Entity
public class Racun {
    
    @Id
    private int racunID; 
    private Date datum;
    
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="racun")  
    private List<StavkaRacuna> stavkeRacuna;

    public Racun() {
    }

    public Racun(int racunID, Date datum, List<StavkaRacuna> stavkeRacuna) {
        this.racunID = racunID;
        this.datum = datum;
        this.stavkeRacuna = stavkeRacuna;
    }

    public int getRacunID() {
        return racunID;
    }

    public void setRacunID(int racunID) {
        this.racunID = racunID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public List<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(List<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }

    @Override
    public String toString() {
        return "Racun{" + "racunID=" + racunID + ", datum=" + datum + ", stavkeRacuna=" + stavkeRacuna + '}';
    }
    
    
    
}
