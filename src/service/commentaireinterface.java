package service;
import java.util.List;
public interface commentaireinterface<T> {
    void insert(T t);
    void delete(int t);
    void update(int t,T c);
    List<T> readall();
    T ReadById(int id);
    Boolean Findid(int id);
    Boolean Findidcm(int id);
}
