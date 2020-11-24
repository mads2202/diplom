package com.malyshev2202.diplom.backend.service;

import com.malyshev2202.diplom.backend.model.MyImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImagesComparatorService {
    private BufferedImage startImage;
    private BufferedImage resultImage;
    private MyImageService imageService;
    private int[][] rDifMatrix;
    private int[][] gDifMatrix;
    private int[][] bDifMatrix;

    public ImagesComparatorService(File startImage, File resultImage) {
        try {
            this.startImage = ImageIO.read(startImage);
            this.resultImage = ImageIO.read(resultImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.imageService = new MyImageService();
        this.rDifMatrix = new int[this.startImage.getWidth()][this.startImage.getHeight()];
        this.gDifMatrix = new int[this.startImage.getWidth()][this.startImage.getHeight()];
        this.bDifMatrix = new int[this.startImage.getWidth()][this.startImage.getHeight()];
    }



    public int[][] compareCanalMatrix(int color) {
        int[][] startImageMatrix;
        int[][] resultImageMatrix;
        switch (color) {
            case MyImage.RED_COLOR:
                startImageMatrix = imageService.initCanalMatrix(MyImage.RED_COLOR, startImage);
                resultImageMatrix = imageService.initCanalMatrix(MyImage.RED_COLOR, resultImage);
                for (int x = 0; x < startImage.getWidth(); x++) {
                    for (int y = 0; y < startImage.getHeight(); y++) {
                        rDifMatrix[x][y] = startImageMatrix[x][y] - resultImageMatrix[x][y];
                    }
                }
                return rDifMatrix;
            case MyImage.GREEN_COLOR:
                startImageMatrix = imageService.initCanalMatrix(MyImage.GREEN_COLOR, startImage);
                resultImageMatrix = imageService.initCanalMatrix(MyImage.GREEN_COLOR, resultImage);
                for (int x = 0; x < startImage.getWidth(); x++) {
                    for (int y = 0; y < startImage.getHeight(); y++) {
                        gDifMatrix[x][y] = startImageMatrix[x][y] - resultImageMatrix[x][y];
                    }
                }
                return gDifMatrix;
            case MyImage.BLUE_COLOR:
                startImageMatrix = imageService.initCanalMatrix(MyImage.BLUE_COLOR, startImage);
                resultImageMatrix = imageService.initCanalMatrix(MyImage.BLUE_COLOR, resultImage);
                for (int x = 0; x < startImage.getWidth(); x++) {
                    for (int y = 0; y < startImage.getHeight(); y++) {
                        bDifMatrix[x][y] = startImageMatrix[x][y] - resultImageMatrix[x][y];
                    }
                }
                return bDifMatrix;
            default:
                throw new IllegalArgumentException("Enter one of const from MyImage.class");
        }
    }

    public int numberOfDifferentPixels(int[][] arr) {
        int i = 0;
        for (int x = 0; x < startImage.getWidth(); x++) {
            for (int y = 0; y < startImage.getHeight(); y++) {
                if (arr[x][y] != 0)
                    i++;
            }
        }
        return i;
    }

    public BufferedImage getStartImage() {
        return startImage;
    }

    public void setStartImage(BufferedImage startImage) {
        this.startImage = startImage;
    }

    public BufferedImage getResultImage() {
        return resultImage;
    }

    public void setResultImage(BufferedImage resultImage) {
        this.resultImage = resultImage;
    }


    public int[][] getrDifMatrix() {
        return rDifMatrix;
    }

    public void setrDifMatrix(int[][] rDifMatrix) {
        this.rDifMatrix = rDifMatrix;
    }

    public int[][] getgDifMatrix() {
        return gDifMatrix;
    }

    public void setgDifMatrix(int[][] gDifMatrix) {
        this.gDifMatrix = gDifMatrix;
    }

    public int[][] getbDifMatrix() {
        return bDifMatrix;
    }

    public void setbDifMatrix(int[][] bDifMatrix) {
        this.bDifMatrix = bDifMatrix;
    }
}
