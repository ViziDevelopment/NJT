
import java.util.List;
import model.Kategorija;
import model.Proizvod;
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
        
        //sesija.close();
        
         servis.Servis.kreirajKategoriju("Voce");
         servis.Servis.kreirajKategoriju("Povrce");

        System.out.println("-------------------------------------------");
        List<Kategorija> kategorije = Servis.vratiSveKategorije();
        for (Kategorija k : kategorije) {
            System.out.println(k);
        }
         System.out.println("-------------------------------------------");
   
        Servis.kreirajProizvod("jabuka", 100, kategorije.get(0));
        Servis.kreirajProizvod("krompir", 90, kategorije.get(1));
         System.out.println("-------------------------------------------");
        List<Proizvod> proizvodi = Servis.vratiSveProizvode();
        for (Proizvod p : proizvodi) {
            System.out.println(p);
                    
        }
         System.out.println("-------------------------------------------");
    
       //  Servis.obrisiProizvod(1);
      //   System.out.println("BRISANJE ZAVRSENO");
      //   proizvodi = Servis.vratiSveProizvode();
      //  for (Proizvod p : proizvodi) {
     //       System.out.println(p);
                    
     //   }
        //  System.out.println("-------------------------------------------");
          
          
          Servis.azurirajProizvod(2, "KROMPIR", 100, kategorije.get(1));
          
          System.out.println("-------------------------------------------");
           proizvodi = Servis.vratiSveProizvode();
        for (Proizvod p : proizvodi) {
            System.out.println(p);
                    
        }
          
    }
    
    
    
    
}
