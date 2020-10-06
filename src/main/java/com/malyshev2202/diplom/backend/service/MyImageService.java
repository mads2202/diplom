package com.malyshev2202.diplom.backend.service;

import com.malyshev2202.diplom.backend.model.MyImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//этот класс представляет из себя служебный класс, предоставляя некоторые методы для класса
public class MyImageService {

    //метод для получения массива интенсивностей красного канала пикселей картинки
    public int[][] initCanalMatrix(int colorConst, BufferedImage bufferedImage) {
        int arr[][]=new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for (int x = 0; x < bufferedImage.getHeight(); x++) {
            for (int y = 0; y <bufferedImage.getHeight(); y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
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
    public void initAllCanalMatrix(MyImage myImage) {
        myImage.setrCanalMatrix(initCanalMatrix( MyImage.RED_COLOR,myImage.getImage()));
        myImage.setgCanalMatrix(initCanalMatrix( MyImage.GREEN_COLOR,myImage.getImage()));
        myImage.setbCanalMatrix(initCanalMatrix( MyImage.BLUE_COLOR,myImage.getImage()));

    }

    //метод для сохранения картинки на диске после обработки
    public void saveImage(BufferedImage bufferedImage, String pathTo) {
        File output = new File(pathTo, "outPutImage.jpg");
        try {
            ImageIO.write(bufferedImage, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
