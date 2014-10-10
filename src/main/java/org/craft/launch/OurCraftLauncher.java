package org.craft.launch;

import argo.jdom.JdomParser;
import argo.jdom.JsonRootNode;
import org.craft.launch.gui.Animation;
import org.craft.launch.gui.GuiBackground;
import org.craft.launch.task.TaskManager;
import org.craft.launch.task.tasks.TaskDownloadLibraries;

import javax.swing.*;
import java.io.InputStreamReader;
import java.net.URL;

public class OurCraftLauncher extends JFrame
{
    public static OurCraftLauncher instance;

    public TaskManager taskManager;
    public JsonRootNode config;
    public GuiBackground background;

    public Animation logoAnimation, loginAnimation, passAnimation, buttonAnimation;

    public OurCraftLauncher() throws Exception
    {
        instance = this;

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

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

        logoAnimation = Animation.newAnimation(700);
        logoAnimation.startAnimation();

        loginAnimation = Animation.newAnimation(1000);
        loginAnimation.startAnimation();

        passAnimation = Animation.newAnimation(1500);
        passAnimation.startAnimation();

        buttonAnimation = Animation.newAnimation(1000);
        buttonAnimation.startAnimation();
    }

    public static void main(String[] args)
    {
        try
        {
            new OurCraftLauncher();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
