package persistence;

import business.entities.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseConnector {

    private static DatabaseConnector instance;

    private String username;
    private String password;
    private String url;
    private Connection conn;

    private DatabaseConnector(String username, String password, String ip, int port, String database) {
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
    }

    public static DatabaseConnector getInstance(Config config) {
        if (instance == null) {
            instance = new DatabaseConnector(
                    config.getUsuariAcces(),
                    config.getContrasenyaBases(),
                    config.getDireccioIP(),
                    config.getPort(),
                    config.getNomBases()
            );
            instance.connect();
        }
        return instance;
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database successfully!");
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Disconnected from database.");
            }
        } catch (SQLException e) {
            System.out.println("Error disconnecting: " + e.getMessage());
        }
    }

    public void insertQuery(String query) {
        try {
            conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error executing insert: " + e.getMessage());
        }
    }

    public void updateQuery(String query) {
        try {
            conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error executing update: " + e.getMessage());
        }
    }

    public void deleteQuery(String query) {
        try {
            conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error executing delete: " + e.getMessage());
        }
    }

    public ResultSet selectQuery(String query) {
        try {
            return conn.createStatement().executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error executing select: " + e.getMessage());
            return null;
        }
    }
}