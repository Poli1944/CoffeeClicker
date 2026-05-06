package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectGameView extends BaseView {

    private ActionListener listener;

    public final static String BTN_STARTGAME = "START_";
    public static final String BTN_DELETEGAME = "DELETE_";
    public static final String BTN_NEWGAME = "new_game";
    public static final String BTN_BACK = "back_to_start";

    private JButton backButton;
    private JButton newGameButton;

    private JPanel panel;

    public SelectGameView() {
        super("Select Game", 42);

        this.panel = new JPanel();

        configureView();
    }

    private void configureView() {

        contentPanel.setLayout(new BorderLayout());

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(30));
        panel.setAlignmentY(Component.TOP_ALIGNMENT);

        panel.setOpaque(false);

        JScrollPane gameScroll = new JScrollPane(panel);
        gameScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        gameScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gameScroll.setBorder(BorderFactory.createEmptyBorder(20, 500, 20, 500));

        contentPanel.add(gameScroll, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        backButton = new JButton("Volver");
        backButton.setFont(kavoon.deriveFont(Font.BOLD, 24f));
        backButton.setActionCommand(BTN_BACK);

        newGameButton = new JButton("Nuevo Juego");
        newGameButton.setFont(kavoon.deriveFont(Font.BOLD, 24f));
        newGameButton.setActionCommand(BTN_NEWGAME);

        buttonPanel.add(backButton);
        buttonPanel.add(newGameButton);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addGame(String id) {

        JPanel gamePanel = new JPanel(new BorderLayout());

        gamePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        gamePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel nameLabel = new JLabel("Game #" + id);
        nameLabel.setFont(kavoon.deriveFont(Font.PLAIN, 36f));


        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftPanel.setOpaque(false);
        leftPanel.add(nameLabel);

        gamePanel.add(leftPanel, BorderLayout.WEST);


        JButton btnStart = new JButton("Select");
        btnStart.setFont(kavoon.deriveFont(Font.BOLD, 18f));
        btnStart.setActionCommand(BTN_STARTGAME + id);


        JButton btnDelete = new JButton("Eliminar");
        btnDelete.setFont(kavoon.deriveFont(Font.BOLD, 18f));
        btnDelete.setActionCommand(BTN_DELETEGAME + id);

        if (listener != null) {
            btnStart.addActionListener(listener);
            btnDelete.addActionListener(listener);
        }

        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightButtons.add(btnStart);
        rightButtons.add(btnDelete);

        gamePanel.add(rightButtons, BorderLayout.EAST);


        panel.add(gamePanel);
        panel.add(Box.createVerticalStrut(15));

        panel.revalidate(); //Actualizar vista
        panel.repaint();

    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    public JButton getBackButton() {
        return  backButton;
    }
    public JButton getNewGameButton() {
        return  newGameButton;
    }
}
