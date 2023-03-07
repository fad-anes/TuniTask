/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author hp
 */
public interface IserviceReponserec <Reponserec> {
    public void ajouterReponserec(Reponserec r);
    public void supprimerReponserec(Integer id);
    public void modifierReponserec(Reponserec r);
    public List<Reponserec> afficherReponserec() ;
    public String getReponceById(Integer id);
}
