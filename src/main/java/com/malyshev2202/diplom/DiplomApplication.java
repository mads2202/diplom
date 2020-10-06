package com.malyshev2202.diplom;

import com.malyshev2202.diplom.backend.model.MyImage;
import com.malyshev2202.diplom.backend.noise.DumpNoise;
import com.malyshev2202.diplom.backend.service.BarGraphService;
import com.malyshev2202.diplom.backend.service.MyImageService;
import com.malyshev2202.diplom.fronend.BarGraphGui;

import java.io.File;

public class DiplomApplication {
    public static void main(String[] args) {
        File initialFile=new File("D:\\Учёба\\diplom","lena.jpg");
        MyImage image=new MyImage(initialFile);
        DumpNoise dumpNoise=new DumpNoise(image);
        dumpNoise.dumpNoiseGenerator(1,1,100);
        MyImageService myImageService=new MyImageService();
        myImageService.saveImage(dumpNoise.createNoisyImage(),"D:\\Учёба\\diplom");

        myImageService.saveImage(dumpNoise.dumpNoiseRecovery(1,1,100),"D:\\Учёба\\");
       BarGraphGui barGraphGui=new  BarGraphGui(new BarGraphService(image),0);
       barGraphGui.setVisible(true);


    }
}
