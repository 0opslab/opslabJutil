package com.opslab.util.image;

import junit.framework.TestCase;
import com.opslab.util.TestUtil;
import org.junit.Ignore;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

/**
 * 测试图片工具类
 */
@Ignore
public class OperateImageTest extends TestCase {

    private String path = TestUtil.path + "/image/";
    private String path_temp = TestUtil.path + "/temp/";

    /**
     * 剪切图片
     *
     * @throws Exception
     */
    public void testCropImage() throws Exception {
        String file = path + "1.jpg";
        BufferedImage img = ImageIO.read(new File(file));
        System.out.println(img.getWidth() + ":" + img.getHeight());
        String readImageFormat = "jpg";
        String writeImageFormat = "jpg";
        //从左上角剪切图片
        OperateImage.cropImage(file, path_temp + "cropImage100x100-0.jpg", 0, 0, 100, 100,
                readImageFormat,
                writeImageFormat);
        //从中间剪切图片
        OperateImage.cropImage(file, path_temp + "cropImage100x100-1.jpg", 100, 100, 100, 100,
                readImageFormat,
                writeImageFormat);
    }

    /**
     * 按照倍率缩小图片
     *
     * @throws Exception
     */
    public void testReduceImageByRatio() throws Exception {
        String file = path + "1.jpg";
        //按倍率缩小图片
        for (int i = 0; i <= 50; i += 10) {
            String tempName = String.format(path_temp + "按倍率%dx%d缩小-.jpg", i, i);
            OperateImage.reduceImageByRatio(file, tempName, i, i);
        }


    }

    public void testReduceImageEqualProportion() throws Exception {
        String file = path + "1.jpg";
        BufferedImage img = ImageIO.read(new File(file));
        //长高等比缩小
        OperateImage.reduceImageEqualProportion(file, path_temp + "ReduceImageEqualx1.jpg", 1);
        OperateImage.reduceImageEqualProportion(file, path_temp + "ReduceImageEqualx2.jpg", 2);
        OperateImage.reduceImageEqualProportion(file, path_temp + "ReduceImageEqualx5.jpg", 5);
    }

    public void testEnlargementImageByRatio() throws Exception {
        String file = path + "1.jpg";
        BufferedImage img = ImageIO.read(new File(file));
        System.out.println(img.getWidth() + ":" + img.getHeight());
        //长高放大
        OperateImage.enlargementImageByRatio(file, path_temp + "enlargementImageByRatio1x1.jpg", 1, 1);
        OperateImage.enlargementImageByRatio(file, path_temp + "enlargementImageByRatio2x2.jpg", 2, 2);
    }

    public void testEnlargementImageEqualProportion() throws Exception {
        String file = path + "1.jpg";
        BufferedImage img = ImageIO.read(new File(file));
        System.out.println(img.getWidth() + ":" + img.getHeight());
        //长高等比放大
        OperateImage.enlargementImageEqualProportion(file, path_temp + "enlargementImageEqualProportion2x2.jpg", 2);
        OperateImage.enlargementImageEqualProportion(file, path_temp + "enlargementImageEqualProportion5x5.jpg", 5);
    }

    public void testResizeImage() throws Exception {
        String file = path + "1.jpg";
        BufferedImage img = ImageIO.read(new File(file));
        System.out.println(img.getWidth() + ":" + img.getHeight());
        //重置图形的边长大小
        OperateImage.resizeImage(file, path_temp + "resizeImage64x64.jpg", 64, 64);
        OperateImage.resizeImage(file, path_temp + "resizeImage32x32.jpg", 32, 32);
    }

    public void testJoinImagesHorizontal() throws Exception {
        String file1 = path + "1.jpg";
        String file2 = path + "2.jpg";
        String file3 = path + "4.jpg";
        String dstFile = path_temp + "JoinImagesHorizontal-01.jpg";
        String dstFile1 = path_temp + "JoinImagesHorizontal-02.jpg";
        String dstFile2 = path_temp + "JoinImagesHorizontal-03.jpg";
        OperateImage.joinImagesHorizontal(file1, file2, "jpg", dstFile);
        //OperateImage.joinImagesHorizontal(file1,file3,"jpg",dstFile1);
        //OperateImage.joinImagesHorizontal(file3,file1,"jpg",dstFile2);
    }

