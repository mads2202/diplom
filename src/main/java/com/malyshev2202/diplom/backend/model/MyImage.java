package com.malyshev2202.diplom.backend.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Этот класс является моделью для изображения
public class MyImage extends BufferedImage {
    private static BufferedImage image;

    private int[][] rCanalMatrix = new int[image.getWidth()][image.getHeight()];
    private int[][] gCanalMatrix = new int[image.getWidth()][image.getHeight()];
    private int[][] bCanalMatrix = new int[image.getWidth()][image.getHeight()];
    public static final int RED_COLOR = 0;
    public static final int GREEN_COLOR = 1;
    public static final int BLUE_COLOR = 2;
    public static final int ALL_COLORS=3;

    private MyImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    //метод для создания экземпляра класса
    public static MyImage createImage(File initialFile) {

        try {
            image = ImageIO.read(initialFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MyImage myImage = new MyImage(image.getWidth(), image.getHeight(), image.getType());
        return myImage;
    }

    public MyImage(BufferedImage image) {
        super(image.getWidth(), image.getHeight(), image.getType());
        this.image=image;
    }

    //метод для получения массива интенсивностей красного канала пикселей картинки
    public int[][] initCanalMatrix(int[][] arr, int colorConst) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                switch (colorConst) {
                    case 0:
                        arr[x][y] = color.getRed();
                        break;
                    case 1:
                        arr[x][y] = color.getGreen();
                        break;
                    case 2:
                        arr[x][y] = color.getBlue();
                        break;
                }
            }
        }
        return arr;
    }

    //инициалировать все матрицы сразу
    public void initAllCanalMatrix() {
        initCanalMatrix(rCanalMatrix, RED_COLOR);
        initCanalMatrix(gCanalMatrix, GREEN_COLOR);
        initCanalMatrix(bCanalMatrix, BLUE_COLOR);
    }
    //метод для сохранения картинки на диске после обработки
    public void saveImage(MyImage image, String pathTo){
        File output = new File(pathTo,"outPutImage.jpg");
        try {
            ImageIO.write(image, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[][] getrCanalMatrix() {
        return rCanalMatrix;
    }

    public int[][] getgCanalMatrix() {
        return gCanalMatrix;
    }

    public int[][] getbCanalMatrix() {
        return bCanalMatrix;
    }

    public static BufferedImage getImage() {
        return image;
    }

}