package presentation.controller;

import business.managers.StatsManager;
import business.managers.UserManager;
import presentation.view.StatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatisticsController implements ActionListener {

    private StatisticsView view;
    private ActionListener listener;
    private StatsManager manager;
    private UserManager userManager;

    public  StatisticsController(StatisticsView view, StatsManager manager, UserManager userManager) {
        this.view = view;
        this.view.registerController(this);
        this.manager = manager;
        this.userManager = userManager;

        view.setUsers(userManager.getAllUsernames());//TODO
    }

    @Override
    public void actionPerformed (ActionEvent e){
        String command = e.getActionCommand();

        if(command.equals("BTN_STOP")){
            ActionEvent event = new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "BACK_TO_GAME");
            listener.actionPerformed(event);
        }else if (command.equals("USER_CHANGED")) {
            String user = view.getSelectedUser();
            if (user != null) {
                view.setGames(new ArrayList<>(manager.getFinishedGamesByUser(user)));
            }
        } else if (command.equals("GAME_CHANGED")) {
            String gameName = view.getSelectedGame();
            if (gameName != null) {
                Game game = manager.getGameByName(gameName);
                view.setChartData(manager.makeMap(game.getGlobalStats()));
            }
        }
    }

    public void addActionListener(ActionListener listener){
        this.listener = listener;
    }
}
