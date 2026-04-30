package presentation.controller;

import presentation.view.StatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsController implements ActionListener {

    StatisticsView view;
    ActionListener listener;

    public  StatisticsController(StatisticsView view) {
        this.view = view;
        this.view.registerController(this);
    }

    @Override
    public void actionPerformed (ActionEvent e){
        String command = e.getActionCommand();

        if(command.equals("BACK_TO_GAME")){
            ActionEvent event = new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "BACK_TO_GAME");
            listener.actionPerformed(event);
        }
    }

    public void addActionListener(ActionListener listener){
        this.listener = listener;
    }
}
