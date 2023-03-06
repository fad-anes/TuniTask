package service;
import entite.rate;
import utils.Datasource;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class rateservice implements rateinterface<rate>{
    private Connection conn;

    public rateservice() {
        conn=Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(rate r) {
        String requete = "insert into rate(offre_id,user_id,rate) values(?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, r.getOffre_id().getIdoffre());
            pst.setInt(2, r.getUser_id().getId());
            pst.setInt(3, r.getRate());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(rateservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public float calcul(int id) {
        int q=0;
        float rst=0;
        String requete="select rate from rate WHERE offre_id= "+id;
        try {
            Statement st0 = conn.createStatement();
            ResultSet rs = st0.executeQuery(requete);
            while (rs.next()) {
                q=q+1;
                rst=rst+rs.getInt("rate");
            }
        }catch (SQLException ex) {
                Logger.getLogger(rateservice.class.getName()).log(Level.SEVERE, null, ex);}
        if(q==0)
        return 0;
        else
            return rst/q;
    }


}
