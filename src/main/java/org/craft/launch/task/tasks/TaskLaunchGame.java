package org.craft.launch.task.tasks;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

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
        System.out.print("Adding " + filePath + " to classpath");
        try
        {
            for(JsonNode node : OurCraftLauncher.instance.remoteConfig.getArrayNode("libraries"))
            {
                String split[] = node.getStringValue().split(":");
                String path = split[0].replace('.', '/') + "/" + split[1] + "/" + split[2] + "/" + split[1] + "-" + split[2];
                path += ".jar";
                injectIntoClasspath(OurCraftLauncher.getFolder().getAbsolutePath() + File.separator + "libraries" + File.separator + path.replace('/', File.separatorChar));
            }
            injectIntoClasspath(filePath);
            injectIntoClasspath(OurCraftLauncher.getFolder().getAbsolutePath() + File.separator + "libraries" + File.separator + "sponge/SpongeAPI.jar");

            LWJGLSetup.load(new File(OurCraftLauncher.getFolder(), "natives"));
            String main = OurCraftLauncher.instance.remoteConfig.getStringValue("main");
            Class<?> clazz = Class.forName(main.split(":")[0]);
            HashMap<String, String> properties = new HashMap<String, String>();
            properties.put("username", username);
            properties.put("lang", "en_US");
            properties.put("gamefolder", OurCraftLauncher.getFolder().getAbsolutePath());
            Method startMethod = clazz.getMethod(main.split(":")[1], HashMap.class);
            startMethod.setAccessible(true);
            startMethod.invoke(null, properties);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    private void injectIntoClasspath(String path) throws Exception
    {
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", new Class<?>[]
        {
                URL.class
        });
        addURL.setAccessible(true);
        addURL.invoke(classLoader, new Object[]
        {
                new File(path).toURI().toURL()
        });
    }
}
