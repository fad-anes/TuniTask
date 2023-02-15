import entite.offre;
import service.offreservice;
import utils.Datasource;

public class Main {
    public static void main(String[] args) {
        //offre o= new offre(2,"java devollopper ready for all your projects","java developper",48);
        offre o= new offre(1,"c,c++,java,python","full stack developper",100);
        offreservice s=new offreservice();
        //s.insert(o);
        //s.delete(o);
        //s.update(o);

        System.out.println(s.readall());
        System.out.println(s.ReadById(3));
    }
}