import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import java.awt.Graphics;


public class EOPanelCreateEvent extends EOPanel {

    EOGUI gui = null;
    public EOPanelCreateEvent(EOGUI gui) {
        this.gui = gui;
        this.setLayout(null);

        int fromTop = 50;
        int borderLeft = 10;

        JLabel eventnameLabel = new JLabel("Begivenheds navn");
        eventnameLabel.setBounds(borderLeft, fromTop, 250, 150);
        eventnameLabel.setFont(gui.getFontmedium());
        this.add(eventnameLabel);

        JLabel startdatetimeLabel = new JLabel("Start dato/tid: ");
        startdatetimeLabel.setBounds(borderLeft + 400 + 10, fromTop, 250, 150);
        startdatetimeLabel.setFont(gui.getFontmedium());
        this.add(startdatetimeLabel);

        JLabel addfacilitatorLabel = new JLabel("Tilfoej facilitator(er) til begivenhed ");
        addfacilitatorLabel.setBounds(borderLeft + 800 + 10, fromTop, 350, 150);
        addfacilitatorLabel.setFont(gui.getFontmedium());
        this.add(addfacilitatorLabel);

    }
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawLine(0,38, this.getWidth(), 38);
            g.drawLine(0,332, this.getWidth(), 332);
            g.drawLine(0,0, this.getWidth(), 0);
            g.drawLine(0,0, 0, this.getHeight());
            g.drawLine(0,this.getHeight()-1, this.getWidth(), this.getHeight()-1);
            g.drawLine(this.getWidth()-1,0, this.getWidth()-1, this.getHeight());
        }





}