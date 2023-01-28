package com.anleu.dsptoolkit.graphs;

import com.anleu.dsptoolkit.Core.EquidistantDoubleSignal;
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
 * <p>
 * // ToDos
 * // ToDo set unit to axis
 */
public class XYGraph extends ApplicationFrame {

    private final XYSeriesCollection data = new XYSeriesCollection();

    public XYGraph(String title) {
        super(title);
    }

    public XYGraph(String title, String dataName, double[] xValues, double[] yValues) {
        super(title);
        final XYSeries series = new XYSeries(dataName);
        if (xValues != null) {
            for (int i = 0; i < xValues.length; i++) {
                series.add(xValues[i], yValues[i]);
            }
        } else {
            for (int i = 0; i < yValues.length; i++) {
                series.add(i, yValues[i]);
            }
        }
        data.addSeries(series);
    }

    public XYGraph(String graphName, EquidistantDoubleSignal signal) {
        super(graphName);
        addSeries(signal);
    }

    public void addSeries(String name, double[] xValues, double[] yValues) {
        final XYSeries series = new XYSeries(name);
        if (xValues != null) {
            for (int i = 0; i < xValues.length; i++) {
                series.add(xValues[i], yValues[i]);
            }
        } else {
            for (int i = 0; i < yValues.length; i++) {
                series.add(i, yValues[i]);
            }
        }
        data.addSeries(series);
    }

    public void addSeries(EquidistantDoubleSignal signal) {
        final XYSeries series = new XYSeries(signal.getName());
        for (int i = 0; i < signal.getLength(); i++) {
            double xValue = 1 / signal.getSamplingRate() * i;
            series.add(xValue, signal.getValueForIndex(i));
        }
        data.addSeries(series);
    }

    public void showGraph() {

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
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
}
