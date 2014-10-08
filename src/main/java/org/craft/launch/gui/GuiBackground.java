package org.craft.launch.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiBackground extends JPanel
{
    private float progress = 0.0f;
    public Image background;

    public GuiBackground() throws IOException
    {
        background = ImageIO.read(GuiBackground.class.getResourceAsStream("/background.png"));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight() * ((int) (progress * 100)) / 100, null);

        updateUI();
    }

    public void animate()
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
                if (elapsed >= animationTime) timer.stop();
            }
        });
        timer.start();
    }
}