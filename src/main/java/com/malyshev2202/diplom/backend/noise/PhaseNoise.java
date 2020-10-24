package com.malyshev2202.diplom.backend.noise;
//todo: собрать картинку 1) добавив 255 посмотреть что будет
// 2) взять по модулю
// 3) другую фазу построить примени новые коэфициенты 2 раза для 2 троек
// 4) востановить картинку.  Возьми полученную картинку и примени к ней методы из phaseNoise  с теми коэфициентами которые скинули потом.
// 5) зашумить согнуть фазу разогнуть фазу расшумить.
import com.malyshev2202.diplom.backend.model.MyImage;

import java.util.ArrayList;
import java.util.Arrays;

public class PhaseNoise implements Noise {
    private MyImage myImage;
    private int[][] rCanalNoisyMatrix;
    private int[][] gCanalNoisyMatrix;
    private int[][] bCanalNoisyMatrix;
    private static final double a01 = 128/128;
    private static final double a11 = -128/128;
    private static final double a21 = 38/128;
    private static final double a02 = 128/128;
    private static final double a12 = 79/128;
    private static final double a22 = -32/128;


    public PhaseNoise(MyImage image) {
        this.myImage = image;
        rCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        gCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        bCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];

    }

    private double[] arr2DTo1DArr(double[][] arr) {
        ArrayList<Double> list = new ArrayList<Double>();
        for (int x = 0; x < myImage.getImage().getWidth(); x++) {
            for (int y = 0; y < myImage.getImage().getHeight(); y++) {
                list.add(arr[x][y]);
            }
        }
        double[] newArr = new double[list.size()];
        for (int i = 0; i < list.size(); i++)
            newArr[i] = list.get(i);

        return newArr;
    }

    private int[] arr2DTo1DArrInt(int[][] arr) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int x = 0; x < myImage.getImage().getWidth(); x++) {
            for (int y = 0; y < myImage.getImage().getHeight(); y++) {
                list.add(arr[x][y]);
            }
        }
        int[] newArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            newArr[i] = list.get(i);

        return newArr;
    }

    public double[][] phaseNoiseGeneratorPhase1(int[][] arr) {
        int[] x = arr2DTo1DArrInt(arr);
        double[] y = new double[x.length];
        y[0] = a21 * x[0];
        y[1] = a21 * x[1] + +a11 * x[0] + -a11 * y[0];
        y[2] = a21 * x[2] + a11 * x[1] + x[0] - a11 * y[1] - a21 * y[0];
        for (int i = 3; i < x.length; i++) {
            y[i] = a21 * x[i] + a11 * x[i - 1] + x[i - 2] - a11 * y[i - 1] - a21 * y[i - 2];
        }

return  arr1DTo2DArr(y);
    }

    public void phaseNoiseGeneratorPhase2(double[][] arr) {
        double[] x = arr2DTo1DArr(arr);
        double[] y = new double[x.length];
        y[0] = a22 * x[0];
        y[1] = a22 * x[1] + a12 * x[0] - a12 * y[0];
        y[2] = a22 * x[2] + a12 * x[1] + x[0] - a12 * y[1] + a22 * y[0];
        for (int i = 3; i < x.length; i++) {
            y[i] = a22 * x[i] + a12 * x[i - 1] + x[i - 2] - a12 * y[i - 1] + a22 * y[i - 2];
        }
        System.out.println(Arrays.toString(y));

    }

    private double[][] arr1DTo2DArr(double[] arr) {
        double[][] newArr = new double[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        int i = 0;

        for (int x = 0; x < myImage.getImage().getWidth(); x++) {
            for (int y = 0; y < myImage.getImage().getHeight(); y++) {
                newArr[x][y] = arr[i];
                i++;

            }
        }
        return newArr;
    }

}
