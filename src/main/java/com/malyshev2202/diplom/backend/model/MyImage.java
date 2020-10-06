package com.malyshev2202.diplom.backend.model;

import com.malyshev2202.diplom.backend.service.MyImageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

//Этот класс является оберткой для изображения для изображения
public class MyImage {
    private Long id;
    private Date date;
    private  BufferedImage image;
    private MyImageService myImageService;

    private int[][] rCanalMatrix;
    private int[][] gCanalMatrix;
    private int[][] bCanalMatrix;
    public static final int RED_COLOR = 0;
    public static final int GREEN_COLOR = 1;
    public static final int BLUE_COLOR = 2;
    public static final int ALL_COLORS = 3;


    public MyImage(BufferedImage image) {
        this.date=new Date();
        this.image = image;
        this.rCanalMatrix = new int[image.getWidth()][image.getHeight()];
        this.gCanalMatrix = new int[image.getWidth()][image.getHeight()];
        this.bCanalMatrix = new int[image.getWidth()][image.getHeight()];
        this.myImageService=new MyImageService();
        myImageService.initAllCanalMatrix(this);



    }
    public MyImage(File initialFile){
        this.date=new Date();
        try {
            this.image=ImageIO.read(initialFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.myImageService=new MyImageService();
        myImageService.initAllCanalMatrix(this);


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public  BufferedImage getImage() {
        return image;
    }

    public  void setImage(BufferedImage image) {
        this.image = image;
    }

    public int[][] getrCanalMatrix() {
        return rCanalMatrix;
    }

    public void setrCanalMatrix(int[][] rCanalMatrix) {
        this.rCanalMatrix = rCanalMatrix;
    }

    public int[][] getgCanalMatrix() {
        return gCanalMatrix;
    }

    public void setgCanalMatrix(int[][] gCanalMatrix) {
        this.gCanalMatrix = gCanalMatrix;
    }

    public int[][] getbCanalMatrix() {
        return bCanalMatrix;
    }

    public void setbCanalMatrix(int[][] bCanalMatrix) {
        this.bCanalMatrix = bCanalMatrix;
    }
}