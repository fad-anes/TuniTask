package gui;

import service.ServiceUsers;
import utile.DataSource;

import java.sql.Connection;

public class main {
    private static Connection conn;
    public static void main(String[] args) {
        conn = (Connection) DataSource.getInstance().getCnx();
    }
}

