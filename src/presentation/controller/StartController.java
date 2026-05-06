package presentation.controller;

import business.managers.UserManager;
import presentation.view.LoginView;
import presentation.view.StartView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {

    private final StartView view;
    private NavController navController;

    public StartController(StartView view, NavController navController) {
        this.view = view;
        this.navController = navController;

        view.getLoginButton().addActionListener(this);
        view.getRegisterButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case StartView.BTN_LOG:
                navController.navigate(StartView.BTN_LOG);

                break;

            case StartView.BTN_REG:
                navController.navigate(StartView.BTN_REG);
                break;
        }
    }
}
