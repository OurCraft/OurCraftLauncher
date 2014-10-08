package org.craft.launch;

import argo.jdom.JdomParser;
import argo.jdom.JsonRootNode;
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
        background.setLayout(null);
        background.setDoubleBuffered(true);

        setContentPane(background);
        setVisible(true);

        background.animate();
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
