package org.craft.launch;

import java.io.*;
import java.net.*;

import javax.swing.*;

import argo.jdom.*;

import org.craft.launch.gui.*;
import org.craft.launch.task.*;
import org.craft.launch.task.tasks.*;

public class OurCraftLauncher extends JFrame
{
    public static OurCraftLauncher instance;

    public TaskManager             taskManager;
    public JsonRootNode            config;
    public GuiBackground           background;

    public Animation               logoAnimation, loginAnimation, passAnimation;

    public OurCraftLauncher() throws Exception
    {
        instance = this;

        taskManager = new TaskManager();
        config = new JdomParser().parse(new InputStreamReader(new URL("https://raw.githubusercontent.com/OurCraft/OurCraftLauncher/master/ourcraft.json").openStream()));

        taskManager.addTasksToList(new TaskDownloadLibraries());
        taskManager.startTasks();

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

        logoAnimation = Animation.newAnimation(1000);
        logoAnimation.startAnimation();

        loginAnimation = Animation.newAnimation(1000);
        loginAnimation.startAnimation();

        passAnimation = Animation.newAnimation(1500);
        passAnimation.startAnimation();
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
}
