package service;
import entite.offre;

import java.util.List;

import entite.Users;
import utile.DataSource;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class offreservice implements offreinterface<offre> {
    private Connection conn;
    public offreservice(){
        conn=DataSource.getInstance().getCnx();
    }
    @Override
    public void insert(offre t) {
        String requete = "insert into offre(user_id,description,titre,salaireH) values(?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, t.getUser_id().getId());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getTitre());
            pst.setFloat(4, t.getSalaireH());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int t) {
        int r;
        String requete="select idoffre from offre";
        try {
            Statement st0 = conn.createStatement();
            ResultSet rs = st0.executeQuery(requete);
            while (rs.next()) {
                r = rs.getInt(1);
                if (t == r) {
                    String requete3 = "DELETE FROM offre WHERE idoffre=" +r;
                    try {
                        Statement st = conn.createStatement();
                        st.execute(requete3);
                        System.out.println("offre supprimé !");

                    } catch (SQLException ex) {
                        Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int t,offre tt) {
        int r;
        String requete="select idoffre from offre";
        try {
            Statement st0 = conn.createStatement();
            ResultSet rs = st0.executeQuery(requete);
            while (rs.next()) {
                r = rs.getInt(1);
                if (t == r) {
                    String requete2 =
                            " UPDATE offre SET " + "description=?," +
                                    " titre =?,salaireH=?  WHERE user_id= " +tt.getUser_id().getId();
                    try {
                        PreparedStatement pst = conn.prepareStatement(requete2);
                        pst.setString(1, tt.getDescription());
                        pst.setString(2, tt.getTitre());
                        pst.setFloat(3, tt.getSalaireH());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<offre> readall() {
        List<offre> list=new ArrayList<>();
        String requete="select o.idoffre,o.user_id,o.description,o.titre,o.salaireH,u.first_name,u.last_name,u.email,u.srcimage from offre AS o" +
              " JOIN users AS u ON o.user_id =u.id ";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Users u=new Users(rs.getInt("user_id"));
                offre p=new offre(rs.getInt("idoffre"),u,
                        rs.getString("description"),rs.getString("titre"),
                        rs.getFloat("salaireH"),rs.getString("first_name"),
                        rs.getString("last_name"),rs.getString("email"),rs.getString("srcimage"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<offre> ReadByIdd(int id) {
        List<offre> list=new ArrayList<>();
        String requete="select o.idoffre,o.user_id ,o.description,o.titre,o.salaireH,u.first_name,u.last_name,u.email,u.srcimage from offre AS o" +
                " JOIN users AS u ON o.user_id =u.id WHERE o.user_id="+id;
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Users u=new Users(rs.getInt("user_id"));
                offre p=new offre(rs.getInt("idoffre"),u,
                        rs.getString("description"),rs.getString("titre"),
                        rs.getFloat("salaireH"),rs.getString("first_name"),
                        rs.getString("last_name"),rs.getString("email"),rs.getString("srcimage"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public offre ReadById(int id) {
        offre p0=new offre();
        String requete0=
        "select o.idoffre,o.user_id, o.description,o.titre,o.salaireH,u.first_name,u.last_name,u.email,u.srcimage from offre AS o" +
                " JOIN users AS u ON o.user_id =u.id WHERE o.idoffre="+id;
        try {

            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete0);
            while(rs.next()){
                Users u=new Users(rs.getInt("user_id"));
                offre p=new offre(rs.getInt("idoffre"),u,rs.getString("description"),rs.getString("titre"),
                        rs.getFloat("salaireH"),rs.getString("first_name"),
                        rs.getString("last_name"),rs.getString("email"),rs.getString("srcimage"));
            p0=p;}
        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p0;
    }

    @Override
    public Boolean Findid(int id) {
        int test;
        int t=0;
        String requete="select id from users";
        try {

            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
               test= rs.getInt("id");
               if(test==id)
                   t=1;
                }
        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(t==1)
            return true;
        else
            return false;
    }

    @Override
    public Boolean Findidof(int id) {
        int test;
        int t=0;
        String requete="select idoffre from offre";
        try {

            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                test= rs.getInt("idoffre");
                if(test==id)
                    t=1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(t==1)
            return true;
        else
            return false;
    }
}
