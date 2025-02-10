package de.dragonrex.test.control;

import de.dragonrex.GameObject;
import de.dragonrex.components.KeyControl;
import de.dragonrex.math.Position;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TestObject extends GameObject implements KeyControl {
    @Override
    public void render(Graphics renderer) {
        renderer.setColor(Color.WHITE);
        renderer.fillRect(250, 250, 50, 50);
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
        return new Position(250, 250);
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        System.out.println("Test Object Key Pressed: " + event.getKeyChar());
    }

    @Override
    public void onKeyReleased(KeyEvent event) {
        System.out.println("Test Object Key Released: " + event.getKeyChar());
    }

    @Override
    public void onKeyTyped(KeyEvent event) {
        System.out.println("Test Object Key Typed: " + event.getKeyChar());
    }
}
