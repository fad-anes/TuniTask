package service;
import entite.offre;
import entite.Users;

import java.util.List;
public interface commentaireinterface<T> {
    void insert(T t, offre o, Users u);
    void delete(int t);
    void update(int t,T c,offre o, Users u);
    List<T> readall();
    List<T> ReadById(int id);
    List<T> ReadByIdu(int ido,int idu);
    Boolean Findid(int id);
    Boolean Findidcm(int id);
    int Findcomenus(int id);
}
