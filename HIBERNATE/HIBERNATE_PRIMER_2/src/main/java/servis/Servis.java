/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servis;

import java.util.ArrayList;
import java.util.List;
import model.Kategorija;
import model.Proizvod;
import model.Racun;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author vanja
 */
public class Servis {
     
    public static void kreirajKategoriju(String naziv){
        Kategorija k = new Kategorija();
        k.setNaziv(naziv);
        try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
            sesija.beginTransaction();
            sesija.persist(k);    //sesija.saveOrUpdate(k);
            sesija.getTransaction().commit();
            System.out.println("KREIRAN OBJ U BAZI"+k);
        }
         
    }    
    public static List<Kategorija> vratiSveKategorije(){
         try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
           return sesija.createQuery("FROM Kategorija",Kategorija.class).list();
        }
        
    }
     
    public static void kreirajProizvod(String naziv, double cena, Kategorija k){ //int k_id
       Proizvod p = new Proizvod();
       p.setNaziv(naziv);
       p.setCena(cena);
       if(k==null){
           System.out.println("GRESKA, FALI KATEGORIJA");
           return;
       }
       p.setKategorija(k); // Kategorija k = new Kategorija(); k.setId(k_id);
               
        try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
            sesija.beginTransaction();
            sesija.persist(p);    //sesija.saveOrUpdate(k);
            sesija.getTransaction().commit();
            System.out.println("KREIRAN OBJ U BAZI"+p);
        }
         
    }  
     
    public static List<Proizvod> vratiSveProizvode(){
         try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
           return sesija.createQuery("FROM Proizvod",Proizvod.class).list();
        }
        
    }
    
    public static void obrisiProizvod(int proizvodId){ //Proizvod p
        
         try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
               sesija.beginTransaction();
            Proizvod p = sesija.get(Proizvod.class,proizvodId);
            if(p!=null){
                 sesija.remove(p);
                 sesija.getTransaction().commit();
            }else{
                System.out.println("NE POSTOJI TAJ PROIZVOD");
                sesija.getTransaction().rollback();
            }
            
        }
        
    }
    public static void azurirajProizvod(int proizvodId, String noviNaziv, double novaCena, Kategorija novaKategorija) {
        try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
            sesija.beginTransaction();

            Proizvod p = sesija.get(Proizvod.class, proizvodId);
            if (p != null) {
                p.setNaziv(noviNaziv);
                p.setCena(novaCena);
                p.setKategorija(novaKategorija);

                sesija.merge(p); // može i saveOrUpdate(p);
                sesija.getTransaction().commit();
                System.out.println("AZURIRAN: " + p);
            } else {
                System.out.println("Proizvod sa ID " + proizvodId + " ne postoji.");
                sesija.getTransaction().rollback();
            }
        }
    }
 
    public static List<Proizvod> sortirajPoCeniRastuce() {
        try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
            return sesija
                    .createQuery("FROM Proizvod ORDER BY cena ASC", Proizvod.class)
                    .list();
        }
    }
    public static List<Proizvod> pretraziProizvodePoNazivu(String deoNaziva) {
        try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
            return sesija
                    .createQuery("FROM Proizvod WHERE naziv LIKE :ime ", Proizvod.class)
                    .setParameter("ime", "%" + deoNaziva + "%")
                    .list();
        }
    }

    public static List<Proizvod> vratiProizvodeZaKategoriju(int kategorijaId) {
        try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
            return sesija
                    .createQuery("FROM Proizvod WHERE kategorija.id = :kid", Proizvod.class)
                    .setParameter("kid", kategorijaId)
                    .list();
        }
    }

    
    public static void kreirajRacun(Racun r){
        try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
            sesija.beginTransaction();
            sesija.persist(r);    //sesija.saveOrUpdate(k);
            sesija.getTransaction().commit();
            System.out.println("KREIRAN OBJ U BAZI"+r);
        }
    }
    
    
    
    
    
    
    
    public static List<Racun> vratiSveRacune(){
         try (Session sesija = HibernateUtil.getSessionFactory().openSession()) {
           return sesija.createQuery("FROM Racun",Racun.class).list();
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
        
}
    
    
    
    
 
