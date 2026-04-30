package presentation.controller;

import presentation.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavController implements ActionListener {

    StartView startView;
    RegisterView registerView;
    ActionListener listener;
    LoginView loginView;
    SelectGameView selectGameView;
    StatisticsView statisticsView;


    public NavController(StartView startView, RegisterView registerView, LoginView loginView, SelectGameView selectGameView, StatisticsView statisticsView) {
        this.startView = startView;
        this.startView.getRegisterButton().addActionListener(this);
        this.startView.getLoginButton().addActionListener(this);
        this.registerView = registerView;
        this.registerView.getStartButton().addActionListener(this);
        this.loginView = loginView;
        this.loginView.getStartButton().addActionListener(this);
        this.selectGameView = selectGameView;
        this.selectGameView.setListener(this);
        this.statisticsView = statisticsView;
        StatisticsController statisticsController = new StatisticsController(statisticsView);
        statisticsController.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {




        switch (e.getActionCommand()) {
            case StartView.BTN_REG:
                startRegisterView();

                break;

            case StartView.BTN_LOG:
                startLoginView();

                break;

            case RegisterView.BTN_START:

                startSelectGameView();

                break;

            case "STATS_PRESSED":
                statisticsView.start();
                break;

            case "BACK_TO_GAME":
                statisticsView.stop();
                break;

            case "LOGOUT_PRESSED": //TODO
                startView.start();

            default:
                if (e.getActionCommand().startsWith("START_")) {
                    startGameView(e.getActionCommand().substring("START_".length()));

                } else {
                    System.err.println("Unknown action command " + e.getActionCommand());
                }

        }
    }

    public void startRegisterView(){
        registerView.start();
        startView.stop();
    }

    public void startLoginView(){
        loginView.start();
        startView.stop();
    }

    public void startSelectGameView(){
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

    public void addActionListener(ActionListener listener){
        this.listener = listener;
    }
}
