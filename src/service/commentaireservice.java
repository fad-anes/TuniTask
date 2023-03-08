package service;

import entite.commentaire;
import java.util.List;

import entite.offre;
import entite.Users;
import utile.DataSource;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class commentaireservice implements commentaireinterface<commentaire>{
    private Connection conn;
    public commentaireservice(){
        conn=DataSource.getInstance().getCnx();
    }
    @Override
    public void insert(commentaire t, offre o, Users u) {
        String requete = "insert into commentaire(offre_id,commentaire,user_id) values(?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, o.getIdoffre());
            pst.setString(2, t.getCommentaire());
            pst.setInt(3, u.getId());
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
    public void update(int t,commentaire c,offre o, Users u) {
        int r;
        String requete="select idcommentaire from commentaire";
        try {
            Statement st0 = conn.createStatement();
            ResultSet rs = st0.executeQuery(requete);
            while (rs.next()) {
                r = rs.getInt(1);
                if (t == r) {
                    String requete2 =" UPDATE commentaire SET " + "commentaire=?" +
                            "  WHERE user_id= ?" ;

                    try {
                        PreparedStatement pst = conn.prepareStatement(requete2);
                        pst.setString(1, c.getCommentaire());
                        pst.setInt(2, c.getUserid().getId());

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
        String requete="select u.first_name,u.last_name,c.commentaire,u.srcimage,o.idoffre,c.user_id  from commentaire AS c"+
                " JOIN offre AS o ON c.offre_id =o.idoffre "+"JOIN users AS u ON c.user_id =u.id";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                commentaire p=new commentaire(rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("srcimage"),rs.getString("commentaire"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(commentaireservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<commentaire> ReadById(int id) {
        List<commentaire> list=new ArrayList<>();
        String requete="select c.offre_id, c.idcommentaire,u.first_name,u.last_name,c.commentaire,u.srcimage,o.idoffre,c.user_id  from commentaire AS c"+
                " JOIN offre AS o ON c.offre_id =o.idoffre "+"JOIN users AS u ON c.user_id =u.id WHERE c.offre_id ="+id;
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                offre o=new offre(rs.getInt("offre_id"));
                Users u =new Users(rs.getInt("user_id"));
                commentaire p=new commentaire(rs.getInt("idcommentaire"),o,u,rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("srcimage"),rs.getString("commentaire"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(commentaireservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<commentaire> ReadByIdu(int ido, int idu) {
        List<commentaire> list0=new ArrayList<>();
        List<commentaire> list1=new ArrayList<>();
        commentaireservice cs=new commentaireservice();

        list0=cs.ReadById(ido);
        for(int i=0;i<list0.size();i++){
            if(list0.get(i).getUserid().getId()==idu)
                list1.add(list0.get(i));
        }

        return list1;
    }

    @Override
    public Boolean Findid(int id) {
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

    @Override
    public Boolean Findidcm(int id) {
        int test;
        int t=0;
        String requete="select idcommentaire from commentaire";
        try {

            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                test= rs.getInt("idcommentaire");
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
    public int Findcomenus(int id) {

        int test=0;
        String requete="select idcommentaire from commentaire WHERE user_id=" +id;
        try {

            PreparedStatement st=conn.prepareStatement(requete);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                test= rs.getInt("idcommentaire");

            }
        } catch (SQLException ex) {
            Logger.getLogger(offreservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (test==0)
       return 0;
        else return test;
    }

}
