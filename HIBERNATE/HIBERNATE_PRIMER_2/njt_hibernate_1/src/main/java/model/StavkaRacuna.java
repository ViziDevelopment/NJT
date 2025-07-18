/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.*;

/**
 *
 * @author vanja
 */
@Entity
public class StavkaRacuna {
    @ManyToOne                    //
    @JoinColumn(name="racunID") 
    private Racun racun;  
    
    @Id
    private int RB;
    
    @ManyToOne
    @JoinColumn(name="proizvodID")
    private Proizvod proizvod;
    
    private double kolicina;

    public StavkaRacuna() {
          RB=0;
        proizvod=null;
        kolicina=0;
    }

    public StavkaRacuna(Racun racun, int RB, Proizvod proizvod, double kolicina) {
        this.racun = racun;
        this.RB = RB;
        this.proizvod = proizvod;
        this.kolicina = kolicina;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRB() {
        return RB;
    }

    public void setRB(int RB) {
        this.RB = RB;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "StavkaRacuna{" + "racun=" + racun + ", RB=" + RB + ", proizvod=" + proizvod + ", kolicina=" + kolicina + '}';
    }
    
    
    
    
}
