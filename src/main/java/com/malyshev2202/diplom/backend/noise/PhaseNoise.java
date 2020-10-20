package com.malyshev2202.diplom.backend.noise;

import com.malyshev2202.diplom.backend.model.MyImage;

import java.util.ArrayList;
import java.util.Arrays;

public class PhaseNoise implements Noise {
    private MyImage myImage;
    private int[][] rCanalNoisyMatrix;
    private int[][] gCanalNoisyMatrix;
    private int[][] bCanalNoisyMatrix;
    private static final int a01 = 128;
    private static final int a11 = -128;
    private static final int a21 = 38;
    private static final int a02 = 128;
    private static final int a12 = 79;
    private static final int a22 = -32;


    public PhaseNoise(MyImage image) {
        this.myImage = image;
        rCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        gCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        bCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];

    }

    private int[] arr2DTo1DArr(int[][] arr) {
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

    public void phaseNoiseGenerator(int[][] arr) {
        int[] x = arr2DTo1DArr(arr);
        int[] y = new int[x.length];
        y[0] = a21 * x[0] + a22 * x[0];
        y[1] = a21 * x[1] + a22 * x[1] + a11 * x[0] + a12 * x[0] - a11 * y[0] - a12 * y[0];
        y[2] = a21 * x[2] + a22 * x[2] + a11 * x[1] + a12 * x[1] + x[0] - a11 * y[1] - a12 * y[1] - a21 * y[0] + a22 * y[0];
        for(int i=3;i<x.length;i++){
            y[i] = a21 * x[i] + a22 * x[i] + a11 * x[i-1] + a12 * x[i-1] + x[i-2] - a11 * y[i-1] - a12 * y[i-1] - a21 * y[i-2] + a22 * y[i-2];
        }
        System.out.println(Arrays.toString(y));

    }

    private int[][] Arr1DTo2DArr(int[] arr) {
        int[][] newArr = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
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
