package business.managers;

import business.entities.Config;
import business.entities.User;
import persistence.SQLUserDAO;
import persistence.UserDAO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;


public class UserManager {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");

    private User currentUser;
    private final UserDAO userDAO;


    public UserManager(Config config) {
        this.userDAO = new SQLUserDAO(config);
        this.currentUser = null;
    }


    public boolean validateUsernameFormat(String username) {
        return username != null && !username.trim().isEmpty();
    }

    public boolean validateEmailFormat(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public boolean validatePasswordFormat(String password) {
        if (password == null) return false;
        return PASSWORD_PATTERN.matcher(password).matches();
    }


    public String register(String username, String email, String password, String confirmPassword) {

        if (!validateUsernameFormat(username)) {
            return "El nombre de usuario no puede estar vacío.";
        }
        if (!validateEmailFormat(email)) {
            return "El formato del email no es válido.";
        }
        if (!validatePasswordFormat(password)) {
            return "La contraseña debe tener mínimo 8 caracteres, " + "una mayúscula, una minúscula y un número.";
        }


        if (!password.equals(confirmPassword)) {
            return "Las contraseñas no coinciden.";
        }

        if (userDAO.isUsernameOrEmailTaken(username, email)) {
            return "El nombre de usuario o el email ya están registrados.";
        }

        String hash = hashSHA256(password);
        if (hash == null) {
            return "Error interno al procesar la contraseña. Inténtalo de nuevo.";
        }

        User newUser = new User(username, email, hash);
        userDAO.addUser(newUser);


        this.currentUser = newUser;

        return null;
    }


    public boolean checkLogin(String login, String password) {
        if (login == null || password == null) return false;

        User found = userDAO.getUserByIdentifier(login);
        if (found == null) return false;

        String hash = hashSHA256(password);
        if (hash == null) return false;

        boolean ok = hash.equals(found.getPassword());
        if (ok) this.currentUser = found;
        return ok;
    }


    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }


    private String hashSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("[UserManager] SHA-256 not available: " + e.getMessage());
            return null;
        }
    }
}