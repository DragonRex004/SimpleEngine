package de.dragonrex;

import de.dragonrex.math.Position;

public class Camera {
    private double speed;
    private Position position;
    private Position dPosition;

    public Camera(double x, double y, double speed) {
        this.speed = speed;
        this.dPosition = new Position(0,0);
        this.position = new Position(x, y);
    }

    public void update() {
        this.position.add(this.dPosition);
        calculateView();
    }

    public void moveUp() {
        this.dPosition.setY(-this.speed);
        calculateView();
    }

    public void moveDown() {
        this.dPosition.setY(this.speed);
        calculateView();
    }

    public void moveRight() {
        this.dPosition.setX(this.speed);
        calculateView();
    }

    public void moveLeft() {
        this.dPosition.setX(-this.speed);
        calculateView();
    }

    public void resetYDPosition() {
        this.dPosition.setY(0);
    }

    public void resetXDPosition() {
        this.dPosition.setX(0);
    }

    public void calculateView() {
        int halfWindowWidth = Engine.getEngine().getWindowWidth() / 2;
        int halfWindowHeight = Engine.getEngine().getWindowHeight() / 2;

        position.setX(Math.max(-halfWindowWidth, Math.min(position.getX(), Engine.getEngine().getWorldWidth() - halfWindowWidth)));
        position.setY(Math.max(-halfWindowHeight, Math.min(position.getY(), Engine.getEngine().getWorldHeight() - halfWindowHeight)));
    }

    public Position getdPosition() {
        return dPosition;
    }

    public double getSpeed() {
        return speed;
    }

    public Position getPosition() {
        return position;
    }
}
