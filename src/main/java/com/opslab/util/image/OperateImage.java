package com.opslab.util.image;

import com.opslab.util.FileUtil;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * 图片相关的操作
 * @author adam.胡升阳
 */
public final class OperateImage {
    // 图形交换格式
    public static String IMAGE_TYPE_GIF = "gif";
    // 联合照片专家组
    public static String IMAGE_TYPE_JPG = "jpg";
    // 联合照片专家组
    public static String IMAGE_TYPE_JPEG = "jpeg";
    // 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
    public static String IMAGE_TYPE_BMP = "bmp";
    // 可移植网络图形
    public static String IMAGE_TYPE_PNG = "png";
    // Photoshop的专用格式Photoshop
    public static String IMAGE_TYPE_PSD = "psd";
    /**
     * 对图片裁剪，并把裁剪新图片保存
     *
     * @param srcPath          读取源图片路径
     * @param toPath           写入图片路径
     * @param x                剪切起始点x坐标
     * @param y                剪切起始点y坐标
     * @param width            剪切宽度
     * @param height           剪切高度
     * @param readImageFormat  读取图片格式
     * @param writeImageFormat 写入图片格式
     * @throws IOException
     */
    public static void cropImage(String srcPath, String toPath,
                          int x, int y, int width, int height,
                          String readImageFormat, String writeImageFormat) throws IOException {
        try(
            FileInputStream fis =new FileInputStream(srcPath);
            ImageInputStream iis = ImageIO.createImageInputStream(fis)
        ) {
            Iterator it = ImageIO.getImageReadersByFormatName(readImageFormat);
            ImageReader reader = (ImageReader) it.next();
            //获取图片流
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            //定义一个矩形
            Rectangle rect = new Rectangle(x, y, width, height);
            //提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            //保存新图片
            ImageIO.write(bi, writeImageFormat, new File(toPath));
        }catch (IOException e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 按倍率缩小图片
     *
     * @param srcImagePath 读取图片路径
     * @param toImagePath  写入图片路径
     * @param widthRatio   宽度缩小比例
     * @param heightRatio  高度缩小比例
     * @throws IOException
     */
    public static void reduceImageByRatio(String srcImagePath, String toImagePath, int widthRatio, int heightRatio) throws IOException {
        File file = new File(srcImagePath);
        try(FileOutputStream out = new FileOutputStream(toImagePath)) {
            //读入文件

            String prefix= FileUtil.suffix(file);
            // 构造Image对象
            BufferedImage srcBuffer = ImageIO.read(file);
            // 按比例缩减图像
            BufferedImage imageBuffer = ImageUtil.imageShrinkRatio(srcBuffer, widthRatio, heightRatio);
            ImageIO.write(imageBuffer,prefix,out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 长高等比例缩小图片
     *
     * @param srcImagePath 读取图片路径
     * @param toImagePath  写入图片路径
     * @param ratio        缩小比例
     * @throws IOException
     */
    public static void reduceImageEqualProportion(String srcImagePath, String toImagePath, int ratio) throws IOException {
        File file = new File(srcImagePath);
        try(FileOutputStream out = new FileOutputStream(toImagePath)) {
            //读入文件
            String prefix= FileUtil.suffix(file);
            // 构造Image对象
            BufferedImage srcBuffer = ImageIO.read(file);
            // 按比例缩减图像
            BufferedImage imageBuffer = ImageUtil.imageShrinkRatio(srcBuffer, ratio, ratio);
            ImageIO.write(imageBuffer,prefix,out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按倍率放大图片
     *
     * @param srcImagePath 读取图形路径
     * @param toImagePath  写入入行路径
     * @param widthRatio   宽度放大比例
     * @param heightRatio  高度放大比例
     * @throws IOException
     */
    public static void enlargementImageByRatio(String srcImagePath, String toImagePath, int widthRatio, int heightRatio) throws IOException {
        File file = new File(srcImagePath);
        try(FileOutputStream out = new FileOutputStream(toImagePath)) {
            //读入文件
            String prefix= FileUtil.suffix(file);
            // 构造Image对象
            BufferedImage srcBuffer = ImageIO.read(file);
            // 按比例缩减图像
            BufferedImage imageBuffer = ImageUtil.imageMagnifyRatio(srcBuffer, widthRatio, heightRatio);
            ImageIO.write(imageBuffer, prefix, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 长高等比例放大图片
     *
     * @param srcImagePath 读取图形路径
     * @param toImagePath  写入入行路径
     * @param ratio        放大比例
     * @throws IOException
     */
    public static void enlargementImageEqualProportion(String srcImagePath, String toImagePath, int ratio) throws IOException {
        File file = new File(srcImagePath);
        try(FileOutputStream out = new FileOutputStream(toImagePath)) {
            //读入文件
            String prefix= FileUtil.suffix(file);
            // 构造Image对象
            BufferedImage srcBuffer = ImageIO.read(file);
            // 按比例缩减图像
            BufferedImage imageBuffer = ImageUtil.imageMagnifyRatio(srcBuffer, ratio, ratio);
            ImageIO.write(imageBuffer, prefix, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重置图形的边长大小
     *
     * @param srcImagePath 原图像
     * @param toImagePath 新生产的图像
     * @param width 图像宽度
     * @param height 图像高度
     * @throws IOException
     */
    public static void resizeImage(String srcImagePath, String toImagePath, int width, int height) throws IOException {
        File file = new File(srcImagePath);
        try(FileOutputStream out = new FileOutputStream(toImagePath)) {
            //读入文件
            String prefix= FileUtil.suffix(file);
            // 构造Image对象
            BufferedImage srcBuffer = ImageIO.read(file);
            // 按比例缩减图像
            BufferedImage imageBuffer = ImageUtil.imageResize(srcBuffer, width, height);
            ImageIO.write(imageBuffer, prefix, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 横向拼接图片（两张）
     *
     * @param firstSrcImagePath  第一张图片的路径
     * @param secondSrcImagePath 第二张图片的路径
     * @param imageFormat        拼接生成图片的格式
     * @param toPath             拼接生成图片的路径
     *                           要拼接的俩张图片尺寸要一致
     */
    public static void joinImagesHorizontal(String firstSrcImagePath, String secondSrcImagePath, String imageFormat, String toPath) {
        try {
            //读取第一张图片
            File fileOne = new File(firstSrcImagePath);
            BufferedImage imageOne = ImageIO.read(fileOne);
            int width = imageOne.getWidth();//图片宽度
            int height = imageOne.getHeight();//图片高度
            //从图片中读取RGB
            int[] imageArrayOne = new int[width * height];
            imageArrayOne = imageOne.getRGB(0, 0, width, height, imageArrayOne, 0, width);

            //对第二张图片做相同的处理
            File fileTwo = new File(secondSrcImagePath);
            BufferedImage imageTwo = ImageIO.read(fileTwo);
            int width2 = imageTwo.getWidth();
            int height2 = imageTwo.getHeight();
            int[] ImageArrayTwo = new int[width2 * height2];
            ImageArrayTwo = imageTwo.getRGB(0, 0, width, height, ImageArrayTwo, 0, width);
            //ImageArrayTwo  =  imageTwo.getRGB(0,0,width2,height2,ImageArrayTwo,0,width2);

            //生成新图片
            //int height3 = (height>height2 || height==height2)?height:height2;
            BufferedImage imageNew = new BufferedImage(width * 2, height, BufferedImage.TYPE_INT_RGB);
            //BufferedImage  imageNew  =  new  BufferedImage(width+width2,height3,BufferedImage.TYPE_INT_RGB);
            imageNew.setRGB(0, 0, width, height, imageArrayOne, 0, width);//设置左半部分的RGB
            imageNew.setRGB(width, 0, width, height, ImageArrayTwo, 0, width);//设置右半部分的RGB
            //imageNew.setRGB(width,0,width2,height2,ImageArrayTwo,0,width2);//设置右半部分的RGB

            File outFile = new File(toPath);
            ImageIO.write(imageNew, imageFormat, outFile);//写图片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 横向拼接一组（多张）图像
     *
     * @param pics    将要拼接的图像
     * @param type    图像写入格式
     * @param dst_pic 图像写入路径
     * @return
     */
    public static boolean joinImageListHorizontal(String[] pics, String type, String dst_pic) {
        try {
            int len = pics.length;
            if (len < 1) {
                System.out.println("pics len < 1");
                return false;
            }
            File[] src = new File[len];
            BufferedImage[] images = new BufferedImage[len];
            int[][] imageArrays = new int[len][];
            for (int i = 0; i < len; i++) {
                src[i] = new File(pics[i]);
                images[i] = ImageIO.read(src[i]);
                int width = images[i].getWidth();
                int height = images[i].getHeight();
                imageArrays[i] = new int[width * height];// 从图片中读取RGB
                imageArrays[i] = images[i].getRGB(0, 0, width, height, imageArrays[i], 0, width);
            }

            int dst_width = 0;
            int dst_height = images[0].getHeight();
            for (int i = 0; i < images.length; i++) {
                dst_height = dst_height > images[i].getHeight() ? dst_height : images[i].getHeight();
                dst_width += images[i].getWidth();
            }
            //System.out.println(dst_width);
            //System.out.println(dst_height);
            if (dst_height < 1) {
                System.out.println("dst_height < 1");
                return false;
            }
            /*
             * 生成新图片
             */
            BufferedImage ImageNew = new BufferedImage(dst_width, dst_height, BufferedImage.TYPE_INT_RGB);
            int width_i = 0;
            for (int i = 0; i < images.length; i++) {
                ImageNew.setRGB(width_i, 0, images[i].getWidth(), dst_height, imageArrays[i], 0, images[i].getWidth());
                width_i += images[i].getWidth();
            }
            File outFile = new File(dst_pic);
            ImageIO.write(ImageNew, type, outFile);// 写图片
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 纵向拼接图片（两张）
     *
     * @param firstSrcImagePath  读取的第一张图片
     * @param secondSrcImagePath 读取的第二张图片
     * @param imageFormat        图片写入格式
     * @param toPath             图片写入路径
     */
    public static void joinImagesVertical(String firstSrcImagePath, String secondSrcImagePath, String imageFormat, String toPath) {
        try {
            //读取第一张图片
            File fileOne = new File(firstSrcImagePath);
            BufferedImage imageOne = ImageIO.read(fileOne);
            int width = imageOne.getWidth();//图片宽度
            int height = imageOne.getHeight();//图片高度
            //从图片中读取RGB
            int[] imageArrayOne = new int[width * height];
            imageArrayOne = imageOne.getRGB(0, 0, width, height, imageArrayOne, 0, width);

            //对第二张图片做相同的处理
            File fileTwo = new File(secondSrcImagePath);
            BufferedImage imageTwo = ImageIO.read(fileTwo);
            int width2 = imageTwo.getWidth();
            int height2 = imageTwo.getHeight();
            int[] ImageArrayTwo = new int[width2 * height2];
            ImageArrayTwo = imageTwo.getRGB(0, 0, width, height, ImageArrayTwo, 0, width);
            //ImageArrayTwo  =  imageTwo.getRGB(0,0,width2,height2,ImageArrayTwo,0,width2);

            //生成新图片
            //int width3 = (width>width2 || width==width2)?width:width2;
            BufferedImage imageNew = new BufferedImage(width, height * 2, BufferedImage.TYPE_INT_RGB);
            //BufferedImage  imageNew  =  new  BufferedImage(width3,height+height2,BufferedImage.TYPE_INT_RGB);
            imageNew.setRGB(0, 0, width, height, imageArrayOne, 0, width);//设置上半部分的RGB
            imageNew.setRGB(0, height, width, height, ImageArrayTwo, 0, width);//设置下半部分的RGB
            //imageNew.setRGB(0,height,width2,height2,ImageArrayTwo,0,width2);//设置下半部分的RGB

            File outFile = new File(toPath);
            ImageIO.write(imageNew, imageFormat, outFile);//写图片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 纵向拼接一组（多张）图像
     *
     * @param pics    将要拼接的图像数组
     * @param type    写入图像类型
     * @param dst_pic 写入图像路径
     * @return
     */
    public static boolean joinImageListVertical(String[] pics, String type, String dst_pic) {
        try {
            int len = pics.length;
            if (len < 1) {
                System.out.println("pics len < 1");
                return false;
            }
            File[] src = new File[len];
            BufferedImage[] images = new BufferedImage[len];
            int[][] imageArrays = new int[len][];
            for (int i = 0; i < len; i++) {
                //System.out.println(i);
                src[i] = new File(pics[i]);
                images[i] = ImageIO.read(src[i]);
                int width = images[i].getWidth();
                int height = images[i].getHeight();
                imageArrays[i] = new int[width * height];// 从图片中读取RGB
                imageArrays[i] = images[i].getRGB(0, 0, width, height, imageArrays[i], 0, width);
            }

            int dst_height = 0;
            int dst_width = images[0].getWidth();
            for (int i = 0; i < images.length; i++) {
                dst_width = dst_width > images[i].getWidth() ? dst_width : images[i].getWidth();
                dst_height += images[i].getHeight();
            }
            //System.out.println(dst_width);
            //System.out.println(dst_height);
            if (dst_height < 1) {
                System.out.println("dst_height < 1");
                return false;
            }
            /*
             * 生成新图片
             */
            BufferedImage ImageNew = new BufferedImage(dst_width, dst_height, BufferedImage.TYPE_INT_RGB);
            int height_i = 0;
            for (int i = 0; i < images.length; i++) {
                ImageNew.setRGB(0, height_i, dst_width, images[i].getHeight(), imageArrays[i], 0, dst_width);
                height_i += images[i].getHeight();
            }
            File outFile = new File(dst_pic);
            ImageIO.write(ImageNew, type, outFile);// 写图片
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 合并图片(按指定初始x、y坐标将附加图片贴到底图之上)
     *
     * @param negativeImagePath 背景图片路径
     * @param additionImagePath 附加图片路径
     * @param x                 附加图片的起始点x坐标
     * @param y                 附加图片的起始点y坐标
     * @param toPath            图片写入路径
     * @throws IOException
     */
    public static void mergeBothImage(String negativeImagePath, String additionImagePath,String iamgeFromat, int x, int y, String toPath) throws IOException {
        InputStream is = null;
        InputStream is2 = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(negativeImagePath);
            is2 = new FileInputStream(additionImagePath);
            BufferedImage image = ImageIO.read(is);
            BufferedImage image2 = ImageIO.read(is2);
            Graphics g = image.getGraphics();
            g.drawImage(image2, x, y, null);
            os = new FileOutputStream(toPath);
            ImageIO.write(image, iamgeFromat, os);//写图片
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (is2 != null) {
                is2.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * 将一组图片一次性附加合并到底图上
     *
     * @param negativeImagePath 源图像（底图）路径
     * @param additionImageList 附加图像信息列表
     * @param imageFormat       图像写入格式
     * @param toPath            图像写入路径
     * @throws IOException
     */
    public static void mergeImageList(String negativeImagePath, List additionImageList, String imageFormat, String toPath) throws IOException {
        InputStream is = null;
        InputStream is2 = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(negativeImagePath);
            BufferedImage image = ImageIO.read(is);
            //Graphics g=image.getGraphics();
            Graphics2D g = image.createGraphics();
            BufferedImage image2 = null;
            if (additionImageList != null) {
                for (int i = 0; i < additionImageList.size(); i++) {
                    //解析附加图片信息：x坐标、 y坐标、 additionImagePath附加图片路径
                    //图片信息存储在一个数组中
                    String[] additionImageInfo = (String[]) additionImageList.get(i);
                    int x = Integer.parseInt(additionImageInfo[0]);
                    int y = Integer.parseInt(additionImageInfo[1]);
                    String additionImagePath = additionImageInfo[2];
                    //读取文件输入流，并合并图片
                    is2 = new FileInputStream(additionImagePath);
                    //System.out.println(x+"  :  "+y+"  :  "+additionImagePath);
                    image2 = ImageIO.read(is2);
                    g.drawImage(image2, x, y, null);
                }
            }
            os = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, os);//写图片
            //JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
            //enc.encode(image);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (is2 != null) {
                is2.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }












    /**
     * 图片灰化操作
     *
     * @param srcImage    读取图片路径
     * @param toPath      写入灰化后的图片路径
     * @param imageFormat 图片写入格式
     */
    public static void grayImage(String srcImage, String toPath, String imageFormat) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImage));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, imageFormat, new File(toPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在源图片上设置水印文字
     *
     * @param srcImagePath 源图片路径
     * @param alpha        透明度（0<alpha<1）
     * @param font         字体（例如：宋体）
     * @param fontStyle    字体格式(例如：普通样式--Font.PLAIN、粗体--Font.BOLD )
     * @param fontSize     字体大小
     * @param color        字体颜色(例如：黑色--Color.BLACK)
     * @param inputWords   输入显示在图片上的文字
     * @param x            文字显示起始的x坐标
     * @param y            文字显示起始的y坐标
     * @param imageFormat  写入图片格式（png/jpg等）
     * @param toPath       写入图片路径
     * @throws IOException
     */
    public static void alphaWords2Image(String srcImagePath, float alpha,
                                 String font, int fontStyle, int fontSize, Color color,
                                 String inputWords, int x, int y, String imageFormat, String toPath) throws IOException {
        FileOutputStream fos = null;
        try {
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            //创建java2D对象
            Graphics2D g2d = image.createGraphics();
            //用源图像填充背景
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null, null);
            //设置透明度
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(ac);
            //设置文字字体名称、样式、大小
            g2d.setFont(new Font(font, fontStyle, fontSize));
            g2d.setColor(color);//设置字体颜色
            g2d.drawString(inputWords, x, y); //输入水印文字及其起始x、y坐标
            g2d.dispose();
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 在源图像上设置图片水印
     * ---- 当alpha==1时文字不透明（和在图片上直接输入文字效果一样）
     *
     * @param srcImagePath    源图片路径
     * @param appendImagePath 水印图片路径
     * @param alpha           透明度
     * @param x               水印图片的起始x坐标
     * @param y               水印图片的起始y坐标
     * @param width           水印图片的宽度
     * @param height          水印图片的高度
     * @param imageFormat     图像写入图片格式
     * @param toPath          图像写入路径
     * @throws IOException
     */
    public static void alphaImage2Image(String srcImagePath, String appendImagePath,
                                 float alpha, int x, int y, int width, int height,
                                 String imageFormat, String toPath) throws IOException {
        FileOutputStream fos = null;
        try {
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            //创建java2D对象
            Graphics2D g2d = image.createGraphics();
            //用源图像填充背景
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null, null);
            //设置透明度
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(ac);
            //设置水印图片的起始x/y坐标、宽度、高度
            BufferedImage appendImage = ImageIO.read(new File(appendImagePath));
            g2d.drawImage(appendImage, x, y, width, height, null, null);
            g2d.dispose();
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 画单点 ---- 实际上是画一个填充颜色的圆
     * ---- 以指定点坐标为中心画一个小半径的圆形，并填充其颜色来充当点
     *
     * @param srcImagePath 源图片颜色
     * @param x            点的x坐标
     * @param y            点的y坐标
     * @param width        填充的宽度
     * @param height       填充的高度
     * @param ovalColor    填充颜色
     * @param imageFormat  写入图片格式
     * @param toPath       写入路径
     * @throws IOException
     */
    public static void drawPoint(String srcImagePath, int x, int y, int width, int height, Color ovalColor, String imageFormat, String toPath) throws IOException {
        FileOutputStream fos = null;
        try {
            //获取源图片
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            //根据xy点坐标绘制连接线
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(ovalColor);
            //填充一个椭圆形
            g2d.fillOval(x, y, width, height);
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 画一组（多个）点---- 实际上是画一组（多个）填充颜色的圆
     * ---- 以指定点坐标为中心画一个小半径的圆形，并填充其颜色来充当点
     *
     * @param srcImagePath 原图片路径
     * @param pointList    点列表
     * @param width        宽度
     * @param height       高度
     * @param ovalColor    填充颜色
     * @param imageFormat  写入图片颜色
     * @param toPath       写入路径
     * @throws IOException
     */
    public static void drawPoints(String srcImagePath, List pointList, int width, int height, Color ovalColor, String imageFormat, String toPath) throws IOException {
        FileOutputStream fos = null;
        try {
            //获取源图片
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            //根据xy点坐标绘制连接线
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(ovalColor);
            //填充一个椭圆形
            if (pointList != null) {
                for (int i = 0; i < pointList.size(); i++) {
                    Point point = (Point) pointList.get(i);
                    int x = (int) point.getX();
                    int y = (int) point.getY();
                    g2d.fillOval(x, y, width, height);
                }
            }
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 画线段
     *
     * @param srcImagePath 源图片路径
     * @param x1           第一个点x坐标
     * @param y1           第一个点y坐标
     * @param x2           第二个点x坐标
     * @param y2           第二个点y坐标
     * @param lineColor    线条颜色
     * @param toPath       图像写入路径
     * @param imageFormat  图像写入格式
     * @throws IOException
     */
    public static void drawLine(String srcImagePath, int x1, int y1, int x2, int y2, Color lineColor, String toPath, String imageFormat) throws IOException {
        FileOutputStream fos = null;
        try {
            //获取源图片
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            //根据xy点坐标绘制连接线
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(lineColor);
            g2d.drawLine(x1, y1, x2, y2);
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 画折线 / 线段
     * ---- 2个点即画线段，多个点画折线
     *
     * @param srcImagePath 源图片路径
     * @param xPoints      x坐标数组
     * @param yPoints      y坐标数组
     * @param nPoints      点的数量
     * @param lineColor    线条颜色
     * @param toPath       图像写入路径
     * @param imageFormat  图片写入格式
     * @throws IOException
     */
    public static void drawPolyline(String srcImagePath, int[] xPoints, int[] yPoints, int nPoints, Color lineColor, String toPath, String imageFormat) throws IOException {
        FileOutputStream fos = null;
        try {
            //获取源图片
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            //根据xy点坐标绘制连接线
            Graphics2D g2d = image.createGraphics();
            //设置线条颜色
            g2d.setColor(lineColor);
            g2d.drawPolyline(xPoints, yPoints, nPoints);
            //图像写出路径
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 绘制折线，并突出显示转折点
     *
     * @param srcImagePath 源图片路径
     * @param xPoints      x坐标数组
     * @param yPoints      y坐标数组
     * @param nPoints      点的数量
     * @param lineColor    连线颜色
     * @param width        点的宽度
     * @param height       点的高度
     * @param ovalColor    点的填充颜色
     * @param toPath       图像写入路径
     * @param imageFormat  图像写入格式
     * @throws IOException
     */
    public static void drawPolylineShowPoints(String srcImagePath, int[] xPoints, int[] yPoints, int nPoints, Color lineColor, int width, int height, Color ovalColor, String toPath, String imageFormat) throws IOException {
        FileOutputStream fos = null;
        try {
            //获取源图片
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            //根据xy点坐标绘制连接线
            Graphics2D g2d = image.createGraphics();
            //设置线条颜色
            g2d.setColor(lineColor);
            //画线条
            g2d.drawPolyline(xPoints, yPoints, nPoints);
            //设置圆点颜色
            g2d.setColor(ovalColor);
            //画圆点
            if (xPoints != null) {
                for (int i = 0; i < xPoints.length; i++) {
                    int x = xPoints[i];
                    int y = yPoints[i];
                    g2d.fillOval(x, y, width, height);
                }
            }
            //图像写出路径
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }


    /**
     * 绘制一个由 x 和 y 坐标数组定义的闭合多边形
     *
     * @param srcImagePath 源图片路径
     * @param xPoints      x坐标数组
     * @param yPoints      y坐标数组
     * @param nPoints      坐标点的个数
     * @param polygonColor 线条颜色
     * @param imageFormat  图像写入格式
     * @param toPath       图像写入路径
     * @throws IOException
     */
    public static void drawPolygon(String srcImagePath, int[] xPoints, int[] yPoints, int nPoints, Color polygonColor, String imageFormat, String toPath) throws IOException {
        FileOutputStream fos = null;
        try {
            //获取图片
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            //根据xy点坐标绘制闭合多边形
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(polygonColor);
            g2d.drawPolygon(xPoints, yPoints, nPoints);
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, fos);
            g2d.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 绘制并填充多边形
     *
     * @param srcImagePath 源图像路径
     * @param xPoints      x坐标数组
     * @param yPoints      y坐标数组
     * @param nPoints      坐标点个数
     * @param polygonColor 多边形填充颜色
     * @param alpha        多边形部分透明度
     * @param imageFormat  写入图形格式
     * @param toPath       写入图形路径
     * @throws IOException
     */
    public static void drawAndAlphaPolygon(String srcImagePath, int[] xPoints, int[] yPoints, int nPoints, Color polygonColor, float alpha, String imageFormat, String toPath) throws IOException {
        FileOutputStream fos = null;
        try {
            //获取图片
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            //根据xy点坐标绘制闭合多边形
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(polygonColor);
            //设置透明度
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(ac);
            g2d.fillPolygon(xPoints, yPoints, nPoints);
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, fos);
            g2d.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

}
