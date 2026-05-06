package presentation.controller;

import presentation.view.SelectGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGameController implements ActionListener {

    private SelectGameView view;
    private NavController navController;

    private static int n;

    public SelectGameController(SelectGameView view, NavController navController)  {
        this.view = view;
        this.navController = navController;

        n = 0;

        view.setListener(this);

        view.getBackButton().addActionListener(this);
        view.getNewGameButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SelectGameView.BTN_BACK:
                navController.navigate(SelectGameView.BTN_BACK);
                break;


            case SelectGameView.BTN_NEWGAME:
                view.addGame(Integer.toString(n));

                n++;
                break;

            default:
                if (e.getActionCommand().startsWith(SelectGameView.BTN_DELETEGAME)) {
                    navController.navigate(e.getActionCommand());
                } else if (e.getActionCommand().startsWith(SelectGameView.BTN_STARTGAME)) {
                    navController.navigate(e.getActionCommand());
                }

        }
    }
}
