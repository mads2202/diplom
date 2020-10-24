package com.malyshev2202.diplom.backend.noise;

import com.malyshev2202.diplom.backend.model.MyImage;
import com.malyshev2202.diplom.backend.service.BarGraphService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

// этот класс предоставляет методы которые будут зашумлять изображение добавлением к интенсивности пикселей различные коэфициенты
public class DumpNoise implements Noise {
    private MyImage myImage;
    private int[][] rCanalNoisyMatrix;
    private int[][] gCanalNoisyMatrix;
    private int[][] bCanalNoisyMatrix;


    public DumpNoise(MyImage image) {
        this.myImage = image;
        rCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        gCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        bCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];

    }

    // этот метод добавляет пользовательские коэфициенты к интенсивности пикселей (!!! возможна потеря данных)
    public void dumpNoiseGenerator(int rCoef, int gCoef, int bCoef) {
        for (int x = 0; x < myImage.getImage().getWidth(); x++) {
            for (int y = 0; y < myImage.getImage().getHeight(); y++) {
                //зашумляем красный канал
                if ((myImage.getrCanalMatrix()[x][y] + rCoef) > 254)
                    rCanalNoisyMatrix[x][y] = 254;
                else if ((myImage.getrCanalMatrix()[x][y] + rCoef) < 0)
                    rCanalNoisyMatrix[x][y] = 0;
                else
                    rCanalNoisyMatrix[x][y] = myImage.getrCanalMatrix()[x][y] + rCoef;
                //зашумляем зелёный канал
                if ((myImage.getgCanalMatrix()[x][y] + gCoef) > 254)
                    gCanalNoisyMatrix[x][y] = 254;
                else if ((myImage.getgCanalMatrix()[x][y] + gCoef) < 0)
                    gCanalNoisyMatrix[x][y] = 0;
                else
                    gCanalNoisyMatrix[x][y] = myImage.getgCanalMatrix()[x][y] + gCoef;
                //зашумляем синий канал
                if ((myImage.getbCanalMatrix()[x][y] + bCoef) > 254)
                    bCanalNoisyMatrix[x][y] = 254;
                else if ((myImage.getbCanalMatrix()[x][y] + bCoef) < 0)
                    bCanalNoisyMatrix[x][y] = 0;
                else
                    bCanalNoisyMatrix[x][y] = myImage.getbCanalMatrix()[x][y] + bCoef;
            }
        }
    }


    //метод востанавливает нормальную  картинкуиз зашумлённой(делать только после метода dumpNoiseGenerator)
    public BufferedImage dumpNoiseRecovery(int rCoef, int gCoef, int bCoef) {
        BufferedImage result = new BufferedImage(myImage.getImage().getWidth(), myImage.getImage().getHeight(), myImage.getImage().getType());
        for (int x = 0; x < result.getWidth(); x++) {
            for (int y = 0; y < result.getHeight(); y++) {
                    rCanalNoisyMatrix[x][y] -= rCoef;
                    gCanalNoisyMatrix[x][y] -= gCoef;
                    bCanalNoisyMatrix[x][y] -= bCoef;
                Color color = new Color(rCanalNoisyMatrix[x][y], gCanalNoisyMatrix[x][y], bCanalNoisyMatrix[x][y]);
                result.setRGB(x, y, color.getRGB());
            }
        }
        myImage.setImage(result);
        return result;
    }

    public MyImage getMyImage() {
        return myImage;
    }

    public void setMyImage(MyImage myImage) {
        this.myImage = myImage;
    }

    public int[][] getrCanalNoisyMatrix() {
        return rCanalNoisyMatrix;
    }

    public void setrCanalNoisyMatrix(int[][] rCanalNoisyMatrix) {
        this.rCanalNoisyMatrix = rCanalNoisyMatrix;
    }

    public int[][] getgCanalNoisyMatrix() {
        return gCanalNoisyMatrix;
    }

    public void setgCanalNoisyMatrix(int[][] gCanalNoisyMatrix) {
        this.gCanalNoisyMatrix = gCanalNoisyMatrix;
    }

    public int[][] getbCanalNoisyMatrix() {
        return bCanalNoisyMatrix;
    }

    public void setbCanalNoisyMatrix(int[][] bCanalNoisyMatrix) {
        this.bCanalNoisyMatrix = bCanalNoisyMatrix;
    }
}
