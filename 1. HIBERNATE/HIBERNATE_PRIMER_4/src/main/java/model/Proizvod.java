package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;



@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Proizvod {
		
	@Id
	    private int proizvodID;
	    private String naziv;
	    private double cena;
	    
	    public Proizvod() {
	    }
	    
	    public Proizvod(int proizvodID, String naziv, double cena) {
	        this.proizvodID=proizvodID;
	        this.naziv=naziv;
	        this.cena=cena;
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
	    
	    public String toString(){
	        return "ProizvodID: "+getProizvodID()+"\tNaziv: "+naziv+"\tCena: "+cena;
	    }

	    public int getProizvodID() {
	        return proizvodID;
	    }

	    public void setProizvodID(int proizvodID) {
	        this.proizvodID = proizvodID;
	    }
	    
}
