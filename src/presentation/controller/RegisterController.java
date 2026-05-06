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
    private ActionListener navListener; // NavController listens here
    private NavController navController;

    /**
     * @param view        the RegisterView this controller manages
     * @param userManager the business manager (already initialised with Config)
     */
    public RegisterController(RegisterView view, UserManager userManager, NavController navController) {
        this.view = view;
        this.userManager = userManager;
        this.navController = navController;

        view.getStartButton().addActionListener(this);
        view.getBackButton().addActionListener(this);
    }

    public void addActionListener(ActionListener listener) {
        this.navListener = listener;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case RegisterView.BTN_START:
                handleRegister();
                navController.navigate(RegisterView.BTN_START); //TODO: PROVISIONAL HASTA QUE FUNCIONE EL LOGIN

                break;

            case RegisterView.BTN_BACK:
                navController.navigate(RegisterView.BTN_BACK);
                break;

            default:
                System.err.println("[RegisterController] Unknown action: " + e.getActionCommand());
        }
    }


    private void handleRegister() {
        view.clearError();

        String username = view.getUsernameField();
        String email = view.getEmailField();
        String password = view.getPasswordField();
        String confirmPassword = view.getConfirmPasswordField();

        // UserManager performs ALL validation + DB work
        // Returns null on success or an error message string on failure
        String error = userManager.register(username, email, password, confirmPassword);

        if (error == null) {
            // Registration + auto-login succeeded -> navigate to SelectGame
            navController.navigate(RegisterView.BTN_START); // NavController listens for "start"
        } else {
            view.showErrorMessage(error);
        }
    }
}