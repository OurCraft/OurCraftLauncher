package org.craft.launch.task;

public interface ITask
{
    public String getTaskName();

    public String getTaskProgress();

    public boolean shouldExecute();

    public void execute();
}
