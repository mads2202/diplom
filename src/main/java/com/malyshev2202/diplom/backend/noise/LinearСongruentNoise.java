package com.malyshev2202.diplom.backend.noise;

import com.malyshev2202.diplom.backend.model.MyImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LinearСongruentNoise implements Noise {
    private MyImage myImage;
    private int[][] rCanalNoisyMatrix;
    private int[][] gCanalNoisyMatrix;
    private int[][] bCanalNoisyMatrix;
    private final static long m = 281474976710656L;
    private final static long a = 25214903917L;
    private final static int c = 11;
    static long seed = 1;

    public LinearСongruentNoise(MyImage image) {
        this.myImage = image;
        rCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        gCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        bCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];

    }
    // метод реализующий линейно-конгруэнтный генератор случайных чисел
    public int getRand() {
        seed = (a * seed + c) % m;
        return (int) (seed % 100);
    }
    //метод который зашумляет изображение добавляя коэфициенты сгенерированные линейно-конгруэнтным генератором к интесивности пикселей
    public void linearСongruentNoiseGenerator() {
        int[] coefs = new int[myImage.getImage().getWidth() * myImage.getImage().getHeight()];
        for (int i = 0; i < myImage.getImage().getWidth() * myImage.getImage().getHeight(); i++) {
            coefs[i] = getRand();
        }
        int i = 0;
        for (int x = 0; x < myImage.getImage().getWidth(); x++) {
            for (int y = 0; y < myImage.getImage().getHeight(); y++) {
                //зашумляем красный канал
                if ((myImage.getrCanalMatrix()[x][y] + coefs[i]) > 255)
                    rCanalNoisyMatrix[x][y] = 255;
                else if ((myImage.getrCanalMatrix()[x][y] + coefs[i]) < 0)
                    rCanalNoisyMatrix[x][y] = 0;
                else
                    rCanalNoisyMatrix[x][y] = myImage.getrCanalMatrix()[x][y] + coefs[i];

                //зашумляем зелёный канал
                if ((myImage.getgCanalMatrix()[x][y] + coefs[i]) > 255)
                    gCanalNoisyMatrix[x][y] = 255;
                else if ((myImage.getgCanalMatrix()[x][y] + coefs[i]) < 0)
                    gCanalNoisyMatrix[x][y] = 0;
                else
                    gCanalNoisyMatrix[x][y] = myImage.getgCanalMatrix()[x][y] + coefs[i];

                //зашумляем синий канал
                if ((myImage.getbCanalMatrix()[x][y] + coefs[i]) > 254)
                    bCanalNoisyMatrix[x][y] = 254;
                else if ((myImage.getbCanalMatrix()[x][y] + coefs[i]) < 0)
                    bCanalNoisyMatrix[x][y] = 0;
                else
                    bCanalNoisyMatrix[x][y] = myImage.getbCanalMatrix()[x][y] + coefs[i];
                i++;

            }
        }

    }
    //метод востанавливает нормальную  картинку из зашумлённой(делать только после метода linearСongruentNoiseGenerator)
    public BufferedImage linearСongruentNoiseRecovery() {
        seed=1;
        int[] coefs = new int[myImage.getImage().getWidth() * myImage.getImage().getHeight()];
        for (int i = 0; i < myImage.getImage().getWidth() * myImage.getImage().getHeight(); i++) {
            coefs[i] = getRand();

        }

        int i = 0;
        BufferedImage result = new BufferedImage(myImage.getImage().getWidth(), myImage.getImage().getHeight(), myImage.getImage().getType());
        for (int x = 0; x < result.getWidth(); x++) {
            for (int y = 0; y < result.getHeight(); y++) {
                //зашумляем красный канал

                rCanalNoisyMatrix[x][y] -= coefs[i];

                //зашумляем зелёный канал

                gCanalNoisyMatrix[x][y] -= coefs[i];

                //зашумляем синий канал

                bCanalNoisyMatrix[x][y] -= coefs[i];
                i++;
                Color color = new Color(rCanalNoisyMatrix[x][y], gCanalNoisyMatrix[x][y], bCanalNoisyMatrix[x][y]);
                result.setRGB(x, y, color.getRGB());

            }
        }
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
