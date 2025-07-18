package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.List;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Dobavljac {

	@Id
	private int dobavljacID;
	private String naziv;
	private String adresa;
	
	@ManyToMany
	  @JoinTable(name = "dobavlja",
	    joinColumns = {
	      @JoinColumn(name="dobavljacID", unique = true)           
	    },
	    inverseJoinColumns = {
	      @JoinColumn(name="proizvodID", unique = true)
	    }
	  )
	  @Cascade (CascadeType.ALL)
	private List<Proizvod> proizvodi;

	
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getDobavljacID() {
		return dobavljacID;
	}

	public void setDobavljacID(int dobavljacID) {
		this.dobavljacID = dobavljacID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Proizvod> getProizvodi() {
		return proizvodi;
	}

	public void setProizvodi(List<Proizvod> proizvodi) {
		this.proizvodi = proizvodi;
	}
	
	@Override
	public String toString() {
		
		return "Dobavljac ID: "+dobavljacID+", Naziv: "+naziv+", Adresa: "+adresa+", Proizvodi: "+proizvodi.toString();
	}
	
}
