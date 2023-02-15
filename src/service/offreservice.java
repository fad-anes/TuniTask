package service;
import entite.offre;
import java.util.List;
import utils.Datasource;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class offreservice implements offreinterface<offre> {
    private Connection conn;
    public offreservice(){
        conn=Datasource.getInstance().getCnx();
    }
    @Override
    public void insert(offre t) {
        String requete = "insert into offre(freelancer_id,description,titre,salaireH) values(?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, t.getFreelancer_id());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getTitre());
            pst.setFloat(4, t.getSalaireH());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(offre t) {
        String requete3 = "DELETE FROM offre WHERE freelancer_id=" + t.getFreelancer_id();
        try {
            Statement st = conn.createStatement();
            st.execute(requete3);
            System.out.println("offre supprimé !");

        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(offre t) {
        String requete2 =
                " UPDATE offre SET " + "description=?," +
                        " titre =?,salaireH=?  WHERE freelancer_id= " + t.getFreelancer_id();
        try {
            PreparedStatement pst = conn.prepareStatement(requete2);
            pst.setString(1, t.getDescription());
            pst.setString(2, t.getTitre());
            pst.setFloat(3, t.getSalaireH());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<offre> readall() {
        List<offre> list=new ArrayList<>();
        String requete="select * from offre";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                offre p=new offre(rs.getInt(1), rs.getInt(2),
                        rs.getString(3),rs.getString(4),
                        rs.getFloat(5));
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
        String requete0="select * from offre WHERE idoffre="+id;
        try {

            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete0);
            while(rs.next()){
            offre p=new offre(rs.getInt(1), rs.getInt(2),
                    rs.getString(3),rs.getString(4),
                    rs.getFloat(5));
            p0=p;}
        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p0;
    }
}
