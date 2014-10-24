package org.craft.launch.task;

import java.util.*;

public class TaskManager
{
    private List<ITask> tasks;
    private ITask       currentTask;

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
            currentTask = task;
            if(task.shouldExecute())
                task.execute();
        }
        currentTask = null;
    }

    public String getCurrentTaskName()
    {
        return currentTask == null ? "" : currentTask.getTaskName();
    }

    public String getCurrentTaskProgress()
    {
        return currentTask == null ? "" : currentTask.getTaskProgress();
    }
}