    public void testJoinImageListHorizontal() throws Exception {
        String[] files = new String[]{path + "1.jpg", path + "2.jpg", path + "3.jpg"};
        String dst = path_temp + "JoinImageListHorizontalx1.jpg";
        //拼接一组图片
        OperateImage.joinImageListHorizontal(files, "jpg", dst);
    }

    public void testJoinImagesVertical() throws Exception {
        String file1 = path + "1.jpg";
        String file2 = path + "2.jpg";
        String dstFile = path_temp + "JoinImagesVertical-01.jpg";
        //纵向拼接俩张图片
        OperateImage.joinImagesVertical(file1, file2, "jpg", dstFile);
    }

    public void testJoinImageListVertical() throws Exception {
        String[] files = new String[]{path + "1.jpg", path + "2.jpg", path + "3.jpg"};
        String dst = path_temp + "JoinImageListVerticalx1.jpg";
        //拼接一组图片
        OperateImage.joinImageListVertical(files, "jpg", dst);
    }

    public void testMergeBothImage() throws Exception {
        String file1 = path + "1.jpg";
        String file3 = path + "4.jpg";
        OperateImage.mergeBothImage(file1, file3, "jpg", 0, 0,
                path_temp + "MergeBothImage-01.jpg");
        OperateImage.mergeBothImage(file1, file3, "jpg", 30, 30,
                path_temp + "MergeBothImage-02.jpg");
    }


    public void testMergeImageList() throws Exception {
        String file1 = path + "1.jpg";
        String[][] files = new String[][]{
                new String[]{"0", "0", path + "2.jpg"},
                new String[]{"100", "100", path + "3.jpg"},
                new String[]{"0", "100", path + "4.jpg"}
        };
        String dstFile = path_temp + "MergeImageList-01.jpg";
        OperateImage.mergeImageList(file1, Arrays.asList(files), "jpg", dstFile);
    }

    public void testMergeBothImageTopleftcorner() throws Exception {

    }

    public void testMergeBothImageToprightcorner() throws Exception {

    }

    public void testMergeBothImageLeftbottom() throws Exception {

    }

    public void testMergeBothImageRightbottom() throws Exception {

    }

    public void testMergeBothImageCenter() throws Exception {

    }

    public void testMergeBothImageTopcenter() throws Exception {

    }

    public void testMergeBothImageBottomcenter() throws Exception {

    }

    public void testMergeBothImageLeftcenter() throws Exception {

    }

    public void testMergeBothImageRightcenter() throws Exception {

    }

    public void testGrayImage() throws Exception {
        String file = path + "1.jpg";
        OperateImage.grayImage(file, path_temp + "GrayImage.jpg", "jpg");
    }

    public void testAlphaWords2Image() throws Exception {
        float alpha = 0.2F;
        String font = "宋体";
        int fontStyle = Font.PLAIN;
        int fontSize = 400;
        Color color = Color.RED;
        String inputWords = "www";
        String imageFormat = "jpg";
        String file = path + "1.jpg";
        String dstFile = path_temp + "AlphaWords2Image.jpg";
        System.out.println(dstFile);
        OperateImage.alphaWords2Image(file, alpha, font, fontStyle, fontSize, color, inputWords, 0, 0, imageFormat, dstFile);
    }

    public void testAlphaImage2Image() throws Exception {

    }

    public void testDrawPoint() throws Exception {

    }

    public void testDrawPoints() throws Exception {

    }

    public void testDrawLine() throws Exception {

    }

    public void testDrawPolyline() throws Exception {

    }

    public void testDrawPolylineShowPoints() throws Exception {

    }

    public void testDrawPolygon() throws Exception {

    }

    public void testDrawAndAlphaPolygon() throws Exception {

    }


}