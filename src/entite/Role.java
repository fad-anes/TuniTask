/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;

/**
 *
 * @author abdes
 */

public class Role implements Serializable {

    private Integer idRole;
   
    private String roleName;
   
    private String skills;
    
    private String experience;
    
    private String entreprise;
  
    private Users idUser;
    String langage ;
public Role() {
    }

    public Role(String skills, String experience, String entreprise, String langage) {
        this.skills = skills;
        this.experience = experience;
        this.entreprise = entreprise;
        this.langage = langage;
    }

    public Role(String roleName, String skills, String experience, Users idUser, String langage) {
        this.roleName = roleName;
        this.skills = skills;
        this.experience = experience;
        this.idUser = idUser;
        this.langage = langage;
    }
    

    public Role(Integer idRole, String roleName, String skills, String experience, String entreprise, Users idUser) {
        this.idRole = idRole;
        this.roleName = roleName;
        this.skills = skills;
        this.experience = experience;
        this.entreprise = entreprise;
        this.idUser = idUser;
    }

    public Role(Integer idRole, String roleName, String skills, String experience, String entreprise, Users idUser, String langage) {
        this.idRole = idRole;
        this.roleName = roleName;
        this.skills = skills;
        this.experience = experience;
        this.entreprise = entreprise;
        this.idUser = idUser;
        this.langage = langage;
    }

    public Role(String roleName, String skills, String experience, String entreprise, Users idUser, String langage) {
        this.roleName = roleName;
        this.skills = skills;
        this.experience = experience;
        this.entreprise = entreprise;
        this.idUser = idUser;
        this.langage = langage;
    }

    public Role(String roleName, Users idUser) {
        this.roleName = roleName;
        this.idUser = idUser;
    }

    public Role(String roleName, String skills, String experience, Users idUser) {
        this.roleName = roleName;
        this.skills = skills;
        this.experience = experience;
        this.idUser = idUser;
    }

    public Role(String roleName, String entreprise, Users idUser) {
        this.roleName = roleName;
        this.entreprise = entreprise;
        this.idUser = idUser;
    }
    
    public Role(Integer idRole) {
        this.idRole = idRole;
    }

   

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public void setLangage(String langage) {
        this.langage = langage;
    }

    public String getLangage() {
        return langage;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "Role{" + "idRole=" + idRole + ", roleName=" + roleName + ", skills=" + skills + ", experience=" + experience + ", entreprise=" + entreprise + ", idUser=" + idUser + '}';
    }
  
    
}
