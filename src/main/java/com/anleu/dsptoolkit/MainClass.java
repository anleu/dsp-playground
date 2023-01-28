package com.anleu.dsptoolkit;

import com.anleu.dsptoolkit.generators.SinusGenerator;
import com.anleu.dsptoolkit.graphs.XYGraph;

public class MainClass {


    public static void main(String[] args) {


        // Show sine wave
        double[] sinusWave = SinusGenerator.createSinusWave(5, 5000);
        XYGraph graph = new XYGraph("Sine Wave 5Hz", null, sinusWave);
        graph.showGraph();

        // show signal that contains several sine waves
        double[] frequencies = {5, 10, 15};
        double[] amplitudes = {1, 0.5, 0.25};
        double[] composedSineWave = SinusGenerator.composedSineWave(frequencies, 5000, amplitudes);
        XYGraph graph2 = new XYGraph("Composed Sine Wave", null, composedSineWave);
        graph2.showGraph();
    }
}
