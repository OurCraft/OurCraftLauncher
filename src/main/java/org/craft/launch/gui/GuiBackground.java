package org.craft.launch.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;
import javax.swing.*;

import org.craft.launch.*;
import org.craft.launch.gui.menu.*;
import org.craft.launch.task.tasks.*;

public class GuiBackground extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 5261908529438409572L;

    public JPasswordField     passwordField;
    public JTextField         loginField;
    public JButton            loginButton;
    public GuiBlock[]         grassBlocks, flowerBlocks;

    public Random             random;
    public Image[]            backgrounds;
    public Image              logo;
    public int                imageID;

    public GuiBackground() throws IOException
    {
        random = new Random();
        backgrounds = new Image[1];

        logo = ImageIO.read(GuiBackground.class.getResourceAsStream("/logo.png"));
        for(int i = 0; i < backgrounds.length; i++ )
            backgrounds[i] = ImageIO.read(GuiBackground.class.getResourceAsStream("/background" + i + ".png"));
        imageID = random.nextInt(backgrounds.length);

        loginField = new JTextField();
        loginField.setSize(200, 25);
        add(loginField);

        passwordField = new JPasswordField();
        passwordField.setSize(200, 25);
        add(passwordField);

        loginButton = new JButton("Play");
        loginButton.setSize(100, 65);
        loginButton.setOpaque(false);
        loginButton.addActionListener(this);
        add(loginButton);

        grassBlocks = new GuiBlock[14];
        flowerBlocks = new GuiBlock[3];

        for(int i = 0; i < grassBlocks.length; i++ )
            grassBlocks[i] = new GuiBlock(new ImageIcon(ImageIO.read(GuiBackground.class.getResourceAsStream("/grass_side.png"))), 64 * i, 480 - 93, 64, 64);
        for(int i = 0; i < flowerBlocks.length; i++ )
            flowerBlocks[i] = new GuiBlock(new ImageIcon(ImageIO.read(GuiBackground.class.getResourceAsStream("/rose.png"))), grassBlocks[random.nextInt(grassBlocks.length - 1)], Direction.UP);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(backgrounds[imageID], 0, 0, getWidth(), getHeight(), null);
        g.drawImage(logo, (getWidth() / 2) - (logo.getWidth(null) / 2), 0, logo.getWidth(null), logo.getHeight(null) * ((int) (OurCraftLauncher.instance.logoAnimation.getProgress() * 100)) / 100, null);

        passwordField.setLocation(-passwordField.getSize().width + (int) ((passwordField.getWidth() + 5) * OurCraftLauncher.instance.passAnimation.getProgress()), 480 - 50 - 10);
        loginField.setLocation(-loginField.getSize().width + (int) ((loginField.getWidth() + 5) * OurCraftLauncher.instance.loginAnimation.getProgress()), 480 - 10 - 50 - 10 - 25);
        loginButton.setLocation(212, 480 - (int) (95 * OurCraftLauncher.instance.buttonAnimation.getProgress()));

        g.drawString("Current task:                " + OurCraftLauncher.instance.taskManager.getCurrentTaskName(), 0, 8);
        g.drawString("Current task progress: " + OurCraftLauncher.instance.taskManager.getCurrentTaskProgress(), 0, 18);

        for(GuiBlock block : grassBlocks)
            block.draw(g);
        for(GuiBlock block : flowerBlocks)
            block.draw(g);

        updateUI();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == loginButton)
        {
            new Thread()
            {
                public void run()
                {
                    OurCraftLauncher.instance.taskManager.addTasksToList(new TaskDownloadLibraries());
                    OurCraftLauncher.instance.taskManager.startTasks();
                }
            }.start();
        }
    }
}
