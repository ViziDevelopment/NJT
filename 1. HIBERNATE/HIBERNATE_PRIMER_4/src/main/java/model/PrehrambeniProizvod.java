package model;

import jakarta.persistence.Entity;
//import javax.persistence.Entity;

@Entity
public class PrehrambeniProizvod extends Proizvod{
	
	

	private String rokTrajanja;
	private String sastav;
	
	public PrehrambeniProizvod(){
		super();
	}
	
	public PrehrambeniProizvod(int proizvodID, String naziv, double cena, String rokTrajanja, String sastav) {
		super(proizvodID, naziv, cena);
		this.rokTrajanja=rokTrajanja;
		this.sastav=sastav;
	}

	public String getRokTrajanja() {
		return rokTrajanja;
	}

	public void setRokTrajanja(String rokTrajanja) {
		this.rokTrajanja = rokTrajanja;
	}

	public String getSastav() {
		return sastav;
	}

	public void setSastav(String sastav) {
		this.sastav = sastav;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+ ", Rok trajanja: "+rokTrajanja+", Sastav: "+sastav;
	}
	
}
