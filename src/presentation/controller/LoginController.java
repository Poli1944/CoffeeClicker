package presentation.controller;

import business.managers.UserManager;
import presentation.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the Login screen.
 * Reads credentials from LoginView, delegates authentication to UserManager,
 * then signals NavController on success.
 */
public class LoginController implements ActionListener {

    private final LoginView   view;
    private final UserManager userManager;
    private       ActionListener navListener; // NavController listens here

    /**
     * @param view        the LoginView this controller manages
     * @param userManager the business manager (already initialised with Config)
     */
    public LoginController(LoginView view, UserManager userManager) {
        this.view        = view;
        this.userManager = userManager;
    }

    /** NavController calls this to receive navigation events. */
    public void addActionListener(ActionListener listener) {
        this.navListener = listener;
    }

    // ── ActionListener ───────────────────────────────────────────────

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case LoginView.BTN_START:
                handleLogin();
                break;

            case LoginView.BTN_BACK:
                // Tell NavController to go back to StartView
                fireAction("BACK_FROM_LOGIN");
                break;

            default:
                System.err.println("[LoginController] Unknown action: "
                        + e.getActionCommand());
        }
    }

    // ── Private helpers ──────────────────────────────────────────────

    private void handleLogin() {
        view.clearError();

        String login    = view.getLoginField();    // username OR email
        String password = view.getPasswordField();

        if (login.isBlank() || password.isBlank()) {
            view.showErrorMessage("Por favor, rellena todos los campos.");
            return;
        }

        boolean ok = userManager.checkLogin(login, password);

        if (ok) {
            // Auth succeeded → navigate to SelectGame
            fireAction(LoginView.BTN_START); // NavController listens for "start"
        } else {
            // Generic message — never reveal which field is wrong
            view.showErrorMessage("Las credenciales introducidas son incorrectas.");
        }
    }

    /** Forwards an action command to NavController. */
    private void fireAction(String command) {
        if (navListener != null) {
            navListener.actionPerformed(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command));
        }
    }
}