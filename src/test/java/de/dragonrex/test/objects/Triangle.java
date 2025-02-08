package de.dragonrex.test.objects;

import de.dragonrex.Engine;
import de.dragonrex.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Triangle extends GameObject {
    private String id;

    public Triangle(String id) {
        this.id = id;
    }

    @Override
    public void render(Graphics renderer) {
        renderer.setColor(Color.BLUE);
        renderer.fillRect(
                (int) (Engine.getEngine().getCamera().getPosition().getX() + 300),
                (int) (Engine.getEngine().getCamera().getPosition().getY() + 200),
                50, 50
        );
    }

    @Override
    public void update() {

    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String type() {
        return "ENGINE_CUBE";
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
