package persistence;

import business.entities.User;

/**
 * DAO interface for user persistence operations.
 */
public interface UserDAO {

    /**
     * Inserts a new user into the 'users' table.
     * The password field must already be SHA-256 hashed.
     */
    void addUser(User user);

    /**
     * Finds a user by username OR email (dual-access login).
     *
     * @param login username or email address
     * @return the matching User, or null if not found
     */
    User getUserByIdentifier(String login);

    /**
     * Returns true if a record already exists with the given username or email.
     * Used to prevent duplicate registrations.
     */
    boolean isUsernameOrEmailTaken(String username, String email);
}