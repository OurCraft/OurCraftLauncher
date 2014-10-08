package org.craft.launch.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class GuiBackground extends JPanel
{
    private float progress = 0.0f;
    private int animationID = -1;

    public Random random;
    public Image[] backgrounds;
    public Image logo;
    public int imageID;

    public GuiBackground() throws IOException
    {
        random = new Random();
        backgrounds = new Image[1];

        logo = ImageIO.read(GuiBackground.class.getResourceAsStream("/logo.png"));
        for (int i = 0; i < backgrounds.length; i++) backgrounds[i] = ImageIO.read(GuiBackground.class.getResourceAsStream("/background" + i + ".png"));
        imageID = random.nextInt(backgrounds.length);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(backgrounds[imageID], 0, 0, getWidth(), getHeight(), null);
        g.drawImage(logo, 0, 0, logo.getWidth(null), logo.getHeight(null) * ((int) (getProgress(0) * 100)) / 100, null);
    }

    public void animate(int id, int time)
    {
        final int animationTime = time;
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

    public float getProgress(int id)
    {
        return animationID == id ? progress : 1;
    }
}