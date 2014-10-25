package org.craft.launch.task.tasks;

import java.io.*;

import org.apache.commons.io.*;
import org.craft.launch.*;

public class LWJGLSetup
{

    private static boolean loaded;

    /**
     * Load LWJGL in given folder
     */
    public static void load(File folder) throws Exception
    {
        if(!loaded)
        {
            if(!folder.exists())
                folder.mkdirs();
            if(folder.isDirectory())
            {
                OperatingSystem os = OperatingSystem.getOS();
                if(os == OperatingSystem.WINDOWS)
                {
                    if(!new File(folder.getPath() + "/jinput-dx8_64.dll").exists())
                    {
                        extractFromClasspath("windows/jinput-dx8_64.dll", folder);
                        extractFromClasspath("windows/jinput-dx8.dll", folder);
                        extractFromClasspath("windows/jinput-raw_64.dll", folder);
                        extractFromClasspath("windows/jinput-raw.dll", folder);
                        extractFromClasspath("windows/lwjgl.dll", folder);
                        extractFromClasspath("windows/lwjgl64.dll", folder);
                        extractFromClasspath("windows/OpenAL32.dll", folder);
                        extractFromClasspath("windows/OpenAL64.dll", folder);
                    }
                    else
                    {
                        System.out.println("Natives already exist.");
                    }
                }
                else if(os == OperatingSystem.SOLARIS)
                {
                    if(!new File(folder.getPath() + "/liblwjgl.so").exists())
                    {
                        extractFromClasspath("solaris/liblwjgl.so", folder);
                        extractFromClasspath("solaris/liblwjgl64.so", folder);
                        extractFromClasspath("solaris/libopenal.so", folder);
                        extractFromClasspath("solaris/libopenal64.so", folder);
                    }
                    else
                    {
                        System.out.println("Natives already exist.");
                    }

                }
                else if(os == OperatingSystem.LINUX)
                {
                    if(!new File(folder.getPath() + "/liblwjgl.so").exists())
                    {
                        extractFromClasspath("linux/liblwjgl.so", folder);
                        extractFromClasspath("linux/liblwjgl64.so", folder);
                        extractFromClasspath("linux/libopenal.so", folder);
                        extractFromClasspath("linux/libopenal64.so", folder);
                    }
                    else
                    {
                        System.out.println("Natives already exist.");
                    }

                }
                else if(os == OperatingSystem.MACOSX)
                {
                    if(!new File(folder.getPath() + "/openal.dylib").exists())
                    {
                        extractFromClasspath("macosx/liblwjgl.jnilib", folder);
                        extractFromClasspath("macosx/liblwjgl-osx.jnilib", folder);
                        extractFromClasspath("macosx/openal.dylib", folder);
                    }
                    else
                    {
                        System.out.println("Natives already exist.");
                    }
                }
                else
                {
                    System.err.println("Operating System couldn't be iditified");
                }
                System.setProperty("net.java.games.input.librarypath", folder.getAbsolutePath());
                System.setProperty("org.lwjgl.librarypath", folder.getAbsolutePath());
            }
            loaded = true;
        }
    }

    /**
     * Extract given file from classpath into given folder
     */
    private static void extractFromClasspath(String fileName, File folder)
    {
        try
        {
            String[] split = fileName.split("" + File.separatorChar);
            FileOutputStream out = new FileOutputStream(new File(folder, split[split.length - 1]));
            IOUtils.copy(LWJGLSetup.class.getResourceAsStream("/" + fileName), out);
            out.flush();
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
