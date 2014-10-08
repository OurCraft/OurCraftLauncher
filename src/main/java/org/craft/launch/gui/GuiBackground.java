package org.craft.launch.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiBackground extends JPanel
{
    public float progress = 0.0f;
    public int animationID = -1;

    public Image background;

    public GuiBackground() throws IOException
    {
        background = ImageIO.read(GuiBackground.class.getResourceAsStream("/background.png"));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, background.getWidth(null), background.getHeight(null) * ((int) ((animationID == 0 ? progress : 1) * 100)) / 100, null);

        updateUI();
    }

    public void animate(int id)
    {
        final int animationTime = 1000;
        int framesPerSecond = 60;
        int delay = 1000 / framesPerSecond;
        final long start = System.currentTimeMillis();
        final Timer timer = new Timer(delay, null);
        timer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                final long now = System.currentTimeMillis();
                final long elapsed = now - start;
                progress = (float) elapsed / animationTime;
                repaint();
                if (elapsed >= animationTime)
                {
                    timer.stop();
                    animationID = -1;
                }
            }
        });
        animationID = id;
        timer.start();
    }
}