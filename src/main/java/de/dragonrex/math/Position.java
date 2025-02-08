package de.dragonrex.math;

public class Position {
    private double x, y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Position position) {
        this.x += position.getX();
        this.y += position.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }
}
