package de.dragonrex.frame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

public class EngineFrame extends Frame {
    FrameConfig config;

    public EngineFrame() {
        this.config = new FrameConfig();
    }

    public EngineFrame(Consumer<FrameConfig> config) {
        this.config = new FrameConfig();
        config.accept(this.config);
    }

    public EngineFrame build() {
        if (this.config.isFullScreen()) {
            this.config.setCentered(false);
            this.setUndecorated(true);
            final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
            final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
            this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        } else {
            this.setTitle(this.config.getTitle());
            this.setPreferredSize(
                    new Dimension(
                            (int) this.config.getWindowSize().getWidth(),
                            (int) this.config.getWindowSize().getHeight()
                    )
            );
        }
        this.addWindowListener(new WindowAdapter() {
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
        this.pack();
        if(config.isCentered())
            this.setLocationRelativeTo(null);
        return this;
    }

    public void start() {
        this.setVisible(true);
    }
}
