package com.anleu.dsptoolkit.dft;

import com.anleu.dsptoolkit.core.EquidistantDoubleSignal;
import com.anleu.dsptoolkit.graphs.XYGraph;

public class FFTUtils {

    public static void prepareFFTGraph(EquidistantDoubleSignal origSignal, double[] imaginaryPart, XYGraph graph) {
        double[] frequcencies = new double[imaginaryPart.length];
        double frequencyResolution = (origSignal.getSamplingRate() / 2) / imaginaryPart.length;
        for (int i = 0; i < frequcencies.length; i++) {
            frequcencies[i] = i * frequencyResolution;
        }
        graph.addSeries("FFT of " + origSignal.getName(), frequcencies, imaginaryPart);
    }
}
