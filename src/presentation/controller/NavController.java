package presentation.controller;

import business.entities.Config;
import business.managers.UserManager;
import presentation.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NavController implements ActionListener {

    // ── Views ────────────────────────────────────────────────────────
    private final StartView      startView;
    private final RegisterView   registerView;
    private final LoginView      loginView;
    private final SelectGameView selectGameView;
    private final StatisticsView statisticsView;

    // ── Business ─────────────────────────────────────────────────────
    private final UserManager userManager;

    // ── Sub-controllers ──────────────────────────────────────────────
    private final LoginController    loginController;
    private final RegisterController registerController;

    private ActionListener listener;

    // ── Constructor ──────────────────────────────────────────────────

    /**
     * @param config app config read from config.json — needed by UserManager
     */
    public NavController(Config config,
                         StartView startView,
                         RegisterView registerView,
                         LoginView loginView,
                         SelectGameView selectGameView,
                         StatisticsView statisticsView) {

        this.startView      = startView;
        this.registerView   = registerView;
        this.loginView      = loginView;
        this.selectGameView = selectGameView;
        this.statisticsView = statisticsView;

        // ── Business layer ───────────────────────────────────────────
        this.userManager = new UserManager(config);

        // ── StartView buttons → NavController ────────────────────────
        this.startView.getRegisterButton().addActionListener(this);
        this.startView.getLoginButton().addActionListener(this);

        // ── RegisterController ───────────────────────────────────────
        this.registerController = new RegisterController(registerView, userManager);
        this.registerView.getStartButton().addActionListener(registerController);
        this.registerView.getBackButton().addActionListener(registerController);
        this.registerController.addActionListener(this); // controller fires back to us

        // ── LoginController ──────────────────────────────────────────
        this.loginController = new LoginController(loginView, userManager);
        this.loginView.getStartButton().addActionListener(loginController);
        this.loginView.getBackButton().addActionListener(loginController);
        this.loginController.addActionListener(this); // controller fires back to us

        // ── SelectGameView ───────────────────────────────────────────
        this.selectGameView.setListener(this);

        // ── StatisticsView ───────────────────────────────────────────
        StatisticsController statisticsController = new StatisticsController(statisticsView);
        statisticsController.addActionListener(this);
    }

    // ── ActionListener ───────────────────────────────────────────────

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            // StartView → Register or Login screen
            case StartView.BTN_REG:
                startRegisterView();
                break;

            case StartView.BTN_LOG:
                startLoginView();
                break;

            case "start":
                startSelectGameView();
                break;

            // Back buttons → return to StartView
            case "BACK_FROM_REGISTER":
                registerView.stop();
                startView.start();
                break;

            case "BACK_FROM_LOGIN":
                loginView.stop();
                startView.start();
                break;

            // In-game navigation
            case "STATS_PRESSED":
                statisticsView.start();
                break;

            case "BACK_TO_GAME":
                statisticsView.stop();
                break;

            case "LOGOUT_PRESSED":
                userManager.logout();
                startView.start();
                break;

            default:
                if (e.getActionCommand().startsWith("START_")) {
                    startGameView(e.getActionCommand().substring("START_".length()));
                } else {
                    System.err.println("[NavController] Unknown action: "
                            + e.getActionCommand());
                }
        }
    }

    // ── Navigation helpers ───────────────────────────────────────────

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
        gameController.addActionListener(this);
        gameView.start();
        selectGameView.stop();
    }

    public void addActionListener(ActionListener listener) {
        this.listener = listener;
    }
}