package de.dragonrex.frame;

import de.dragonrex.math.Size;
import de.dragonrex.util.ImageUtils;

import java.awt.*;

public class FrameConfig {
    private String title = "Engine Demo";
    private boolean fullScreen = false;
    private Size windowSize = new Size(800, 600);
    private boolean centered = true;
    private Image icon = ImageUtils.readImage("assets/SimpleEngineLogo.jpg", 64, 64);

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getIcon() {
        return this.icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public boolean isFullScreen() {
        return this.fullScreen;
    }

    public void setWindowSize(Size windowSize) {
        this.windowSize = windowSize;
    }

    public Size getWindowSize() {
        return this.windowSize;
    }

    public void setCentered(boolean centered) {
        this.centered = centered;
    }

    public boolean isCentered() {
        return this.centered;
    }
}
