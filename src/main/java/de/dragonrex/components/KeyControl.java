package de.dragonrex.components;

import java.awt.event.KeyEvent;

public interface KeyControl {
    void onKeyPressed(KeyEvent event);
    void onKeyReleased(KeyEvent event);
    void onKeyTyped(KeyEvent event);
}
