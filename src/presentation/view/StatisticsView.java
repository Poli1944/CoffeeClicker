package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.List;

public class StatisticsView extends BaseView{
    private JComboBox<String> userSelector;
    private JComboBox<String> gameSelector;
    private JButton btnBack;
    private JPanel grafPanel;

    private Map<Long, Double> mapData;
    public final static String BTN_STOP = "stats_to_game";

    public StatisticsView(){
        super("Game Statistics", 24);

        this.userSelector = new JComboBox<>();
        this.gameSelector = new JComboBox<>();
        this.btnBack = new JButton("Back");

        this.grafPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawChart(g);
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

    public void setGames (ArrayList<Game> games){
        gameSelector.removeAllItems();
        ArrayList<String> stringGames = new ArrayList<>();
        for (Game game : games){
            stringGames.add(game.getGameId());
        }
        for (String game : stringGames){
            gameSelector.addItem(game);
        }
    }

    public void setUsers (ArrayList<String> users){
        userSelector.removeAllItems();
        for (String user : users){
            userSelector.addItem(user);
        }
    }

    public void registerController(ActionListener l) {
        this.btnBack.addActionListener(l);
        this.btnBack.setActionCommand(BTN_STOP);
        this.userSelector.addActionListener(l);
        this.userSelector.setActionCommand("USER_CHANGED");
        this.gameSelector.addActionListener(l);
        this.gameSelector.setActionCommand("GAME_CHANGED");
    }

    public void setChartData(Map<Long, Double> data) {
        this.mapData = data;
        this.grafPanel.repaint();
    }

    public String getSelectedUser() {
        return (String) userSelector.getSelectedItem();
    }

    public String getSelectedGame() {
        return (String) gameSelector.getSelectedItem();
    }

    private void drawChart(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (mapData == null || mapData.isEmpty()){
            return;
        }

        long maxTime = Collections.max(mapData.keySet());
        double maxCoffe = Collections.max(mapData.values());

        int marge = 50;
        int w = grafPanel.getWidth() - 2 * marge;
        int h = grafPanel.getHeight() - 2 * marge;

        g2.drawLine(marge, h + marge, w + marge, h + marge);
        g2.drawLine(marge, marge, marge, h + marge);
        List<Long> times = new ArrayList<>(mapData.keySet());
        Collections.sort(times);

        int prevX = -1, prevY = -1;
        for (long t : times) {

            long denominadorTime;
            if (maxTime == 0) {
                denominadorTime = 1;
            } else {
                denominadorTime = maxTime;
            }
            int x = marge + (int) (t * w / denominadorTime);

            double denominadorCoffe;
            if (maxCoffe == 0) {
                denominadorCoffe = 1.0;
            } else {
                denominadorCoffe = maxCoffe;
            }
            int y = (h + marge) - (int) (mapData.get(t) * h / denominadorCoffe);

            if (prevX != -1) {
                g2.drawLine(prevX, prevY, x, y);
            }
            prevX = x;
            prevY = y;
        }

        g2.drawString("Time (min)", w / 2, h + marge + 30);
    }
}
