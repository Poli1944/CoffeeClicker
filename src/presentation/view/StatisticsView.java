package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatisticsView extends BaseView{
    private JComboBox<String> userSelector;
    private JComboBox<String> gameSelector;
    private JButton btnBack;
    private JPanel grafPanel;

    public StatisticsView(){
        super("Game Statistics", 24);

        this.userSelector = new JComboBox<>();
        this.gameSelector = new JComboBox<>();
        this.btnBack = new JButton("Back");

        this.grafPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //TODO: FUNCIÓ DE DIBUIXAR MANUALMENT EL GRAF
            }
        };
        this.grafPanel.setPreferredSize(new Dimension(600, 400));
        this.grafPanel.setBackground(Color.WHITE);

        this.setupStatsView();
    }

    private void setupStatsView(){
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JPanel selectorsPanel = new JPanel();
        selectorsPanel.setLayout(new FlowLayout());
        selectorsPanel.add(new JLabel("Select User:"));
        selectorsPanel.add(userSelector);
        selectorsPanel.add(new JLabel("Select Game:"));
        selectorsPanel.add(gameSelector);
        contentPanel.add(selectorsPanel);

        contentPanel.add(grafPanel);

        JPanel bottom = new JPanel();
        bottom.add(btnBack);
        this.add(bottom, BorderLayout.SOUTH);
    }

    public void setGames (ArrayList<String> games){
        gameSelector.removeAllItems();
        for (String game : games){
            gameSelector.addItem(game);
        }
    }

    public void setUsers (ArrayList<String> users){
        gameSelector.removeAllItems();
        for (String user : users){
            userSelector.addItem(user);
        }
    }

    public void registerController(ActionListener l) {
        this.btnBack.addActionListener(l);
        this.btnBack.setActionCommand("BACK_TO_GAME");
        this.userSelector.addActionListener(l);
        this.gameSelector.addActionListener(l);
    }
}
