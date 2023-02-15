package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Datasource {
    private String url = "jdbc:mysql://localhost:3306/tunitask";
    private String user = "root";
    private String pwd = "";
    private Connection cnx;
    private static Datasource instance;
    public Datasource()  {

        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(Datasource.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static Datasource getInstance(){
        if(instance==null)
            instance=new Datasource();
        return instance;
    }
    public Connection getCnx(){
        return cnx;

    }
}
