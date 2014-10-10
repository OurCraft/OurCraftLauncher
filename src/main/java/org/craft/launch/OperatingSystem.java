package org.craft.launch;

import java.io.File;
import java.util.Locale;

public enum OperatingSystem
{
    WINDOWS,
    MAC,
    LINUX;

    public static OperatingSystem getOperatingSystem()
    {
        return System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win") ? WINDOWS : System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("mac") ? MAC : LINUX;
    }

    public File getBaseDir()
    {
        switch (this)
        {
            case WINDOWS:
                return new File(System.getenv("APPDATA") + File.separator + ".ourcraft" + File.separator);
            case MAC:
                return new File(System.getProperty("user.home", ".") + File.separator + "Library" + File.separator + "Application Support" + File.separator + "OurCraft");
            default:
                return new File(System.getProperty("user.home", ".") + File.separator + ".ourcraft" + File.separator);
        }
    }
}