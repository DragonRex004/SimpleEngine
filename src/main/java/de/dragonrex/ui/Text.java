package de.dragonrex.ui;

import de.dragonrex.GameObject;
import de.dragonrex.math.Position;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class Text extends GameObject {
    private String id;
    private String text = "New Text";
    private Font font = Font.getFont("Arial");
    private Position position = new Position(0,0);
    private Color textColor = Color.WHITE;
    private Runnable onUpdate = () -> {};
    private Consumer<KeyEvent> onKeyPressed = (keyEvent) -> {};
    private Consumer<KeyEvent> onKeyReleased = (keyEvent) -> {};
    private Consumer<KeyEvent> onKeyTyped = (keyEvent) -> {};

    public Text(String id) {
        this.id = id;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void onUpdate(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }

    public void onKeyPressed(Consumer<KeyEvent> onKeyPressed) {
        this.onKeyPressed = onKeyPressed;
    }

    public void onKeyReleased(Consumer<KeyEvent> onKeyReleased) {
        this.onKeyReleased = onKeyReleased;
    }

    public void onKeyTyped(Consumer<KeyEvent> onKeyTyped) {
        this.onKeyTyped = onKeyTyped;
    }

    @Override
    public void render(Graphics renderer) {
        renderer.setFont(this.font);
        renderer.setColor(this.textColor);
        renderer.drawString(this.text,(int) position.getX(),(int) position.getY());
    }

    @Override
    public void update() {
        this.onUpdate.run();
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String type() {
        return "ENGINE_TEXT";
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.onKeyPressed.accept(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.onKeyReleased.accept(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        this.onKeyTyped.accept(e);
    }
}
