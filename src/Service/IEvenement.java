package Service;
import java.util.List;


public interface IEvenement<Evenement> {
    void insert(Evenement e);
    void delete(Evenement e);
    void update( Evenement e);
    List<Entity.Evenement> readAll();
    Evenement readById(int id);
}