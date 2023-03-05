import entite.commentaire;
import entite.offre;
import entite.rate;
import entite.user;
import service.commentaireservice;
import service.offreservice;
import service.rateservice;
import utils.Datasource;

public class Main {
    public static void main(String[] args) {
       // offre o= new offre(2,"yyy++","dfgdfg",800);
        offre o= new offre(21);
        user u=new user(25);
       // rate r=new rate(o,u,0.5f);
        commentaire c=new commentaire(o,"lkm!",u);
        commentaireservice cs=new commentaireservice();
        offreservice s=new offreservice();
        rateservice rs=new rateservice();
       // rs.insert(r);

        System.out.println(rs.calcul(21));
        //s.insert(o);
       //s.delete(14);
        //s.update(14,o);
        //cs.insert(c,o,u);
        //cs.delete(2);
        //cs.update(4,c);
        //System.out.println(cs.Findcomenus(28,18));
        //System.out.println(s.readall());
       //System.out.println(s.ReadById(17));
       // System.out.println(cs.readall());
       // System.out.println(cs.ReadById(18));
       // System.out.println(s.Findid(2));

        //System.out.println(cs.Findidcm(5));

    }
}