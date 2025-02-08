package de.dragonrex.test;

import de.dragonrex.Engine;
import de.dragonrex.GameObject;
import de.dragonrex.math.Position;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Cube extends GameObject {
    private String id;
    private Position position;
    private Position dPosition;
    private double speed;

    public Cube(String id, double x, double y, double speed) {
        this.id = id;
        this.dPosition = new Position(0, 0);
        this.position = new Position(x, y);
        this.speed = speed;
    }

    @Override
    public void render(Graphics renderer) {
        renderer.setColor(Color.RED);
        renderer.fillRect(
                (int) (Engine.getEngine().getCamera().getPosition().getX() + position.getX()),
                (int) (Engine.getEngine().getCamera().getPosition().getY() + position.getY()),
                50, 50
        );
    }

    @Override
    public void update() {
        this.position.add(this.dPosition);
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
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dPosition.setX(-speed);
        } else if (key == KeyEvent.VK_RIGHT) {
            dPosition.setX(speed);
        } else if (key == KeyEvent.VK_UP) {
            dPosition.setY(-speed);
        } else if (key == KeyEvent.VK_DOWN) {
            dPosition.setY(speed);
        }

        if (key == KeyEvent.VK_LEFT && dPosition.getY() == -speed) {
            dPosition.setX(-speed * Math.cos(Math.PI / 4));
            dPosition.setY(-speed * Math.sin(Math.PI / 4));
        } else if (key == KeyEvent.VK_RIGHT && dPosition.getY() == -speed) {
            dPosition.setX(speed * Math.cos(Math.PI / 4));
            dPosition.setY(-speed * Math.sin(Math.PI / 4));
        } else if (key == KeyEvent.VK_LEFT && dPosition.getY() == speed) {
            dPosition.setX(-speed * Math.cos(Math.PI / 4));
            dPosition.setY(speed * Math.sin(Math.PI / 4));
        } else if (key == KeyEvent.VK_RIGHT && dPosition.getY() == speed) {
            dPosition.setX(speed * Math.cos(Math.PI / 4));
            dPosition.setY(speed * Math.sin(Math.PI / 4));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dPosition.setY(0);
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dPosition.setX(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
