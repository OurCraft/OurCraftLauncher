package org.craft.launch.gui;

import java.awt.*;

import javax.swing.*;

public class PaintedTextField extends JTextField
{
    private Image image;

    public PaintedTextField(ImageIcon i)
    {
        image = i.getImage();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
    }
}
