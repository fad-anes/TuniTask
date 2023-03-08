package service;
import java.util.List;
public interface offreinterface<T> {
    void insert(T t);
    void delete(int t);
    void update(int t,T tt);
    List<T> readall();
    List<T> ReadByIdd(int id);
    T ReadById(int id);
    Boolean Findid(int id);
    Boolean Findidof(int id);
}
