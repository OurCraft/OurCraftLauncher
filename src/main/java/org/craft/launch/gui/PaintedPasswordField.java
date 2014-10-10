package org.craft.launch.gui;

import javax.swing.*;
import java.awt.*;

public class PaintedPasswordField extends JPasswordField
{
    private Image image;

    public PaintedPasswordField(ImageIcon i)
    {
        setOpaque(false);
        setBorder(null);
        image = i.getImage();
    }

    public void paint(Graphics g)
    {
        g.drawImage(image, 0, 0, this);
        super.paint(g);
    }
}
