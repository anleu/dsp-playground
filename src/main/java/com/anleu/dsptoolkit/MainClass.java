package com.anleu.dsptoolkit;

import com.anleu.dsptoolkit.dft.Radix2FFT;
import com.anleu.dsptoolkit.generators.SineWaveGenerator;
import com.anleu.dsptoolkit.graphs.XYGraph;

public class MainClass {


    public static void main(String[] args) {


        // Show sine wave
        double[] sineWave5Hz = SineWaveGenerator.createSinusWave(5, 5000);
        XYGraph graph = new XYGraph("Graph", "Sine Wave 5Hz", null, sineWave5Hz);

        double[] sineWave10Hz = SineWaveGenerator.createSinusWave(10, 5000);
        graph.addSeries("Sine Wave 10Hz", null, sineWave10Hz);
        graph.showGraph();

        Radix2FFT radix2FFT = new Radix2FFT(null, sineWave5Hz);
        double[] realPart = new double[sineWave5Hz.length];
        double[] imgPart = new double[sineWave5Hz.length];
        radix2FFT.calculate(imgPart, realPart);

        XYGraph graph2 = new XYGraph("Real Part", "Real Part", null, realPart);
        graph2.showGraph();

        XYGraph graph3 = new XYGraph("Img Part", "Img Part", null, imgPart);
        graph3.showGraph();


        // show signal that contains several sine waves
//        double[] frequencies = {5, 10, 15};
//        double[] amplitudes = {1, 0.5, 0.25};
//        double[] composedSineWave = SineWaveGenerator.composedSineWave(frequencies, 5000, amplitudes);
//        XYGraph graph2 = new XYGraph("Composed Sine Wave", null, composedSineWave);
//        graph2.showGraph();
    }
}
