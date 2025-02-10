package de.dragonrex.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
    public static Image readImage(String path, int width, int height) {
        try {
            return ImageIO.read(new File(path)).getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
