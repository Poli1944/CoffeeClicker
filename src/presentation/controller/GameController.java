package presentation.controller;

import presentation.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameController extends MouseAdapter implements ActionListener {
    private GameView view;

    ActionListener listener;


    public GameController(GameView view) {
        this.view = view;
        this.view.registerController(this, this);

        //per testing això s'ha de treure en un futur
        view.addItemToShop("test1","images/test.jpg",false);
        view.addItemToShop("test1","images/test.jpg",false);
        view.addItemToShop("test1","images/test.jpg",false);
        view.addItemToShop("test1","images/test.jpg",false);
        view.addItemToShop("test1","images/test.jpg",false);
        view.addItemToShop("test1","images/test.jpg",false);
        view.addItemToShop("test1","images/test.jpg",false);
        view.addItemToShop("test1","images/test.jpg",false);
        view.addItemToShop("test1","images/test.jpg",false);
        view.addItemToShop("test1","images/test.jpg",false);
        view.addItemToShop("test1","images/test.jpg",true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("LOGOUT")) {
            System.out.println("LOGOUT!!");
            ActionEvent event = new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "LOGOUT_PRESSED");
            listener.actionPerformed(event);

        } else if (command.equals("STATS")) {
            ActionEvent event = new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "STATS_PRESSED");
            listener.actionPerformed(event);
            System.out.println("STATS!!");

        } else if (command.startsWith("BUY_")) {

            System.out.println("BUY!!");

        } else if (command.contains("SELL_")) {

            System.out.println("SELL!!");
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICK!!");
    }

    public void addActionListener(ActionListener listener){
        this.listener = listener;
    }

}
