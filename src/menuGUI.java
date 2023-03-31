import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class menuGUI implements ActionListener {
    public static void main(String[] args) {
        new menuGUI();
    }

    Font headerFont = new Font("MV Boli", Font.BOLD, 40);
    Font buttonFont = new Font("MV Boli", Font.BOLD, 20);
    JButton blackCoffee, latte, icedCoffee, small, medium, large, cash, card;
    JTextArea totalArea;

    menuGUI() {

        JFrame frame = new JFrame();
        {
            frame.setSize(1900, 1100);
            frame.setResizable(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Test JFrame");
            frame.setLayout(null);
            frame.setResizable(false);
            frame.getContentPane().setBackground(Color.pink);
        }

        JPanel headerPanel = new JPanel();
        {
            headerPanel.setBackground(new Color(0xFFF1E6));
            headerPanel.setBounds(150, 50, 1600, 300);
            headerPanel.setLayout(null);
        }
        JPanel bodyPanel = new JPanel();
        {
            bodyPanel.setBackground(new Color(0xFFF1E6));
            bodyPanel.setBounds(150, 400, 1600, 600);
            bodyPanel.setLayout(null);
        }

        Border defaultBorder = BorderFactory.createLineBorder(new Color(0xD4A373), 2);

        /*Header JLabels*/
        JLabel header = new JLabel();
        {
            header.setText("Shop of things");
            header.setForeground(new Color(0x929B7A));
            header.setFont(new Font("MV Boli", Font.BOLD, 50));
            header.setIconTextGap(0);
            header.setBackground(new Color(0xFAEDCD));
            header.setOpaque(true);
            header.setBorder(defaultBorder);
            header.setHorizontalAlignment(JLabel.CENTER);
            header.setBounds(500, 75, 600, 75);
        }
        JLabel drinks = new JLabel();
        {
            drinks.setText("Drink options:");
            drinks.setForeground(new Color(0x929B7A));
            drinks.setFont(headerFont);
            drinks.setBackground(new Color(0xFAEDCD));
            drinks.setOpaque(true);
            drinks.setBorder(defaultBorder);
            drinks.setHorizontalAlignment(JLabel.CENTER);
            drinks.setBounds(175, 225, 300, 50);
        }
        JLabel size = new JLabel();
        {
            size.setText("Size options:");
            size.setForeground(new Color(0x929B7A));
            size.setFont(headerFont);
            size.setBackground(new Color(0xFAEDCD));
            size.setOpaque(true);
            size.setBorder(defaultBorder);
            size.setHorizontalAlignment(JLabel.CENTER);
            size.setBounds(650, 225, 300, 50);
        }
        JLabel cost = new JLabel();
        {
            cost.setText("Cost:");
            cost.setForeground(new Color(0x929B7A));
            cost.setFont(headerFont);
            cost.setBackground(new Color(0xFAEDCD));
            cost.setOpaque(true);
            cost.setBorder(defaultBorder);
            cost.setHorizontalAlignment(JLabel.CENTER);
            cost.setBounds(1125, 225, 300, 50);
        }

        /*JButtons*/
        blackCoffee = new JButton();
        {
            blackCoffee.setBounds(250, 50, 150, 150);
            blackCoffee.setText("Black Coffee");
            blackCoffee.setFocusable(false);
            blackCoffee.addActionListener(this);
            blackCoffee.setBackground(new Color(0xFAEDCD));
            blackCoffee.setBorder(defaultBorder);
            blackCoffee.setFont(buttonFont);
        }
        latte = new JButton();
        {
            latte.setBounds(250, 225, 150, 150);
            latte.setText("Latte");
            latte.setFocusable(false);
            latte.addActionListener(this);
            latte.setBackground(new Color(0xFAEDCD));
            latte.setBorder(defaultBorder);
            latte.setFont(buttonFont);
        }
        icedCoffee = new JButton();
        {
            icedCoffee.setBounds(250, 400, 150, 150);
            icedCoffee.setText("Iced Coffee");
            icedCoffee.setFocusable(false);
            icedCoffee.addActionListener(this);
            icedCoffee.setBackground(new Color(0xFAEDCD));
            icedCoffee.setBorder(defaultBorder);
            icedCoffee.setFont(buttonFont);
        }
        small = new JButton();
        {
            small.setBounds(725, 50, 150, 150);
            small.setText("Small");
            small.setFocusable(false);
            small.addActionListener(this);
            small.setBackground(new Color(0xFAEDCD));
            small.setBorder(defaultBorder);
            small.setFont(buttonFont);
        }
        medium = new JButton();
        {
            medium.setBounds(725, 225, 150, 150);
            medium.setText("Medium");
            medium.setFocusable(false);
            medium.addActionListener(this);
            medium.setBackground(new Color(0xFAEDCD));
            medium.setBorder(defaultBorder);
            medium.setFont(buttonFont);
        }
        large = new JButton();
        {
            large.setBounds(725, 400, 150, 150);
            large.setText("Large");
            large.setFocusable(false);
            large.addActionListener(this);
            large.setBackground(new Color(0xFAEDCD));
            large.setBorder(defaultBorder);
            large.setFont(buttonFont);
        }
        cash = new JButton();
        {
            cash.setBounds(1200, 50, 150, 150);
            cash.setText("Cash");
            cash.setFocusable(false);
            cash.addActionListener(this);
            cash.setBackground(new Color(0xFAEDCD));
            cash.setBorder(defaultBorder);
            cash.setFont(buttonFont);
        }
        card = new JButton();
        {
            card.setBounds(1200, 225, 150, 150);
            card.setText("Card");
            card.setFocusable(false);
            card.addActionListener(this);
            card.setBackground(new Color(0xFAEDCD));
            card.setBorder(defaultBorder);
            card.setFont(buttonFont);
        }

         totalArea = new JTextArea();
        {
            totalArea.setBounds(1125, 400, 300, 150);
            totalArea.setFocusable(false);
            totalArea.setText("Select Items");
            totalArea.setBorder(defaultBorder);
            totalArea.setBackground(new Color(0xFAEDCD));
            totalArea.setFont(new Font("MV Boli", Font.PLAIN, 16));
        }

        headerPanel.add(drinks);
        headerPanel.add(size);
        headerPanel.add(cost);
        headerPanel.add(header);
        bodyPanel.add(blackCoffee);
        bodyPanel.add(latte);
        bodyPanel.add(icedCoffee);
        bodyPanel.add(small);
        bodyPanel.add(medium);
        bodyPanel.add(large);
        bodyPanel.add(cash);
        bodyPanel.add(card);
        bodyPanel.add(totalArea);
        frame.add(headerPanel);
        frame.add(bodyPanel);

        frame.setVisible(true);

    }

    String drink="",pay="",size="";
    double sizeCost, drinkCost;
    LocalDateTime dateTime = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDate = dateTime.format(dateTimeFormatter);

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==blackCoffee) {
            drink = "Black coffee";
            drinkCost=7.50;

        } else if (e.getSource()==latte) {
            drink = "Latte";
            drinkCost=8.00;

        } else if (e.getSource()==icedCoffee) {
            drink = "Iced coffee";
            drinkCost=11.50;
        }

        if (e.getSource()==small){
            size = "Small";
            sizeCost=0.90;

        } else if (e.getSource()==medium) {
            size = "Medium";
            sizeCost=1.00;

        } else if (e.getSource()==large) {
            size = "Large";
            sizeCost=1.10;
        }

        if (e.getSource()==cash){
            pay = "Cash";

        } else if (e.getSource()==card) {
            pay = "Card";

        }

        double total=drinkCost*sizeCost;
        totalArea.setText("Drink:"+drink+"\nSize:"+size+"\nPayment method: "+pay+"\nTotal: €"+total+"\n@ "+formattedDate);


    }
}
