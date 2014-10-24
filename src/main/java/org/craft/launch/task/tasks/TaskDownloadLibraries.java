package org.craft.launch.task.tasks;

import java.io.*;
import java.net.*;

import argo.jdom.*;

import org.apache.commons.io.*;
import org.craft.launch.*;
import org.craft.launch.task.*;

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
        for(JsonNode node : OurCraftLauncher.instance.remoteConfig.getArrayNode("libraries"))
        {
            String split[] = node.getStringValue().split(":");
            String path = split[0].replace('.', '/') + "/" + split[1] + "/" + split[2] + "/" + split[1] + "-" + split[2];
            String name = split[1] + " " + split[2];
            path += ".jar";

            taskProgress = "Downloading library " + name + " from " + "http://repo1.maven.org/maven2/" + path;

            try
            {
                if(!getDestDir(path).exists())
                    FileUtils.copyURLToFile(new URL("http://repo1.maven.org/maven2/" + path), getDestDir(path));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            String version = OurCraftLauncher.instance.remoteConfig.getStringValue("version");
            String path = "OurCraft-" + version + ".jar";
            File ourcraftBinFile = new File(OperatingSystem.getOperatingSystem().getBaseDir() + File.separator + "versions" + File.separator + version + path);
            if(!ourcraftBinFile.exists())
                FileUtils.copyURLToFile(new URL("https://drone.io/github.com/OurCraft/OurCraft/files/" + path), ourcraftBinFile);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public File getDestDir(String path)
    {
        return new File(OperatingSystem.getOperatingSystem().getBaseDir() + File.separator + "libraries" + File.separator + path.replace('/', File.separatorChar));
    }
}
