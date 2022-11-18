import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

    private JPanel northPanel, centerPanel, eastPanel, westPanel, southPanel;
    private JButton myButton;
    public Gui(boolean debug) {
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Test");
        this.setBackground(Color.RED);

        createNewPanel();

        if(!debug) {
            this.setVisible(true);
        }
    }

    public void createNewPanel() {
        northPanel = new JPanel();
        southPanel = new JPanel();
        eastPanel = new JPanel();
        westPanel = new JPanel();
        centerPanel = new JPanel();

        myButton = new JButton("Click me!");
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(centerPanel.getBackground().equals(Color.RED)) {
                    centerPanel.setBackground(Color.YELLOW);
                } else {
                    centerPanel.setBackground(Color.RED);
                }
            }
        });

        northPanel.setBackground(Color.BLUE);
        southPanel.setBackground(Color.GREEN);
        eastPanel.setBackground(Color.CYAN);
        westPanel.setBackground(Color.PINK);
        centerPanel.setBackground(Color.RED);

        centerPanel.add(myButton);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);

    }

    public JPanel getNorthPanel() {
        return northPanel;
    }

    public void setNorthPanel(JPanel northPanel) {
        this.northPanel = northPanel;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JPanel getEastPanel() {
        return eastPanel;
    }

    public void setEastPanel(JPanel eastPanel) {
        this.eastPanel = eastPanel;
    }

    public JPanel getWestPanel() {
        return westPanel;
    }

    public void setWestPanel(JPanel westPanel) {
        this.westPanel = westPanel;
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }

    public void setSouthPanel(JPanel southPanel) {
        this.southPanel = southPanel;
    }

    public JButton getMyButton() {
        return myButton;
    }

    public void setMyButton(JButton myButton) {
        this.myButton = myButton;
    }
}
