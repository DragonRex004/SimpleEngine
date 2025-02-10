package de.dragonrex;

import de.dragonrex.components.KeyControl;
import de.dragonrex.components.MouseControl;
import de.dragonrex.components.MouseMotionControl;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Engine extends Canvas implements KeyListener, Runnable, MouseMotionListener, MouseListener, ComponentListener {
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
        this.getObjects().forEach(gameObject -> {
            if(gameObject instanceof KeyControl keyControl) keyControl.onKeyPressed(e);
        });
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.getObjects().forEach(gameObject -> {
            if(gameObject instanceof KeyControl keyControl) keyControl.onKeyReleased(e);
        });
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
        this.getObjects().forEach(gameObject -> {
            if(gameObject instanceof KeyControl keyControl) keyControl.onKeyTyped(e);
        });
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.getObjects().forEach(gameObject -> {
            if(gameObject instanceof MouseMotionControl mouseControl) mouseControl.onMouseMoved(e);
        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.getObjects().forEach(gameObject -> {
            if(gameObject instanceof MouseMotionControl mouseControl) mouseControl.onMouseDragged(e);
        });
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.getObjects().forEach(gameObject -> {
            if(gameObject instanceof MouseControl mouseControl) mouseControl.onMousePressed(e);
        });
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.getObjects().forEach(gameObject -> {
            if(gameObject instanceof MouseControl mouseControl) mouseControl.onMouseReleased(e);
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.getObjects().forEach(gameObject -> {
            if(gameObject instanceof MouseControl mouseControl) mouseControl.onMouseClicked(e);
        });
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

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
