package stringProcessor.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelo
 */
public class DatabaseConnection {

    private static Connection con;
    //private static String dbUrl = "jdbc:derby:C:/Users/Jelo/Desktop/thesis; create=true; user=jelo; password=jeleee;" ;
    private static String dbUrl = "jdbc:derby:classpath:stringProcessor/database/thesis; create=true; user=jelo; password=jeleee;";

    public DatabaseConnection() {
        try {
            //loading driver
            Class.forName("org.t.derby.jdbc.EmbeddedDriver");
        } catch (Exception e) {
            System.out.println("Class not found" + e);
        }

        System.out.println("JDBC class found");
    }

    public static Connection connectDB() {

        con = null;

        try {
            //connecting to the database
            con = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Database Connected");
        return con;
    }

    public static Connection getConnection() {
        return con;
    }
}
