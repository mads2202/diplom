package com.malyshev2202.diplom.backend.service;


import com.malyshev2202.diplom.backend.model.MyImage;

import java.util.Arrays;

//этот класс служит серисом, который будет рассчитывать кличество пикселей соответсвующих интенсивности от 0 до 255
// для того чтобы посторить на основе этих данных гистограммы
public class BarGraphService {
    private int[] redIntencityMatrix = new int[256];
    private int[] greenIntencityMatrix = new int[256];
    private int[] blueIntencityMatrix = new int[256];


    public BarGraphService(MyImage myImage) {
                fillRedIntencityMatrix(myImage);
                fillGreenIntencityMatrix(myImage);
                fillBlueIntencityMatrix(myImage);

        }


// метод для заполнения матрицы интенсивности для красного канала
    private void fillRedIntencityMatrix(MyImage image) {
        for (int i = 0; i < redIntencityMatrix.length; i++) {
            for (int y = 0; y < image.getImage().getHeight(); y++) {
                for (int x = 0; x < image.getImage().getWidth(); x++) {
                    if (image.getrCanalMatrix()[y][x] == i)
                        redIntencityMatrix[i]+=1;
                }
            }
        }
    }

    public int[] getRedIntencityMatrix() {
        return redIntencityMatrix;
    }

    public int[] getGreenIntencityMatrix() {
        return greenIntencityMatrix;
    }

    public int[] getBlueIntencityMatrix() {
        return blueIntencityMatrix;
    }
    // метод для заполнения матрицы интенсивности для зелёного канала
    private void fillGreenIntencityMatrix(MyImage image) {
        for (int i = 0; i < greenIntencityMatrix.length; i++) {
            for (int y = 0; y < image.getImage().getHeight(); y++) {
                for (int x = 0; x < image.getImage().getWidth(); x++) {
                    if (image.getgCanalMatrix()[y][x] == i)
                        greenIntencityMatrix[i] += 1;
                }
            }
        }
    }
    // метод для заполнения матрицы интенсивности для синего канала
    private void fillBlueIntencityMatrix(MyImage image) {
        for (int i = 0; i < blueIntencityMatrix.length; i++) {
            for (int y = 0; y < image.getImage().getHeight(); y++) {
                for (int x = 0; x < image.getImage().getWidth(); x++) {
                    if (image.getbCanalMatrix()[y][x] == i)
                        blueIntencityMatrix[i] += 1;
                }
            }
        }
    }
    // метод поиска медианного значения в матрице интенссивности
    public int getMedian(int[] arr){
        int[] sortedArr=arr.clone();
        Arrays.sort(sortedArr);
        double median;
        int medianIntencity=0;
        if (sortedArr.length % 2 == 0)
            median = ((double)sortedArr[sortedArr.length/2] + (double)sortedArr[sortedArr.length/2 - 1])/2;
        else
            median = (double) sortedArr[sortedArr.length/2];
        double dif=Math.abs(arr[0]-median);
        for (int i=0; i<arr.length;i++){
            if((Math.abs(arr[i]-median))<dif){
                dif=Math.abs(arr[i]-median);
                medianIntencity=i;}

        }
        return medianIntencity;
    }


}
