package service;
import java.util.List;
public interface offreinterface<T> {
    void insert(T t);
    void delete(T t);
    void update(T t);
    List<T> readall();
    T ReadById(int id);
    Boolean Findid(int id);
    Boolean Findidof(int id);
}
