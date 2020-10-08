package com.malyshev2202.diplom.fronend;

import com.malyshev2202.diplom.backend.service.BarGraphService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;

import com.malyshev2202.diplom.backend.model.MyImage;

public class BarGraphGui extends ApplicationFrame {
    private int[] redIntencityMatrix;
    private int[] greenIntencityMatrix;
    private int[] blueIntencityMatrix;

    public BarGraphGui(BarGraphService barGraphService, int colorConst) {
        super("График распределения интенсивности");
        this.redIntencityMatrix = barGraphService.getRedIntencityMatrix();
        this.greenIntencityMatrix = barGraphService.getGreenIntencityMatrix();
        this.blueIntencityMatrix = barGraphService.getBlueIntencityMatrix();
        JFreeChart xylineChart = ChartFactory.createXYLineChart("Интенсивность", "Интенсивность", "Пиксели",
                createDataSet(colorConst), PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new Dimension(560, 367));
        final XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        switch (colorConst) {
            case MyImage.RED_COLOR:
                renderer.setSeriesPaint(0, Color.RED);
                break;
            case MyImage.GREEN_COLOR:
                renderer.setSeriesPaint(0, Color.GREEN);
                break;
            case MyImage.BLUE_COLOR:
                renderer.setSeriesPaint(0, Color.BLUE);
                break;
            case MyImage.ALL_COLORS:
                renderer.setSeriesPaint(0, Color.RED);
                renderer.setSeriesPaint(1, Color.GREEN);
                renderer.setSeriesPaint(2, Color.BLUE);
                break;
        }
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);

    }

    private XYDataset createDataSet(int colorConst) {
        XYSeries red = new XYSeries("красный канал");
        for (int x = 0; x < redIntencityMatrix.length; x++) {
            red.add(x, redIntencityMatrix[x]);
        }
        XYSeries green = new XYSeries("зелёный канал");
        for (int x = 0; x < greenIntencityMatrix.length; x++) {
            green.add(x, greenIntencityMatrix[x]);
        }
        XYSeries blue = new XYSeries("синий канал");
        for (int x = 0; x < blueIntencityMatrix.length; x++) {
            blue.add(x, blueIntencityMatrix[x]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        switch (colorConst) {
            case MyImage.RED_COLOR:
                dataset.addSeries(red);
                break;
            case MyImage.GREEN_COLOR:
                dataset.addSeries(green);
                break;
            case MyImage.BLUE_COLOR:
                dataset.addSeries(blue);
                break;
            case MyImage.ALL_COLORS:
                dataset.addSeries(red);
                dataset.addSeries(green);
                dataset.addSeries(blue);
                break;

        }
        return dataset;
    }
}
