package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BaseView extends JFrame {

    JPanel headerPanel = new JPanel();
    JPanel contentPanel = new JPanel();

    static Font kavoon;

    public BaseView(String title, int titleSize) {


        try {
            kavoon = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Kavoon-Regular.ttf"));

        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        configureWindow(title, titleSize);
    }

    void configureWindow(String title, int titleSize) {

        JPanel basePanel = new JPanel(new BorderLayout());
        setContentPane(basePanel);



        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);




        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(kavoon.deriveFont(Font.BOLD, (float) titleSize));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        headerPanel.add(titleLabel);



        add(contentPanel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);



        setIconImage(new ImageIcon(getClass().getResource("/resources/Browt.png")).getImage());
    }

    public void start() {
        setVisible(true);
    }

    public void stop() {
        setVisible(false);
    }
}
