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

    private final LoginView view;
    private final UserManager userManager;
    private NavController navController;

    /**
     * @param view the LoginView this controller manages
     * @param userManager the business manager (already initialised with Config)
     */
    public LoginController(LoginView view, UserManager userManager, NavController navController) {
        this.view = view;
        this.userManager = userManager;
        this.navController = navController;

        view.getStartButton().addActionListener(this);
        view.getBackButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case LoginView.BTN_START:
                handleLogin();

                break;

            case LoginView.BTN_BACK:
                navController.navigate(LoginView.BTN_BACK);
                break;
        }
    }


    private void handleLogin() {
        view.clearError();

        String login = view.getLoginField();    // username OR email
        String password = view.getPasswordField();

        if (login.isBlank() || password.isBlank()) {
            view.showErrorMessage("Por favor, rellena todos los campos.");
            return;
        }

        boolean ok = userManager.checkLogin(login, password);

        if (ok) {
            navController.navigate(LoginView.BTN_START);
        } else {
            view.showErrorMessage("Las credenciales introducidas son incorrectas.");
        }
    }
}