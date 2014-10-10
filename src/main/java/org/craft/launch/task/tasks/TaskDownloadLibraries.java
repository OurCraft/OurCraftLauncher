package org.craft.launch.task.tasks;

import argo.jdom.JsonNode;
import org.apache.commons.io.FileUtils;
import org.craft.launch.OperatingSystem;
import org.craft.launch.OurCraftLauncher;
import org.craft.launch.task.ITask;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TaskDownloadLibraries implements ITask
{
    public String taskProgress;

    public String getTaskName()
    {
        return "Downloading libraries";
    }

    public String getTaskProgress()
    {
        return taskProgress;
    }

    public boolean shouldExecute()
    {
        return true;
    }

    public void execute()
    {
        for(JsonNode node : OurCraftLauncher.instance.config.getArrayNode("libraries"))
        {
            String split[] = node.getStringValue().split(":");
            String path = split[0].replace('.', '/') + "/" + split[1] + "/" + split[2] + "/" + split[1] + "-" + split[2];
            String name = split[1] + " " + split[2];
            path += ".jar";

            taskProgress = "Downloading library " + name + " from " + "http://repo1.maven.org/maven2/" + path;

            try
            {
                if (!getDestDir(path).exists()) FileUtils.copyURLToFile(new URL("http://repo1.maven.org/maven2/" + path), getDestDir(path));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public File getDestDir(String path)
    {
        return new File(OperatingSystem.getOperatingSystem().getBaseDir() + File.separator + "libraries" + File.separator + path.replace('/', File.separatorChar));
    }
}
