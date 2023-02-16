package service;

import entite.commentaire;
import java.util.List;

import entite.offre;
import utils.Datasource;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class commentaireservice implements commentaireinterface<commentaire>{
    private Connection conn;
    public commentaireservice(){
        conn=Datasource.getInstance().getCnx();
    }
    @Override
    public void insert(commentaire t) {
        String requete = "insert into commentaire(offre_id,commentaire) values(?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, t.getOffre_id());
            pst.setString(2, t.getCommentaire());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(commentaireservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(int t) {
        int r;
        String requete="select idcommentaire from commentaire";
        try {
            Statement st0 = conn.createStatement();
            ResultSet rs = st0.executeQuery(requete);
            while (rs.next()) {
                r = rs.getInt(1);
                if (t == r) {
                    String requete3 = "DELETE FROM commentaire WHERE idcommentaire=" + t;
                    try {
                        Statement st = conn.createStatement();
                        st.execute(requete3);
                        System.out.println("commentaire supprimé !");

                    } catch (SQLException ex) {
                        Logger.getLogger(commentaireservice.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            }
        }catch(SQLException ex){
                Logger.getLogger(commentaireservice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }




    @Override
    public void update(int t,commentaire c) {
        int r;
        String requete="select idcommentaire from commentaire";
        try {
            Statement st0 = conn.createStatement();
            ResultSet rs = st0.executeQuery(requete);
            while (rs.next()) {
                r = rs.getInt(1);
                if (t == r) {
                    String requete2 =
                            " UPDATE commentaire SET " + "offre_id=?," +
                                    " commentaire =? WHERE offre_id= " + c.getOffre_id();
                    try {
                        PreparedStatement pst = conn.prepareStatement(requete2);
                        pst.setInt(1, c.getOffre_id());
                        pst.setString(2, c.getCommentaire());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(commentaireservice.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(commentaireservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<commentaire> readall() {
        List<commentaire> list=new ArrayList<>();
        String requete="select * from commentaire";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                commentaire p=new commentaire(rs.getInt(1), rs.getInt(2),
                        rs.getString(3));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(commentaireservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public commentaire ReadById(int id) {
        commentaire p0=new commentaire();
        String requete0="select * from commentaire WHERE idcommentaire="+id;
        try {

            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete0);
            while(rs.next()){
                commentaire p=new commentaire(rs.getInt(1), rs.getInt(2),
                        rs.getString(3));
                p0=p;}
        } catch (SQLException ex) {
            Logger.getLogger(commentaireservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p0;
    }
}
