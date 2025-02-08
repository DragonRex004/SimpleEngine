package de.dragonrex;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Engine extends Canvas implements KeyListener, Runnable, MouseMotionListener, ComponentListener {
    private static Engine engine;
    private final Camera camera;
    private Thread engineThread;
    private List<GameObject> objects;
    private int worldWidth;
    private int worldHeight;
    private int windowWidth;
    private int windowHeight;

    private Image offscreenImage;
    private Graphics offscreenGraphics;

    public Engine(int windowWidth, int windowHeight, int worldWidth, int worldHeight) {
        engine = this;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.objects = new ArrayList<>();
        this.addKeyListener(this);
        this.addComponentListener(this);
        this.setPreferredSize(new Dimension(this.windowWidth, this.windowHeight));
        this.camera = new Camera(0, 0, 5);
        this.setup();
    }


    public void start() {
        this.engineThread = new Thread(this);
        this.engineThread.setName("ENGINE-1.0.0");
        this.engineThread.start();
    }

    protected abstract void setup();
    protected abstract void loop();
    protected abstract void onKeyboard(KeyEvent event, boolean keyPressed);
    protected abstract void onMouse(MouseEvent event, boolean mouseMoved);


    @Override
    public void run() {
        while (this.engineThread.isAlive()) {
            this.objects.forEach(GameObject::update);
            this.loop();
            this.camera.update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Graphics g) {
        if (offscreenImage == null) {
            offscreenImage = createImage(getWidth(), getHeight());
            offscreenGraphics = offscreenImage.getGraphics();
        }

        offscreenGraphics.clearRect(0, 0, getWidth(), getHeight());
        paint(offscreenGraphics);
        g.drawImage(offscreenImage, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        this.objects.forEach(object -> object.render(g));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (GameObject gameObject : this.objects) {
            gameObject.keyPressed(e);
        }
        this.onKeyboard(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (GameObject gameObject : this.objects) {
            gameObject.keyReleased(e);
        }
        this.onKeyboard(e, false);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        if(this.camera != null) this.camera.calculateView();
        offscreenImage = null;
    }

    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {
        for (GameObject gameObject : this.objects) {
            gameObject.keyTyped(e);
        }
        this.onKeyboard(e, false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.onMouse(e, true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.onMouse(e, false);
    }

    public static Engine getEngine() {
        return engine;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public Camera getCamera() {
        return camera;
    }

    public List<GameObject> getObjects() {
        return objects;
    }
}
