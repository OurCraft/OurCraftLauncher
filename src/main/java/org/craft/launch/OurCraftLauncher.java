package org.craft.launch;

import org.craft.launch.gui.GuiBackground;

import javax.swing.*;

public class OurCraftLauncher extends JFrame
{
    public GuiBackground background;

    public OurCraftLauncher()
    {
        setTitle("OurCraft Launcher");
        setSize(854, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        background = new GuiBackground();
        background.setLayout(null);
        background.setDoubleBuffered(true);

        setContentPane(background);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new OurCraftLauncher();
    }
}
