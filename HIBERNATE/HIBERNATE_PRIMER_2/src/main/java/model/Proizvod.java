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
@Table(name = "proizvod")
public class Proizvod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int proizvodID;
    private String naziv;
    private double cena;

    @ManyToOne
    @JoinColumn(name = "kategorija_id")
    private Kategorija kategorija;

    public Proizvod() {
    }

    public Proizvod(int proizvodID, String naziv, double cena, Kategorija kategorija) {
        this.proizvodID = proizvodID;
        this.naziv = naziv;
        this.cena = cena;
        this.kategorija = kategorija;
    }

    public int getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(int proizvodID) {
        this.proizvodID = proizvodID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public String toString() {
        return "Proizvod{" + "proizvodID=" + proizvodID + ", naziv=" + naziv + ", cena=" + cena + ", kategorija=" + kategorija.getNaziv() + '}';
    }
    
    
    
}
