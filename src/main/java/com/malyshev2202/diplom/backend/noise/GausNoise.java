package com.malyshev2202.diplom.backend.noise;

import com.malyshev2202.diplom.backend.model.MyImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GausNoise implements Noise {
    private MyImage myImage;
    private int[][] rCanalNoisyMatrix;
    private int[][] gCanalNoisyMatrix;
    private int[][] bCanalNoisyMatrix;
    private int[][] rCanalNoisyCoefMatrix;
    private int[][] gCanalNoisyCoefMatrix;
    private int[][] bCanalNoisyCoefMatrix;

    public GausNoise(MyImage image) {
        this.myImage = image;
        rCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        gCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        bCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        rCanalNoisyCoefMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        gCanalNoisyCoefMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        bCanalNoisyCoefMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
    }
    // метод зашумления картинки путём добавления или вычитание коэфициента до медианного значения интенсивности
    public void gauseNoiseGenerator(int redMedianIntencity, int greenMedianIntencity, int blueMedianIntencity) {
        for (int x = 0; x < myImage.getImage().getWidth(); x++) {
            for (int y = 0; y < myImage.getImage().getHeight(); y++) {
                //зашумляем красный канал
                rCanalNoisyCoefMatrix[x][y] = redMedianIntencity - myImage.getrCanalMatrix()[x][y];
                rCanalNoisyMatrix[x][y] = redMedianIntencity;


                //зашумляем зелёный канал
                gCanalNoisyCoefMatrix[x][y] = greenMedianIntencity - myImage.getgCanalMatrix()[x][y];
                gCanalNoisyMatrix[x][y] = greenMedianIntencity;

                //зашумляем синий канал
                bCanalNoisyCoefMatrix[x][y] = blueMedianIntencity - myImage.getbCanalMatrix()[x][y];
                bCanalNoisyMatrix[x][y] = blueMedianIntencity;


            }
        }
    }
    //метод востанавливает нормальную  картинку из зашумлённой(делать только после метода gausNoiseGenerator)
    public BufferedImage gausNoiseRecovery() {
        BufferedImage result = new BufferedImage(myImage.getImage().getWidth(), myImage.getImage().getHeight(), myImage.getImage().getType());
        for (int x = 0; x < result.getWidth(); x++) {
            for (int y = 0; y < result.getHeight(); y++) {
                rCanalNoisyMatrix[x][y] -= rCanalNoisyCoefMatrix[x][y];
                gCanalNoisyMatrix[x][y] -= gCanalNoisyCoefMatrix[x][y];
                bCanalNoisyMatrix[x][y] -= bCanalNoisyCoefMatrix[x][y];


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

    public int[][] getrCanalNoisyCoefMatrix() {
        return rCanalNoisyCoefMatrix;
    }

    public void setrCanalNoisyCoefMatrix(int[][] rCanalNoisyCoefMatrix) {
        this.rCanalNoisyCoefMatrix = rCanalNoisyCoefMatrix;
    }

    public int[][] getgCanalNoisyCoefMatrix() {
        return gCanalNoisyCoefMatrix;
    }

    public void setgCanalNoisyCoefMatrix(int[][] gCanalNoisyCoefMatrix) {
        this.gCanalNoisyCoefMatrix = gCanalNoisyCoefMatrix;
    }

    public int[][] getbCanalNoisyCoefMatrix() {
        return bCanalNoisyCoefMatrix;
    }

    public void setbCanalNoisyCoefMatrix(int[][] bCanalNoisyCoefMatrix) {
        this.bCanalNoisyCoefMatrix = bCanalNoisyCoefMatrix;
    }
}
