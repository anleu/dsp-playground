package com.anleu.dsptoolkit;

import com.anleu.dsptoolkit.core.EquidistantDoubleSignal;
import com.anleu.dsptoolkit.dft.Radix2FFT;
import com.anleu.dsptoolkit.filters.RCLowpassFilter;
import com.anleu.dsptoolkit.generators.SineWaveGenerator;
import com.anleu.dsptoolkit.graphs.XYGraph;

public class MainClass {


    public static void main(String[] args) {
        testRCLowpass();
    }

    private static void testRadix2FFT() {
        // Show sine wave
        EquidistantDoubleSignal sineWave5Hz = SineWaveGenerator.createSinusWave(5, 5000);

        Radix2FFT radix2FFT = new Radix2FFT(sineWave5Hz);
        double[] imaginaryPart = radix2FFT.calculateImaginaryPart();
        double[] frequcencies = new double[imaginaryPart.length];
        double frequencyResolution = (sineWave5Hz.getSamplingRate() / 2) / imaginaryPart.length;
        for (int i = 0; i < frequcencies.length; i++) {
            frequcencies[i] = i * frequencyResolution;
        }

        XYGraph graph = new XYGraph("Img Part", "Img Part", frequcencies, imaginaryPart);
        graph.showGraph();
    }

    private static void testSineWaveGenerator() {
        EquidistantDoubleSignal sineWave5Hz = SineWaveGenerator.createSinusWave(5, 5000);
        XYGraph graph = new XYGraph("Graph", sineWave5Hz);

        EquidistantDoubleSignal sineWave10Hz = SineWaveGenerator.createSinusWave(10, 5000);
        graph.addSeries(sineWave10Hz);
        graph.showGraph();
    }

    private static void testRCLowpass() {
        double[] frequencies = {5, 200};
        double[] amplitudes = {1, 1};
        EquidistantDoubleSignal composedSineWave = SineWaveGenerator.composedSineWave(frequencies, 5000, amplitudes);
        XYGraph graph = new XYGraph("Graph", composedSineWave);

        EquidistantDoubleSignal onePathFilteredSignal = RCLowpassFilter.calculateOnePath(composedSineWave, 10);
        onePathFilteredSignal.setName("One Path Filtered Signal");
        graph.addSeries(onePathFilteredSignal);

        EquidistantDoubleSignal twoPathFilteredSignal = RCLowpassFilter.calculateTwoPath(composedSineWave, 10);
        twoPathFilteredSignal.setName("Two Path Filtered Signal");
        graph.addSeries(twoPathFilteredSignal);

        graph.showGraph();


    }


}
