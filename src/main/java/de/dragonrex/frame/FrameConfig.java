package de.dragonrex.frame;

import de.dragonrex.math.Size;

public class FrameConfig {
    private String title = "Engine Demo";
    private boolean fullScreen = false;
    private Size windowSize = new Size(800, 600);
    private boolean centered = true;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setWindowSize(Size windowSize) {
        this.windowSize = windowSize;
    }

    public Size getWindowSize() {
        return windowSize;
    }

    public void setCentered(boolean centered) {
        this.centered = centered;
    }

    public boolean isCentered() {
        return centered;
    }
}
