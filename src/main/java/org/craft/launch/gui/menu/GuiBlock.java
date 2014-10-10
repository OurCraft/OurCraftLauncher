package org.craft.launch.gui.menu;

import javax.swing.*;
import java.awt.*;

public class GuiBlock
{
    private Image       image;
    private int         posX, posY, width, height;
    private GuiBlock    parent;
    private Direction   direction;

    public GuiBlock(ImageIcon i, int x, int y, int w, int h)
    {
        image = i.getImage();
        posX = x;
        posY = y;
        width = w;
        height = h;
    }

    public GuiBlock(ImageIcon i, GuiBlock p, Direction d)
    {
        image = i.getImage();
        posX = p.posX;
        posY = p.posY;
        width = p.width;
        height = p.height;
        parent = p;
        direction = d;
    }

    public void draw(Graphics g)
    {
        if (parent != null && direction != null) g.drawImage(image, posX + (direction == Direction.LEFT ? -width : direction == Direction.RIGHT ? width : 0), posY + (direction == Direction.UP ? -height : direction == Direction.DOWN ? height : 0), width, height, null);
        else g.drawImage(image, posX, posY, width, height, null);
    }
}
