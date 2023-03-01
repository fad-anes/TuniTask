import entite.commentaire;
import entite.offre;
import entite.user;
import service.commentaireservice;
import service.offreservice;
import utils.Datasource;

public class Main {
    public static void main(String[] args) {
       // offre o= new offre(2,"yyy++","dfgdfg",800);
        offre o= new offre(18,"pret pour vos offre","full stack developper",50);
        user u=new user(28);
        commentaire c=new commentaire(o,"lkm!",u);
        commentaireservice cs=new commentaireservice();
        offreservice s=new offreservice();
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