package org.craft.launch;

import java.io.*;
import java.net.*;

import javax.swing.*;

import argo.jdom.*;

import org.craft.launch.gui.*;
import org.craft.launch.task.*;

public class OurCraftLauncher extends JFrame
{
    private static final long      serialVersionUID = -1131125583603664129L;

    public static OurCraftLauncher instance;

    private static File            folder;

    public TaskManager             taskManager;
    public JsonRootNode            remoteConfig;
    public GuiBackground           background;

    public Animation               logoAnimation, loginAnimation, passAnimation, buttonAnimation;

    public OurCraftLauncher() throws Exception
    {
        instance = this;

        taskManager = new TaskManager();
        remoteConfig = new JdomParser().parse(new InputStreamReader(new URL("https://raw.githubusercontent.com/OurCraft/OurCraftLauncher/master/ourcraft.json").openStream()));

        setTitle("OurCraft Launcher");
        setSize(854, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        background = new GuiBackground();
        background.setDoubleBuffered(true);
        background.setLayout(null);

        setContentPane(background);
        setVisible(true);

        logoAnimation = Animation.newAnimation(700);
        logoAnimation.startAnimation();

        loginAnimation = Animation.newAnimation(500);
        loginAnimation.startAnimation();

        passAnimation = Animation.newAnimation(750);
        passAnimation.startAnimation();

        buttonAnimation = Animation.newAnimation(500);
        buttonAnimation.startAnimation();
    }

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new OurCraftLauncher();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static File getFolder()
    {
        if(folder == null)
        {
            String appdata = System.getenv("APPDATA");
            if(appdata != null)
                folder = new File(appdata, ".ourcraft");
            else
                folder = new File(System.getProperty("user.home"), ".ourcraft");
            if(!folder.exists())
                folder.mkdirs();
        }
        return folder;
    }
}
