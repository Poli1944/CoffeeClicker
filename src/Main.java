import business.entities.Config;
import persistence.ConfigDAO;
import presentation.controller.NavController;
import presentation.controller.SelectGameController;
import presentation.view.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Config config = new ConfigDAO().readConfig();

            StartView startView = new StartView();
            RegisterView registerView = new RegisterView();
            LoginView loginView = new LoginView();
            SelectGameView selectGameView = new SelectGameView();
            StatisticsView statisticsView = new StatisticsView();

            NavController navController = new NavController(config, startView, registerView, loginView, selectGameView, statisticsView);

            SelectGameController selectGameController = new SelectGameController(selectGameView,  navController);

            startView.start();
        });
    }
}