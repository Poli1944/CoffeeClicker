package presentation.controller;

import business.managers.UserManager;
import presentation.view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the Register screen.
 * Reads the form fields from RegisterView, delegates all validation
 * and persistence to UserManager, then signals NavController on success.
 */
public class RegisterController implements ActionListener {

    private final RegisterView view;
    private final UserManager  userManager;
    private       ActionListener navListener; // NavController listens here

    /**
     * @param view        the RegisterView this controller manages
     * @param userManager the business manager (already initialised with Config)
     */
    public RegisterController(RegisterView view, UserManager userManager) {
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

            case RegisterView.BTN_START:
                handleRegister();
                break;

            case RegisterView.BTN_BACK:
                // Tell NavController to go back to StartView
                fireAction("BACK_FROM_REGISTER");
                break;

            default:
                System.err.println("[RegisterController] Unknown action: "
                        + e.getActionCommand());
        }
    }

    // ── Private helpers ──────────────────────────────────────────────

    private void handleRegister() {
        view.clearError();

        String username        = view.getUsernameField();
        String email           = view.getEmailField();
        String password        = view.getPasswordField();
        String confirmPassword = view.getConfirmPasswordField();

        // UserManager performs ALL validation + DB work
        // Returns null on success or an error message string on failure
        String error = userManager.register(username, email, password, confirmPassword);

        if (error == null) {
            // Registration + auto-login succeeded → navigate to SelectGame
            fireAction(RegisterView.BTN_START); // NavController listens for "start"
        } else {
            view.showErrorMessage(error);
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