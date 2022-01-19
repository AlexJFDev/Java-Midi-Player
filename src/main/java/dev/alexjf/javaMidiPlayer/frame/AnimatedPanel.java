package dev.alexjf.javaMidiPlayer.frame;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class AnimatedPanel extends JPanel implements ControllerEventListener {

    int eventInt = 0;

    @Override
    public void controlChange(ShortMessage event) {
        eventInt++;
        System.out.println("set event status");
        repaint();
    }

    public void paintComponent(Graphics graphics){
        while (eventInt > 0){
            System.out.println("Painting");

            int red = (int)(Math.random() * 250);
            int green = (int)(Math.random() * 250);
            int blue = (int)(Math.random() * 250);

            graphics.setColor(new Color(red, green, blue));

            int height = (int)((Math.random() * 120) + 10);
            int width = (int)((Math.random() * 120) + 10);
            int x = (int)((Math.random() * 40) + 10);
            int y = (int)((Math.random() * 40) + 10);
            graphics.fillRect(x, y, width, height);
            eventInt--;
            System.out.println("Painted");
        }
    }
}
