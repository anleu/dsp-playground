package com.anleu.dsptoolkit;

import com.anleu.dsptoolkit.generators.SinusGenerator;
import com.anleu.dsptoolkit.graphs.XYGraph;

public class MainClass {


    public static void main(String[] args) {


        // Show simple graph
        double[] sinusWave = SinusGenerator.createSinusWave(5, 1);
        XYGraph graph = new XYGraph("Sinus", null, sinusWave);
        graph.showGraph();
    }
}
