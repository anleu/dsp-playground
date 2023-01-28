package com.anleu.dsptoolkit.graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Show a simple xy graph based on jfreechart
 * Currently no options as parameter but there are a lot of options in the jfreechart library itself you can call from the context menu within the graph
 */
public class XYGraph extends ApplicationFrame {


    final XYSeries series = new XYSeries("Data");

    public XYGraph(String title, double[] xValues, double[] yValues) {
        super(title);
        if (xValues != null) {
            for (int i = 0; i < xValues.length; i++) {
                series.add(xValues[i], yValues[i]);
            }
        } else {
            for (int i = 0; i < yValues.length; i++) {
                series.add(i, yValues[i]);
            }
        }

    }

    public void showGraph() {
        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                getTitle(),
                "X",
                "Y",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
}
