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
    private int[][] rCanalNoisyCoefMatrix;
    private int[][] gCanalNoisyCoefMatrix;
    private int[][] bCanalNoisyCoefMatrix;
    private final static long m = 281474976710656L;
    private final static long a = 25214903917L;
    private final static int c = 11;
    static long seed = 1;

    public DumpNoise(MyImage image) {
        this.myImage = image;
        rCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        gCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        bCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        rCanalNoisyCoefMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        gCanalNoisyCoefMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        bCanalNoisyCoefMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
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

    // метод реализующий линейно-конгруэнтный генератор случайных чисел
    public int getRand() {
        seed = (a * seed + c) % m;
        return (int) (seed % 250);
    }

    //метод который зашумляет изображение добавляя коэфициенты сгенерированные линейно-конгруэнтным генератором к интесивности пикселей
    public void linearСongruentNoiseGenerator() {
        int[] coefs = new int[myImage.getImage().getWidth() * myImage.getImage().getHeight()];
        for (int i = 0; i < myImage.getImage().getWidth() * myImage.getImage().getHeight(); i++) {
            coefs[i] = getRand();
        }
        System.out.println(coefs[1]);
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

    //метод создаёт зашумлённую картинку(делать только после метода dumpNoiseGenerator)
    public BufferedImage createNoisyImage() {
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
}
