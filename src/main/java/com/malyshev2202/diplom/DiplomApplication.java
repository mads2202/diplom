package com.malyshev2202.diplom;

import com.malyshev2202.diplom.backend.model.MyImage;
import com.malyshev2202.diplom.backend.noise.DumpNoise;
import com.malyshev2202.diplom.backend.service.BarGraphService;
import com.malyshev2202.diplom.fronend.BarGraphGui;

import java.io.File;

public class DiplomApplication {
    public static void main(String[] args) {
        File initialFile=new File("D:\\Учёба\\diplom","lena.jpg");
        MyImage image=MyImage.createImage(initialFile);
        image.initAllCanalMatrix();
        DumpNoise dumpNoise=new DumpNoise(image);
        dumpNoise.dumpNoiseGenerator(-67,45,-50);
        image.saveImage(dumpNoise.createNoisyImage(),"D:\\Учёба\\diplom");

        image.saveImage(dumpNoise.dumpNoiseRecovery(-67,45,-50),"D:\\Учёба\\");
       BarGraphGui barGraphGui=new  BarGraphGui(new BarGraphService(image),0);
       barGraphGui.setVisible(true);


    }
}
