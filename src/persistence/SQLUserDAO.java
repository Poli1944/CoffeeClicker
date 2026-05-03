package persistence;

import business.entities.Config;
import business.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLUserDAO implements UserDAO {

    private final DatabaseConnector db;


    public SQLUserDAO(Config config) {
        this.db = DatabaseConnector.getInstance(config);
    }


    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (username, email, password_hash) VALUES ('"
                + user.getUsername() + "', '"
                + user.getEmail()    + "', '"
                + user.getPassword() + "')";
        db.insertQuery(query);
    }

    @Override
    public User getUserByIdentifier(String login) {
        String query = "SELECT username, email, password_hash FROM users "
                + "WHERE username = '" + login + "' OR email = '" + login + "'";

        ResultSet rs = db.selectQuery(query);
        try {
            if (rs != null && rs.next()) {
                return new User(
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                );
            }
        } catch (SQLException e) {
            System.err.println("[SQLUserDAO] getUserByIdentifier: " + e.getMessage());
        }
        return null;
    }


    @Override
    public boolean isUsernameOrEmailTaken(String username, String email) {
        String query = "SELECT COUNT(*) AS total FROM users "
                + "WHERE username = '" + username + "' OR email = '" + email + "'";

        ResultSet rs = db.selectQuery(query);
        try {
            if (rs != null && rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            System.err.println("[SQLUserDAO] isUsernameOrEmailTaken: " + e.getMessage());
        }
        return false;
    }
}