package com.opslab.util.image;

import org.junit.Ignore;
import org.junit.Test;
import com.opslab.util.TestUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ImageIOTest {

    public static BufferedImage zoomInImage(BufferedImage originalImage, Integer times) {
        int width = originalImage.getWidth() * times;
        int height = originalImage.getHeight() * times;
        BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;

    }

    @Test
    @Ignore
    public void testImage() throws IOException {
        String path = TestUtil.path + "/image";
        String path_temp = TestUtil.path + "/temp/image";
        String file = path + "1.jpg";
        String file_temp = path_temp + "1.jpg";
        BufferedImage bufferedImage = ImageIO.read(new File(file));
        BufferedImage bufferedImage1 = zoomInImage(bufferedImage, 50);
        FileOutputStream outputStream = new FileOutputStream(file_temp);
        ImageIO.write(bufferedImage1, "jpg", outputStream);
    }
}
