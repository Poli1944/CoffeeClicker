import presentation.controller.GameController;
import presentation.controller.NavController;
import presentation.controller.SelectGameController;
import presentation.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StartView startView = new StartView();
            RegisterView registerView = new RegisterView();
            LoginView loginView = new LoginView();
            SelectGameView selectGameView = new SelectGameView();
            StatisticsView statisticsView = new StatisticsView();
            NavController navController = new NavController(startView, registerView, loginView, selectGameView, statisticsView);

            SelectGameController selectGameController = new SelectGameController(selectGameView);



            startView.start();
        });
    }
}