package de.dragonrex.test;

import de.dragonrex.Engine;
import de.dragonrex.frame.EngineFrame;
import de.dragonrex.test.objects.Cube;
import de.dragonrex.test.objects.Triangle;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Simple2DEngine extends Engine {

    public Simple2DEngine() {
        super(800, 600, 100000, 100000);
        EngineFrame engineFrame = new EngineFrame(config -> {
            config.setFullScreen(true);
        }).build();
        engineFrame.add(this);
        engineFrame.start();
    }

    public static void main(String[] args) {
        new Simple2DEngine().start();
    }

    @Override
    protected void setup() {
        this.getObjects().add(new Triangle("Test-2"));
        this.getObjects().add(new Cube("TEST-1", 100, 100, 5));
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void onKeyboard(KeyEvent event, boolean keyPressed) {
        if(keyPressed) {
            int key = event.getKeyCode();
            if (key == KeyEvent.VK_D) this.getCamera().moveLeft();
            if (key == KeyEvent.VK_A) this.getCamera().moveRight();
            if (key == KeyEvent.VK_S) this.getCamera().moveUp();
            if (key == KeyEvent.VK_W) this.getCamera().moveDown();
        } else {
            if (event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) this.getCamera().resetYDPosition();
            if (event.getKeyCode() == KeyEvent.VK_D || event.getKeyCode() == KeyEvent.VK_A) this.getCamera().resetXDPosition();
        }
    }

    @Override
    protected void onMouse(MouseEvent event, boolean mouseMoved) {

    }
}
