
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Kategorija;
import model.Proizvod;
import model.Racun;
import model.StavkaRacuna;
import org.hibernate.Session;
import servis.Servis;
import util.HibernateUtil;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

/**
 *
 * @author vanja
 */
public class Njt_hibernate_1 {

    public static void main(String[] args) {
         //Session sesija = HibernateUtil.getSessionFactory().openSession();
        
       //  sesija.close();
        
        // servis.Servis.kreirajKategoriju("Voce");
      //   servis.Servis.kreirajKategoriju("Povrce"); 
             List<Kategorija> kategorije = Servis.vratiSveKategorije();
         
       // Servis.kreirajProizvod("jabuka", 100, kategorije.get(0));
      //  Servis.kreirajProizvod("krompir", 90, kategorije.get(1)); 
        List<Proizvod> proizvodi = Servis.vratiSveProizvode();
        for (Proizvod p : proizvodi) {
            System.out.println(p);     
        }
    
        List<StavkaRacuna> listaStavki = new ArrayList<>();
        StavkaRacuna s1 = new StavkaRacuna();
        s1.setKolicina(10) ;
        s1.setProizvod(proizvodi.get(0));
        s1.setRB(1);
        
         StavkaRacuna s2 = new StavkaRacuna();
        s2.setKolicina(20) ;
        s2.setProizvod(proizvodi.get(1));
        s2.setRB(2);
        
        listaStavki.add(s1);        listaStavki.add(s2);

        Racun r = new Racun(1, new Date(), listaStavki);
        
       // Servis.kreirajRacun(r);
        
        
        
        List<Racun> sviRacuni = Servis.vratiSveRacune();
        for (Racun racun : sviRacuni) {
            System.out.println(racun);
        }
        
    }
    
    
}
