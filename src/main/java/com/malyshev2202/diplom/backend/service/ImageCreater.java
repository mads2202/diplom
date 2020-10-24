package com.malyshev2202.diplom.backend.service;

import com.malyshev2202.diplom.backend.model.MyImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageCreater {

    //метод создаёт зашумлённую картинку(делать только после метода dumpNoiseGenerator)
    public static BufferedImage createNoisyImage(MyImage myImage,int[][] rCanalNoisyMatrix, int[][] gCanalNoisyMatrix, int[][] bCanalNoisyMatrix) {
        BufferedImage result = myImage.getImage();
        for (int x = 0; x < myImage.getImage().getWidth(); x++) {
            for (int y = 0; y < myImage.getImage().getHeight(); y++) {
                Color color = new Color(rCanalNoisyMatrix[x][y], gCanalNoisyMatrix[x][y], bCanalNoisyMatrix[x][y]);
                result.setRGB(x, y, color.getRGB());
            }
        }
        myImage.setImage(result);
        return result;

    }
}
