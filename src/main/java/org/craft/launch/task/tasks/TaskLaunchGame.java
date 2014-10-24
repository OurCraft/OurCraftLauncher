package org.craft.launch.task.tasks;

import org.craft.launch.task.*;

public class TaskLaunchGame implements ITask
{

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

    }

}
