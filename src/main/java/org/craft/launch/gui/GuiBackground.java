package org.craft.launch.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GuiBackground extends JPanel
{
    public Image background;

    public GuiBackground() throws IOException
    {
        background = ImageIO.read(GuiBackground.class.getResourceAsStream("/background.png"));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        updateUI();
    }
}
