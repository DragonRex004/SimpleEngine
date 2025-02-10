package de.dragonrex.test.control;

import de.dragonrex.GameObject;
import de.dragonrex.components.KeyControl;
import de.dragonrex.math.Position;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Test2Object extends GameObject {
    @Override
    public void render(Graphics renderer) {
        renderer.setColor(Color.BLACK);
        renderer.fillRect(400, 400, 50, 50);
    }

    @Override
    public void update() {

    }

    @Override
    public String id() {
        return "Test 123";
    }

    @Override
    public String type() {
        return "ENGINE_TEST";
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public Position getPosition() {
        return new Position(400, 400);
    }
}
