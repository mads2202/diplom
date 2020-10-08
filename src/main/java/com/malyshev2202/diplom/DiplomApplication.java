package com.malyshev2202.diplom;

import com.malyshev2202.diplom.backend.model.MyImage;
import com.malyshev2202.diplom.backend.noise.DumpNoise;
import com.malyshev2202.diplom.backend.service.BarGraphService;
import com.malyshev2202.diplom.backend.service.MyImageService;
import com.malyshev2202.diplom.fronend.BarGraphGui;

import java.io.File;

public class DiplomApplication {
    public static void main(String[] args) {
        File initialFile = new File("D:\\Учёба\\diplom", "lena.jpg");
        MyImage image = new MyImage(initialFile);
        BarGraphService barGraphService = new BarGraphService(image);
        DumpNoise dumpNoise = new DumpNoise(image);
        //dumpNoise.dumpNoiseGenerator(1,1,100);
        int redIntencity=barGraphService.getMedian(barGraphService.getRedIntencityMatrix());
       // System.out.println(redIntencity);

        int greenIntencity=barGraphService.getMedian(barGraphService.getGreenIntencityMatrix());
       // System.out.println(greenIntencity);

        int blueIntencity=barGraphService.getMedian(barGraphService.getBlueIntencityMatrix());
       // System.out.println(blueIntencity);

        //dumpNoise.gauseNoiseGenerator( redIntencity, greenIntencity,blueIntencity );
        dumpNoise.linearСongruentNoiseGenerator();
        MyImageService myImageService = new MyImageService();
        myImageService.saveImage(dumpNoise.createNoisyImage(), "D:\\Учёба\\diplom");
        //myImageService.saveImage(dumpNoise.gausNoiseRecovery(),"D:\\Учёба\\");
        myImageService.saveImage(dumpNoise.linearСongruentNoiseRecovery(),"D:\\Учёба\\");
        //myImageService.saveImage(dumpNoise.dumpNoiseRecovery(1,1,100),"D:\\Учёба\\");
        BarGraphGui barGraphGui = new BarGraphGui(barGraphService, 3);
        barGraphGui.setVisible(true);




    }
}
