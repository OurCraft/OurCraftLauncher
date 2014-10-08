package org.craft.launch.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animation
{
    private int duration;
    private float progress;

    public Animation(int d)
    {
        duration = d;
    }

    public float getProgress()
    {
        return progress;
    }

    public void startAnimation()
    {
        int framesPerSecond = 60;
        int delay = 1000 / framesPerSecond;
        final long start = System.currentTimeMillis();
        final Timer timer = new Timer(delay, null);
        timer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                final long now = System.currentTimeMillis();
                final long elapsed = now - start;
                progress = ((float) elapsed / duration);
                if (elapsed >= duration) timer.stop();
            }
        });
        timer.start();
    }

    public static Animation newAnimation(int duration)
    {
        return new Animation(duration);
    }
}
