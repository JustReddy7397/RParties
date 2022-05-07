package ga.justreddy.wiki.api.database;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

public final class SQL {

    public static Connection con;

    public static void connect(String path) {
        if (!isConnected()) {
            try {
                Class.forName("org.h2.Driver");
                con = DriverManager.getConnection(" jbdc:h2:"  + path);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void close() {
        if (isConnected()) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {
        return con != null;
    }

    public static void createTable() {
        if (isConnected()) {
            try {
                getConnection().createStatement().executeQuery("CREATE TABLE IF NOT EXISTS parties (leader VARCHAR(100), members VARCHAR(1000000), muted BOOLEAN(100))");
            } catch (SQLException ex) {
                ex.printStackTrace();

            }

        }
    }

    public static void update(String qry) {
        try{
            getConnection().createStatement().executeUpdate(qry);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static ResultSet getResult(String qry) {
        try{
           return getConnection().createStatement().executeQuery(qry);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static PreparedStatement prepareStatement(String qry) {
        try{
            return getConnection().prepareStatement(qry);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection() {
        return con;
    }
}
