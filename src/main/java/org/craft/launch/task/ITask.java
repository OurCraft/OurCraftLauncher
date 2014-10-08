package org.craft.launch.task;

public interface ITask
{
    public String getTaskName();

    public boolean shouldExecute();

    public void execute();
}
