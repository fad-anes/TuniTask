/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author abdes
 */

public class Users implements Serializable {


    private Integer id;
    
    private String password;
 
    private String email;
   
    private String firstName;
 
    private String lastName;
  
    private Date dateOfBirth;
 
    private Date createdAt;
   
    private String srcimage;
  
    private Boolean statut ;
    public Users() {
    }

    public Users(Integer id, String password, String email, String firstName, String lastName, Date dateOfBirth, Date createdAt, Boolean statut, String srcimage) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.srcimage = srcimage;
        this.statut = statut;
    }

    public Users(String password, String email, String firstName, String lastName, Date dateOfBirth, Boolean statut) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.statut = statut;
    }

    public Users(String password, String email, String firstName, String lastName, Date dateOfBirth, String srcimage, Boolean statut) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.srcimage = srcimage;
        this.statut = statut;
    }

    public Users(String password, String email, String firstName, String lastName, Date dateOfBirth, String srcimage) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.srcimage = srcimage;
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String email, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }


    public Users(Integer id, String password, String email, String firstName, String lastName, Date dateOfBirth, Date createdAt, String srcimage) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.srcimage = srcimage;
    }
    
    public Users( String password, String email, String firstName, String lastName, Date dateOfBirth) {
     
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        
    }

    public Users(Integer id, String password, String email, String firstName, String lastName, Date dateOfBirth, String srcimage) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.srcimage = srcimage;
    }

    public Users(int id, String pwd, String email, String name, String pname, Date date, Date datec, Boolean statut, String img) {
           this.id = id;
        this.password = pwd;
        this.email = email;
        this.firstName = name;
        this.lastName = pname;
        this.dateOfBirth = date;
        this.createdAt=datec;
        this.statut = statut;
        this.srcimage = img;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public Boolean getStatut() {
        return statut;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSrcimage() {
        return srcimage;
    }

    public void setSrcimage(String srcimage) {
        this.srcimage = srcimage;
    }

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", password=" + password + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth +"statut= "+statut+ ", createdAt=" + createdAt + '}';
    }
  
    
}
