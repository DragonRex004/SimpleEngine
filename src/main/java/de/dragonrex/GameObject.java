package de.dragonrex;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class GameObject {
    public abstract void render(Graphics renderer);
    public abstract void update();
    public abstract String id();
    public abstract String type();
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
    public abstract void keyTyped(KeyEvent e);
}
