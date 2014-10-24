package org.craft.launch;


public enum OperatingSystem
{
    WINDOWS, LINUX, MACOSX, SOLARIS, UNKNOWN;

    public static OperatingSystem getOS()
    {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win"))
        {
            return OperatingSystem.WINDOWS;
        }
        if(os.contains("sunos") || os.contains("solaris"))
        {
            return OperatingSystem.SOLARIS;
        }
        if(os.contains("unix"))
        {
            return OperatingSystem.LINUX;
        }
        if(os.contains("linux"))
        {
            return OperatingSystem.LINUX;
        }
        if(os.contains("mac"))
        {
            return OperatingSystem.MACOSX;
        }
        return OperatingSystem.UNKNOWN;
    }

}
