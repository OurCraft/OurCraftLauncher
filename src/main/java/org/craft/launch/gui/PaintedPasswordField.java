package org.craft.launch.gui;

import java.awt.*;

import javax.swing.*;

public class PaintedPasswordField extends JPasswordField
{
    private Image image;

    public PaintedPasswordField(ImageIcon i)
    {
        image = i.getImage();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
    }
}
