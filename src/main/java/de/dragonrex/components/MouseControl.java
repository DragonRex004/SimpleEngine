package de.dragonrex.components;

import java.awt.event.MouseEvent;

public interface MouseControl {
    void onMousePressed(MouseEvent event);
    void onMouseReleased(MouseEvent event);
    void onMouseClicked(MouseEvent event);
}
