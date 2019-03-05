package data;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ibenk
 */
public class DBConnector {

    private static String driver    = "com.mysql.cj.jdbc.Driver";
    private static String url       = "jdbc:mysql://localhost:3306/cupcake";
    private static String user      = "root";
    private static String password  = "root";
    private static Connection conn  = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return conn;
    }
}
