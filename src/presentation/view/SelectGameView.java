package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectGameView extends BaseView {

    private ActionListener listener;

    public final static String BTN_STARTGAME = "START_";

    private JPanel panel;

    public SelectGameView() {
        super("Select Game", 42);

        this.panel = new JPanel();

        configureView();
    }

    private void configureView() {



        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentY(Component.TOP_ALIGNMENT);



        JScrollPane gameScroll = new JScrollPane(panel);
        gameScroll.setPreferredSize(new Dimension(700, 600));
        gameScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        gameScroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.add(gameScroll, BorderLayout.CENTER);
    }

    public void addGame(String id) {

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        gamePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        gamePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        gamePanel.add(Box.createHorizontalStrut(15));

        JLabel nameLabel = new JLabel("Game #" + id);
        nameLabel.setFont(kavoon.deriveFont(Font.PLAIN, 36f));
        gamePanel.add(nameLabel);

        gamePanel.add(Box.createHorizontalStrut(30));

        JButton btnStart = new JButton("Select");
        btnStart.setActionCommand(BTN_STARTGAME + id);

        gamePanel.add(btnStart);

        if (listener != null) {
            btnStart.addActionListener(listener);
        }

        gamePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(gamePanel);
        panel.add(Box.createVerticalStrut(15));

    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }
}
