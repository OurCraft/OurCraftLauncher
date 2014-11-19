package org.craft.launch.task.tasks;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import argo.jdom.*;

import org.craft.launch.*;
import org.craft.launch.task.*;

public class TaskLaunchGame implements ITask
{

    private String username;

    public TaskLaunchGame(String username)
    {
        this.username = username;
    }

    @Override
    public String getTaskName()
    {
        return "Launch game";
    }

    @Override
    public String getTaskProgress()
    {
        return "Launching game";
    }

    @Override
    public boolean shouldExecute()
    {
        return true;
    }

    @Override
    public void execute()
    {
        String version = OurCraftLauncher.instance.remoteConfig.getStringValue("version");
        String filePath = OurCraftLauncher.getFolder().getAbsolutePath() + File.separator + "versions" + File.separator + version + File.separator + "OurCraft-" + version + ".jar";
        try
        {
            String classpath = "";
            String classpathSeparator = OperatingSystem.getOS() == OperatingSystem.WINDOWS ? ";" : ":";
            for(JsonNode node : OurCraftLauncher.instance.remoteConfig.getArrayNode("libraries"))
            {
                String split[] = node.getStringValue().split(":");
                String path = split[0].replace('.', '/') + "/" + split[1] + "/" + split[2] + "/" + split[1] + "-" + split[2];
                path += ".jar";
                classpath += OurCraftLauncher.getFolder().getAbsolutePath() + File.separator + "libraries" + File.separator + path.replace('/', File.separatorChar) + classpathSeparator;
            }
            classpath += OurCraftLauncher.getFolder().getAbsolutePath() + "/libraries/sponge/SpongeAPI.jar" + classpathSeparator;

            classpath += filePath + classpathSeparator;
            String main = OurCraftLauncher.instance.remoteConfig.getStringValue("main");
            LWJGLSetup.load(new File(OurCraftLauncher.getFolder(), "natives"));
            HashMap<String, String> properties = new HashMap<String, String>();
            properties.put("username", username);
            properties.put("lang", "en_US");
            properties.put("gamefolder", OurCraftLauncher.getFolder().getAbsolutePath());
            properties.put("nativesFolder", OurCraftLauncher.getFolder().getAbsolutePath() + "/natives/");
            ArrayList<String> launchArgs = new ArrayList<String>();
            launchArgs.add("java");
            launchArgs.add("-Djava.system.class.loader=org.craft.OurClassLoader");
            launchArgs.add("-cp");
            launchArgs.add(classpath);
            launchArgs.add(main);
            for(Entry<String, String> entry : properties.entrySet())
            {
                launchArgs.add("--" + entry.getKey());
                launchArgs.add(entry.getValue());
            }

            LWJGLSetup.load(new File(OurCraftLauncher.getFolder().getAbsolutePath() + "/natives/"));
            ProcessBuilder processBuilder = new ProcessBuilder(launchArgs);
            OurCraftLauncher.instance.dispose();
            int exitCode = processBuilder.inheritIO().start().waitFor();
            System.err.print("Game exited with code: " + exitCode);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

}
