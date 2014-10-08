package org.craft.launch.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animation
{
    private static int duration;
    private static float progress;

    public Animation(int d)
    {
        duration = d;
    }

    public int getDuration()
    {
        return duration;
    }

    public float getProgress()
    {
        return progress;
    }

    private void setProgress(float p)
    {
        progress = p;
    }

    public static Animation animate(int d)
    {
        final Animation animation = new Animation(d);

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
                animation.setProgress((float) elapsed / animation.getDuration());
                if (elapsed >= animation.getDuration()) timer.stop();
            }
        });
        timer.start();

        return animation;
    }
}
