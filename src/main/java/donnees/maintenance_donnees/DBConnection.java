package donnees.maintenance_donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TreeSet;

public class DBConnection {

    private static Connection connection;
    private static String currentDBname = "maintenance_donnees";

    private DBConnection() {
        // variables a modifier en fonction de la base
        String userName = "root";
        String password = "";
        String serverName = "localhost";
        //Attention, sous MAMP, le port est 8889
        String portNumber = "3306";

        // creation de la connection
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        String urlDB = "jdbc:mysql://" + serverName + ":";
        urlDB += portNumber + "/" + currentDBname;
        try {
            connection = DriverManager.getConnection(urlDB, connectionProps);
        } catch (SQLException e) {
            System.out.println("Erreur dans l'url ou les props de la classe");
        }
    }

    public static synchronized Connection getConnection() {
        if (connection == null) {
            new DBConnection();
        }
        return connection;
    }

    public static void setNomDB(String dbname) {
        if (!dbname.equals(currentDBname)) {
            connection = null;
            currentDBname = dbname;
        }
    }
}
