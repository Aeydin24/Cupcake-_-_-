/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ndupo
 */
public class Connector 
{
    private static final String URL = "jdbc:mysql://157.230.103.201:3306/lego";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Blomst.95";

    private Connection conn;

    public void setConnection( Connection con ) {
        conn = con;
    }

    public Connection connection() throws ClassNotFoundException, SQLException {
        if ( conn == null ) {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            conn = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        }
        return conn;
    }
}
