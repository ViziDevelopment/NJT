package main;

import java.util.Iterator;
import java.util.List;

import model.PrehrambeniProizvod;
import model.Proizvod;
import model.TehnickiProizvod;

//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtility;

public class Main {

	/**
	 * @param args
	 */
    public static void main(String[] args) {
        //HibernateDBBR.konekcija();
        Main m=new Main();
        Proizvod p1=new TehnickiProizvod(7, "Pegla", 1500,"2000w, Baterija 12V",1,"Ukljucis, peglas, pakujes.");
        Proizvod p2=new PrehrambeniProizvod(8, "Kafa", 100,"12 mjeseci","Kafa arabika");
        Proizvod p3=new Proizvod(9, "Kesa za vino", 100);
        m.kreirajIUbaciProizvod(p1);
        m.kreirajIUbaciProizvod(p2);
        m.kreirajIUbaciProizvod(p3);
        List l=m.listProizvod();
        Iterator i=l.iterator();
        while(i.hasNext()){
            Object p=i.next();
            System.out.println(p);
        }
        HibernateUtility.getSessionFactory().close();
    }
    
    public void kreirajIUbaciProizvod(Proizvod p){
        Session session=HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(p);
        session.getTransaction().commit();
        
    }
    
    public List listProizvod(){
        Session session=HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        Query query=session.createQuery("from Proizvod");
        List lista=query.list();//session.createQuery("from Proizvod").list();
        session.getTransaction().commit();
        return lista;
    }
}
