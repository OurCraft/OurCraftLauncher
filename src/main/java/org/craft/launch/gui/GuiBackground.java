package org.craft.launch.gui;

import org.craft.launch.OurCraftLauncher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class GuiBackground extends JPanel
{
    public PaintedPasswordField passwordField;
    public PaintedTextField textField;
    public JButton loginButton;

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

        passwordField = new PaintedPasswordField(new ImageIcon(ImageIO.read(GuiBackground.class.getResourceAsStream("/field.png"))));
        passwordField.setSize(200, 25);
        add(passwordField);

        textField = new PaintedTextField(new ImageIcon(ImageIO.read(GuiBackground.class.getResourceAsStream("/field.png"))));
        textField.setSize(200, 25);
        add(textField);

        loginButton = new JButton(new ImageIcon(ImageIO.read(GuiBackground.class.getResourceAsStream("/play.png"))));
        loginButton.setSize(40, 60);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        add(loginButton);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(backgrounds[imageID], 0, 0, getWidth(), getHeight(), null);
        g.drawImage(logo, (getWidth() / 2) - (logo.getWidth(null) / 2), 0, logo.getWidth(null), logo.getHeight(null) * ((int) (OurCraftLauncher.instance.logoAnimation.getProgress() * 100)) / 100, null);

        passwordField.setLocation(-passwordField.getSize().width + (int) ((passwordField.getWidth() + 5) * OurCraftLauncher.instance.passAnimation.getProgress()), 480 - 50 - 10);
        textField.setLocation(-textField.getSize().width + (int) ((textField.getWidth() + 5) * OurCraftLauncher.instance.loginAnimation.getProgress()), 480 - 10 - 50 - 10 - 25);
        loginButton.setLocation(212, 480 - (int) (95 * OurCraftLauncher.instance.buttonAnimation.getProgress()));

        updateUI();
    }
}