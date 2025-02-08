package de.dragonrex;

import de.dragonrex.impl.Cube;
import de.dragonrex.impl.Triangle;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Simple2DEngine extends Canvas implements KeyListener, Runnable, MouseMotionListener, ComponentListener {
    private static Simple2DEngine engine;
    private List<GameObject> objects;

    private double cameraX = 0;
    private double cameraY = 0;
    private double dxCam = 0;
    private double dyCam = 0;
    private int worldWidth = 10000;
    private int worldHeight = 10000;
    private int windowWidth = 800;
    private int windowHeight = 600;

    private double speed = 5;

    private Image offscreenImage;
    private Graphics offscreenGraphics;

    public Simple2DEngine() {
        engine = this;
        this.objects = new ArrayList<>();
        this.addKeyListener(this);
        this.addComponentListener(this);
        this.setFocusable(true);
        this.setSize(windowWidth, windowHeight);
        this.objects.add(new Triangle("Test-2"));
        this.objects.add(new Cube("TEST-1", 100, 100, 5));
    }

    // Hauptmethode, um das Fenster zu starten
    public static void main(String[] args) {
        Frame frame = new Frame("2D Engine");
        Simple2DEngine engine = new Simple2DEngine();
        frame.add(engine);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowStateChanged(WindowEvent e) {
                System.out.println(e.getWindow().getWidth());
                System.out.println(e.getWindow().getHeight());
            }
        });
        engine.start();
    }


    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            this.objects.forEach(GameObject::update);
            updateCamera();
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
    public void updateCamera() {
        cameraX += dxCam;
        cameraY += dyCam;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        this.objects.forEach(object -> object.render(g));
    }

    // Tasteneingaben für die Bewegung
    @Override
    public void keyPressed(KeyEvent e) {
        for (GameObject gameObject : this.objects) {
            gameObject.keyPressed(e);
        }
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D) {
            dxCam  = -speed; // Kamera nach links bewegen
        } else if (key == KeyEvent.VK_A) {
            dxCam = speed; // Kamera nach rechts bewegen
        } else if (key == KeyEvent.VK_S) {
            dyCam = -speed; // Kamera nach oben bewegen
        } else if (key == KeyEvent.VK_W) {
            dyCam = speed; // Kamera nach unten bewegen
        }
        int halfWindowWidth = windowWidth / 2;
        int halfWindowHeight = windowHeight / 2;

        cameraX = Math.max(-halfWindowWidth, Math.min(cameraX, worldWidth - halfWindowWidth));
        cameraY = Math.max(-halfWindowHeight, Math.min(cameraY, worldHeight - halfWindowHeight));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (GameObject gameObject : this.objects) {
            gameObject.keyReleased(e);
        }

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
            dyCam = 0;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_A) {
            dxCam = 0;
        }
    }

    // Abfangen der Größenänderung des Fensters
    @Override
    public void componentResized(ComponentEvent e) {
        // Aktualisiere die Fenstergröße
        windowWidth = getWidth();
        windowHeight = getHeight();

        // Optional: Kamera oder andere Parameter nach der Größenänderung anpassen
        System.out.println("Neues Fenstergröße: " + windowWidth + "x" + windowHeight);

        // Stelle sicher, dass die Kamera innerhalb der Weltgrenzen bleibt
        int halfWindowWidth = windowWidth / 2;
        int halfWindowHeight = windowHeight / 2;

        cameraX = Math.max(-halfWindowWidth, Math.min(cameraX, worldWidth - halfWindowWidth));
        cameraY = Math.max(-halfWindowHeight, Math.min(cameraY, worldHeight - halfWindowHeight));

        offscreenImage = null;
    }

    // Leere Implementierungen der anderen Methoden des ComponentListener
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
    }

    // Mausbewegung, um die Kamera zu steuern
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    // Die Methode `mouseDragged` wird in diesem Fall nicht benötigt, aber sie könnte genutzt werden
    @Override
    public void mouseDragged(MouseEvent e) {}

    public static Simple2DEngine getEngine() {
        return engine;
    }
    /*
    public double getXPos() {
        return x;
    }

    public double getYPos() {
        return y;
    }
    */
    public double getCameraX() {
        return cameraX;
    }

    public double getCameraY() {
        return cameraY;
    }
}
