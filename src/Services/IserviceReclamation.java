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
public interface IserviceReclamation <Reclamation> {
    
      public void ajouterReclamation(Reclamation r);
    public void supprimerReclamation(Integer id);
    public void modifierReclamation(Reclamation r);
    public List<Reclamation> afficherReclamations();
}
