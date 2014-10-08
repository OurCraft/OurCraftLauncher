package org.craft.launch.task.tasks;

import argo.jdom.JsonNode;
import org.craft.launch.OurCraftLauncher;
import org.craft.launch.task.ITask;

public class TaskDownloadLibraries implements ITask
{
    public String getTaskName()
    {
        return "Downloading libraries...";
    }

    public boolean shouldExecute()
    {
        return true;
    }

    public void execute()
    {
        for (JsonNode node : OurCraftLauncher.instance.config.getArrayNode("libraries"))
        {
            String split[] = node.getStringValue().split(":");
            String path = split[0].replace('.', '/') + "/" + split[1] + "/" + split[2] + "/" + split[1] + "-" + split[2];
            String name = split[1] + " " + split[2];
            path += ".jar";

            System.out.println("[OurCraft Launcher] Downloading library " + name + " from " + "http://repo1.maven.org/maven2/" + path);
        }
    }
}
