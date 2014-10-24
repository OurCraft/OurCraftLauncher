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

            System.out.println("[OurCraft Launcher] " + taskProgress);
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
            File ourcraftBinFile = new File(OurCraftLauncher.getFolder().getAbsolutePath() + File.separator + "versions" + File.separator + version + File.separator + path);
            String url = "https://drone.io/github.com/OurCraft/OurCraft/files/build/libs/" + path;
            taskProgress = "Downloading " + path + " from " + url;
            System.out.println("[OurCraft Launcher] " + taskProgress);
            if(!ourcraftBinFile.exists())
                FileUtils.copyURLToFile(new URL(url), ourcraftBinFile);

            String spongeAPI = OurCraftLauncher.instance.remoteConfig.getStringValue("SpongeAPI-lib");
            File spongeBinFile = new File(OurCraftLauncher.getFolder().getAbsolutePath() + File.separator + "libraries/sponge/SpongeAPI.jar");
            String spongeURL = "https://drone.io/github.com/OurCraft/OurCraft/files/" + spongeAPI;
            taskProgress = "Downloading " + spongeAPI + " from " + spongeURL;
            System.out.println("[OurCraft Launcher] " + taskProgress);
            if(!spongeBinFile.exists())
                FileUtils.copyURLToFile(new URL(spongeURL), spongeBinFile);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public File getDestDir(String path)
    {
        return new File(OurCraftLauncher.getFolder().getAbsolutePath() + File.separator + "libraries" + File.separator + path.replace('/', File.separatorChar));
    }
}
