package de.dragonrex.test.control;

import de.dragonrex.Engine;
import de.dragonrex.frame.EngineFrame;

public class ControlTest extends Engine {
    public ControlTest(int windowWidth, int windowHeight, int worldWidth, int worldHeight) {
        super(windowWidth, windowHeight, worldWidth, worldHeight);
        EngineFrame engineFrame = new EngineFrame(config -> {
            config.setFullScreen(false);
            config.setTitle("Control Test");
        }).build();
        engineFrame.add(this);
        engineFrame.start();
    }

    @Override
    protected void setup() {
        this.getObjects().add(new TestObject());
        this.getObjects().add(new Test2Object());
    }

    @Override
    protected void loop() {

    }

    public static void main(String[] args) {
        new ControlTest(500, 500, 2500, 2500).start();
    }
}
