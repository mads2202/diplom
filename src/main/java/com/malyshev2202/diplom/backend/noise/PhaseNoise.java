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


    public PhaseNoise(MyImage image) {
        this.myImage = image;
        rCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        gCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];
        bCanalNoisyMatrix = new int[myImage.getImage().getWidth()][myImage.getImage().getHeight()];

    }

    public int[][] phaseNoiseGeneratorPhase(int[][] arr, double a0, double a1, double a2) {

        a1=a1/a0;
        a2=a2/a0;
        int[] x = arr2DTo1DArrInt(arr);
        System.out.println(x.length);
        double[] y = new double[x.length];
        y[0] = a2 * x[0];
        y[1] = a2 * x[1] + +a1 * x[0] + -a1 * y[0];
        y[2] = a2 * x[2] + a1 * x[1] + x[0] - a1 * y[1] - a2 * y[0];
        for (int i = 3; i < x.length; i++) {
            y[i] = a2 * x[i] + a1 * x[i - 1] + x[i - 2] - a1 * y[i - 1] - a2 * y[i - 2];
                if(y[i]>255)
                    y[i]=255;

        }
     /*   for (int i = 0; i < y.length; i++) {
            if(y[i]>255 ||y[i]<-255)
               System.out.println(i +" " +y[i]);
        }*/

        return arr1DTo2DArr(absIntArr(doubleArrToIntArr(y)));

    }

    public int[] doubleArrToIntArr(double[] arr){
        int[] newArr= new int[arr.length];
        for (int i=0;i<arr.length;i++){
            newArr[i]=(int)arr[i];
        }
        return newArr;
    }

    public int[] absIntArr(int[] arr){
        int[] newArr= new int[arr.length];
        for (int i=0;i<arr.length;i++){
            newArr[i]=Math.abs(arr[i]);
        }
        return newArr;
    }


    private int[][] arr1DTo2DArr(int[] arr) {
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
