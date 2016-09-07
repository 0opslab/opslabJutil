package com.opslab.util.image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 图片相关的操作类
 */
public final class ImageUtil {

    /**
     * 重新设定图像的长高宽
     * @param originalImage 图像数据
     * @param width 宽
     * @param height 高
     * @return
     */
    public static BufferedImage imageResize(BufferedImage originalImage, Integer width,Integer height){
        if(width <= 0){
            width =1;
        }
        if(height <= 0){
            height =1;
        }
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage,0,0,width,height,null);
        g.dispose();
        return newImage;
    }

    /**
     * 按照给点的比例放大图像
     * 当缩减比例小于等于0时不发生任何变化
     * @param originalImage 图像数据
     * @param withdRatio 宽度缩减比例
     * @param heightRatio 高度缩减比例
     * @return 图像数据
     */
    public static BufferedImage imageMagnifyRatio(BufferedImage originalImage, Integer withdRatio,Integer heightRatio){
        if(withdRatio <= 0){
            withdRatio =1;
        }
        if(heightRatio <= 0){
            heightRatio =1;
        }
        int width = originalImage.getWidth()*withdRatio;
        int height = originalImage.getHeight()*heightRatio;
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage,0,0,width,height,null);
        g.dispose();
        return newImage;
    }
    /**
     * 按照给点的比例缩小图像
     * 当缩减比例小于等于0时不发生任何变化
     * @param originalImage 图像数据
     * @param withdRatio 宽度缩减比例
     * @param heightRatio 高度缩减比例
     * @return 图像数据
     */
    public static BufferedImage imageShrinkRatio(BufferedImage originalImage, Integer withdRatio,Integer heightRatio){
        if(withdRatio <= 0){
            withdRatio =1;
        }
        if(heightRatio <= 0){
            heightRatio =1;
        }
        int width = originalImage.getWidth()/withdRatio;
        int height = originalImage.getHeight()/heightRatio;
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage,0,0,width,height,null);
        g.dispose();
        return newImage;
    }
}
