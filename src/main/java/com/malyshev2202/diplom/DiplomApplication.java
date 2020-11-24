package com.malyshev2202.diplom;

import com.malyshev2202.diplom.backend.model.MyImage;
import com.malyshev2202.diplom.backend.noise.DumpNoise;
import com.malyshev2202.diplom.backend.noise.GausNoise;
import com.malyshev2202.diplom.backend.noise.LinearСongruentNoise;
import com.malyshev2202.diplom.backend.noise.PhaseNoise;
import com.malyshev2202.diplom.backend.service.BarGraphService;
import com.malyshev2202.diplom.backend.service.ImageCreater;
import com.malyshev2202.diplom.backend.service.ImagesComparatorService;
import com.malyshev2202.diplom.backend.service.MyImageService;
import com.malyshev2202.diplom.fronend.BarGraphGui;

import java.awt.image.BufferedImage;
import java.io.File;

public class DiplomApplication {
    public static void main(String[] args) {
        File initialFile = new File("D:\\Учёба\\diplom", "lena.jpg");
        MyImage image = new MyImage(initialFile);
        BarGraphService barGraphService = new BarGraphService(image);
        MyImageService myImageService = new MyImageService();
/*
        //вариант 1 шума(тупо добавляем кожфициенты  по 3 каналам)
        //создали класс тупой шум для применения тупых шумов
        DumpNoise dumpNoise = new DumpNoise(image);
        //создаём зашумлённые матрицы
        long start = System.nanoTime();
        dumpNoise.dumpNoiseGenerator(230,0,200);
        long finish=System.nanoTime();
        System.out.println((finish-start)/ 1000000);
        //на основе зашумленных матриц создаём шумную картинку и сохраняем ее
        myImageService.saveImage(ImageCreater.createNoisyImage(image,dumpNoise.getrCanalNoisyMatrix(),
                dumpNoise.getgCanalNoisyMatrix(),dumpNoise.getbCanalNoisyMatrix()),
                "D:\\Учёба\\diplom");
        //восстанавливаем картинку и сохраняем результат
        myImageService.saveImage(dumpNoise.dumpNoiseRecovery(230,0,200),"D:\\Учёба\\");
*/
/*
        //вариант 2 шума(приводим все значения интенсивности пикселей к медианному значению по 3 каналам)
        //создали класс тупой шум для применения тупых шумов
        GausNoise gausNoise = new GausNoise(image);
        //в следующих 3 строчках получаем медианные значения интенсивности матриц интенсивности
        int redIntencity=barGraphService.getMedian(barGraphService.getRedIntencityMatrix());
        int greenIntencity=barGraphService.getMedian(barGraphService.getGreenIntencityMatrix());
        int blueIntencity=barGraphService.getMedian(barGraphService.getBlueIntencityMatrix());
        //создаём зашумлённые матрицы
        gausNoise.gauseNoiseGenerator( redIntencity, greenIntencity,blueIntencity );
        //на основе зашумленных матриц создаём шумную картинку и сохраняем ее
        myImageService.saveImage(ImageCreater.createNoisyImage(image,gausNoise.getrCanalNoisyMatrix(),
                gausNoise.getgCanalNoisyMatrix(),gausNoise.getbCanalNoisyMatrix()), "D:\\Учёба\\diplom");
        //восстанавливаем картинку и сохраняем результат
        myImageService.saveImage(gausNoise.gausNoiseRecovery(),"D:\\Учёба\\");
*/

/*
        //вариант 3 шума(зашумляем картинку добавлением коэфициентов сгенерированных линейно конгруэнтым методом)
        //создали класс тупой шум для применения тупых шумов
        LinearСongruentNoise linearСongruentNoise = new LinearСongruentNoise(image);
        //создаём зашумлённые матрицы
        long start = System.nanoTime();
        linearСongruentNoise.linearСongruentNoiseGenerator();
        System.out.println((System.nanoTime()-start)/ 1000000);
        //на основе зашумленных матриц создаём шумную картинку и сохраняем ее
        myImageService.saveImage(ImageCreater.createNoisyImage(image,linearСongruentNoise.getrCanalNoisyMatrix(),
                linearСongruentNoise.getgCanalNoisyMatrix(),linearСongruentNoise.getbCanalNoisyMatrix()), "D:\\Учёба\\diplom");
        //восстанавливаем картинку и сохраняем результат
        myImageService.saveImage(linearСongruentNoise.linearСongruentNoiseRecovery(),"D:\\Учёба\\");
*/

    // использование фазового шума
       // создаём класс фазового шума


       /* PhaseNoise phaseNoise = new PhaseNoise(image);
        LinearСongruentNoise linearСongruentNoise = new LinearСongruentNoise(image);
        //создаём зашумлённые матрицы
        linearСongruentNoise.linearСongruentNoiseGenerator();
        BufferedImage bufferedImage=ImageCreater.createNoisyImage(image,linearСongruentNoise.getrCanalNoisyMatrix(),
                linearСongruentNoise.getgCanalNoisyMatrix(),linearСongruentNoise.getbCanalNoisyMatrix());
        MyImage noisyImage= new MyImage(bufferedImage);
        int[][] r=phaseNoise.phaseNoiseGeneratorPhase(noisyImage.getrCanalMatrix(),512.0,226.0,29.0);
        for (int i=0;i<10;i++){
            r=phaseNoise.phaseNoiseGeneratorPhase(r,512.0,226.0,29.0);
        }
        phaseNoise.setrCanalNoisyMatrix(r);
        int[][] g=phaseNoise.phaseNoiseGeneratorPhase(noisyImage.getgCanalMatrix(),512.0,226.0,29.0);
        for (int i=0;i<20;i++){
            g=phaseNoise.phaseNoiseGeneratorPhase(g,512.0,226.0,29.0);
        }
        phaseNoise.setgCanalNoisyMatrix(g);
        int[][] b=phaseNoise.phaseNoiseGeneratorPhase(noisyImage.getbCanalMatrix(),512.0,226.0,29.0);
        for (int i=0;i<30;i++){
            b=phaseNoise.phaseNoiseGeneratorPhase(b,512.0,226.0,29.0);
        }
        phaseNoise.setbCanalNoisyMatrix(b);

        myImageService.saveImage(ImageCreater.createNoisyImage(noisyImage,phaseNoise.getrCanalNoisyMatrix(),
                phaseNoise.getgCanalNoisyMatrix(),phaseNoise.getbCanalNoisyMatrix()),
                "D:\\Учёба\\diplom");
       BarGraphGui barGraphGui = new BarGraphGui(barGraphService, 3);
        barGraphGui.setVisible(true);*/

        /*
        //отличия
        File resultFile = new File("D:\\Учёба", "outPutImage.jpg");
        ImagesComparatorService comparatorService=new ImagesComparatorService(initialFile,resultFile);
        System.out.println(comparatorService.numberOfDifferentPixels(comparatorService.compareCanalMatrix(MyImage.RED_COLOR)));
        System.out.println(comparatorService.numberOfDifferentPixels(comparatorService.compareCanalMatrix(MyImage.GREEN_COLOR)));
        System.out.println(comparatorService.numberOfDifferentPixels(comparatorService.compareCanalMatrix(MyImage.BLUE_COLOR)));*/


    }
}

