package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class GameView extends BaseView{
    private JLabel coffeCount;
    private JLabel productionRate;
    private JImagePanel clickArea;
    private JLabel shopTitle;
    private JPanel buildingsContainer;
    private JPanel upgradesContainer;
    private JButton logout;
    private JButton stats;

    private ActionListener listener;


    public GameView(String title) {
        super(title, 16);
        this.coffeCount = new JLabel("0");
        this.productionRate = new JLabel("0 per second");
        this.clickArea = new JImagePanel("CoffeeClicker/images/test.jpg"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 300);
            }
        };

        this.shopTitle = new JLabel("\tSHOP");
        this.logout = new JButton("Logout");
        this.stats = new JButton("Stats");

        this.buildingsContainer = new JPanel();
        this.buildingsContainer.setLayout(new BoxLayout(buildingsContainer, BoxLayout.Y_AXIS));
        this.upgradesContainer = new JPanel();
        this.upgradesContainer.setLayout(new BoxLayout(upgradesContainer, BoxLayout.Y_AXIS));


        this.startGameView();
    }

    public void startGameView(){

        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(coffeCount);
        leftPanel.add(productionRate);
        leftPanel.add(clickArea);
        contentPanel.add(leftPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(shopTitle);
        rightPanel.add(new JLabel("BUILDINGS"));
        JScrollPane buildingsScroll = new JScrollPane(buildingsContainer);
        buildingsScroll.setPreferredSize(new Dimension(350, 250));
        buildingsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightPanel.add(buildingsScroll);
        rightPanel.add(new JLabel("UPGRADES"));
        JScrollPane upgradesScroll = new JScrollPane(upgradesContainer);
        upgradesScroll.setPreferredSize(new Dimension(350, 200));
        upgradesScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightPanel.add(upgradesScroll);
        contentPanel.add(rightPanel);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
        footerPanel.add(logout);
        footerPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        footerPanel.add(stats);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    public void addItemToShop(String name, String imagePath, boolean isUpgrade) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        row.add(new JImagePanel(imagePath));
        row.add(new JLabel(name));
        row.add(Box.createHorizontalGlue());


        JButton btnBuy = new JButton("Buy");
        btnBuy.setActionCommand("BUY_" + name);
        JButton btnSell = new JButton("Sell");
        btnSell.setActionCommand("SELL_" + name);

        if (listener != null) {
            btnBuy.addActionListener(listener);
            btnSell.addActionListener(listener);
        }

        row.add(btnBuy);
        row.add(btnSell);

        if (isUpgrade) {
            upgradesContainer.add(row);
        } else {
            buildingsContainer.add(row);
        }

    }

    public void registerController(ActionListener listener, MouseListener ml) {
        this.listener = listener;
        this.logout.addActionListener(listener);
        this.logout.setActionCommand("LOGOUT");
        this.stats.addActionListener(listener);
        this.stats.setActionCommand("STATS");
        this.clickArea.addMouseListener(ml);
    }

    public void setListener(ActionListener listener){
        this.listener = listener;
    }

}



