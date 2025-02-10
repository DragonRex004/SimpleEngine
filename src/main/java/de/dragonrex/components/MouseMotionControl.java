package de.dragonrex.components;

import java.awt.event.MouseEvent;

public interface MouseMotionControl {
    void onMouseMoved(MouseEvent event);
    void onMouseDragged(MouseEvent event);
}
