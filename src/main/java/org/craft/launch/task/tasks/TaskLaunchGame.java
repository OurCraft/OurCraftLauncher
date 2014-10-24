package org.craft.launch.task.tasks;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

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
        String filePath = OperatingSystem.getOperatingSystem().getBaseDir().getAbsolutePath() + File.separator + version + File.separator + "OurCraft-" + version + ".jar";
        try
        {
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", new Class<?>[]
            {
                    URL.class
            });
            addURL.setAccessible(true);
            addURL.invoke(classLoader, new Object[]
            {
                    new File(filePath).toURI().toURL()
            });
            String main = OurCraftLauncher.instance.remoteConfig.getStringValue("main");
            Class<?> clazz = Class.forName(main.split(":")[0]);
            HashMap<String, String> properties = new HashMap<String, String>();
            properties.put("username", username);
            properties.put("gamefolder", OperatingSystem.getOperatingSystem().getBaseDir().getAbsolutePath());
            Method startMethod = clazz.getMethod(main.split(":")[1], HashMap.class);
            startMethod.setAccessible(true);
            startMethod.invoke(null, properties);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
