package org.craft.launch.task;

import java.util.*;

public class TaskManager
{
    private List<ITask> tasks;

    public TaskManager()
    {
        tasks = new ArrayList<ITask>();
    }

    public void addTasksToList(ITask... t)
    {
        tasks.addAll(Arrays.asList(t));
    }

    public void startTasks()
    {
        for(ITask task : tasks)
        {
            System.out.println("[OurCraft Launcher] " + task.getTaskName() + "...");
            if(task.shouldExecute())
                task.execute();
        }
    }
}
