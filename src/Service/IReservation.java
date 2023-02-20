package Service;

import java.util.List;
import Entity.Reservation;

public interface IReservation<T> {
    void insert(T e);
    void delete(T e);
    void update(T e);
    List<T> readAll();
    T readById(int id);

}


