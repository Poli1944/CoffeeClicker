package presentation.controller;

import business.entities.Config;
import business.managers.UserManager;
import presentation.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NavController {

    private final StartView startView;
    private final RegisterView registerView;
    private final LoginView loginView;
    private final SelectGameView selectGameView;
    private final StatisticsView statisticsView;

    private final UserManager userManager;

    private final LoginController loginController;
    private final RegisterController registerController;
    private final StartController startController;


    /**
     * @param config app config read from config.json — needed by UserManager
     */
    public NavController(Config config, StartView startView, RegisterView registerView,
                         LoginView loginView, SelectGameView selectGameView, StatisticsView statisticsView) {

        this.startView = startView;
        this.registerView = registerView;
        this.loginView = loginView;
        this.selectGameView = selectGameView;
        this.statisticsView = statisticsView;

        // Business
        this.userManager = new UserManager(config);

        this.startController = new StartController(startView, this);



        // RegisterController
        this.registerController = new RegisterController(registerView, userManager, this);

        // LoginController
        this.loginController = new LoginController(loginView, userManager, this);

    }


    public void navigate(String action) {
        switch (action) {

            case StartView.BTN_REG:
                startRegisterView();
                break;

            case StartView.BTN_LOG:
                startLoginView();
                break;

            case LoginView.BTN_START:
                startSelectGameView();
                break;


            case LoginView.BTN_BACK:
                loginView.stop();
                registerView.stop();
                startView.start();
                break;

            // In-game navigation
            case GameView.BTN_STATS:
                statisticsView.start();
                break;

            case StatisticsView.BTN_STOP:
                statisticsView.stop();
                break;

            case GameView.BTN_LOGOUT: //TODO: Quitar la lógica de UserManager
                userManager.logout();
                startView.start();
                break;

            default:
                if (action.startsWith(SelectGameView.BTN_STARTGAME)) {
                    startGameView(action.substring(SelectGameView.BTN_STARTGAME.length()));
                } else {
                     System.err.println("[NavController] Unknown action: " + action);
                }
        }
    }


    public void startRegisterView() {
        registerView.start();
        startView.stop();
    }

    public void startLoginView() {
        loginView.start();
        startView.stop();
    }

    public void startSelectGameView() {
        selectGameView.start();
        loginView.stop();
        registerView.stop();
    }

    public void startGameView(String id) {
        GameView gameView = new GameView("Game - " + id);
        GameController gameController = new GameController(gameView);
        gameView.start();
        selectGameView.stop();
    }
}