package presentation.controller;

import presentation.view.SelectGameView;

public class SelectGameController {

    SelectGameView view;

    public SelectGameController(SelectGameView view) {
        this.view = view;

        view.addGame("1");
        view.addGame("2");
        view.addGame("3");
        view.addGame("4");
        view.addGame("5");
        view.addGame("6");
        view.addGame("7");
        view.addGame("8");
        view.addGame("9");
        view.addGame("10");
        view.addGame("11");

    }


}
