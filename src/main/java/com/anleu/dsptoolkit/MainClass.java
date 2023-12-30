package com.anleu.dsptoolkit;

import com.anleu.dsptoolkit.core.EquidistantDoubleSignal;
import com.anleu.dsptoolkit.decimate.Decimate;
import com.anleu.dsptoolkit.dft.DFT;
import com.anleu.dsptoolkit.fft.FFTUtils;
import com.anleu.dsptoolkit.fft.Radix2FFT;
import com.anleu.dsptoolkit.filters.RCLowpassFilter;
import com.anleu.dsptoolkit.generators.SineWaveGenerator;
import com.anleu.dsptoolkit.graphs.XYGraph;

import java.util.stream.IntStream;

public class MainClass {


    public static void main(String[] args) {
      testDecimate();
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
        EquidistantDoubleSignal sineWave5Hz = SineWaveGenerator.createSinusWave(1, 1000, 1000, 1);
        XYGraph graph = new XYGraph("Graph", sineWave5Hz);
        graph.setxAxisLabel("Time in " + sineWave5Hz.getXUnit());
        graph.showGraph();
    }

    private static void testRCLowpass() {
        double[] frequencies = {5, 10, 20, 30, 40, 50, 200};
        double[] amplitudes = IntStream.range(0, frequencies.length).mapToDouble(i -> 1).toArray();
        EquidistantDoubleSignal composedSineWave = SineWaveGenerator.composedSineWave(frequencies, 1000, 1000, amplitudes);
        XYGraph signalGraph = new XYGraph("Signal-Graph", composedSineWave);

        EquidistantDoubleSignal onePathFilteredSignal = RCLowpassFilter.calculateOnePath(composedSineWave, 10);
        onePathFilteredSignal.setName("One Path Filtered Signal");
        signalGraph.addSeries(onePathFilteredSignal);

        EquidistantDoubleSignal twoPathFilteredSignal = RCLowpassFilter.calculateTwoPath(composedSineWave, 10);
        twoPathFilteredSignal.setName("Two Path Filtered Signal");
        signalGraph.addSeries(twoPathFilteredSignal);

        signalGraph.showGraph();

        XYGraph fftGraph = new XYGraph("FFT-Graph");

        Radix2FFT fftComposedSineWave = new Radix2FFT(composedSineWave);
        FFTUtils.prepareFFTGraph(composedSineWave, fftComposedSineWave.calculateImaginaryPart(), fftGraph);

        Radix2FFT fftOnePathFilteredSignal = new Radix2FFT(onePathFilteredSignal);
        FFTUtils.prepareFFTGraph(onePathFilteredSignal, fftOnePathFilteredSignal.calculateImaginaryPart(), fftGraph);

        Radix2FFT fftTwoPathFilteredSignal = new Radix2FFT(twoPathFilteredSignal);
        FFTUtils.prepareFFTGraph(twoPathFilteredSignal, fftTwoPathFilteredSignal.calculateImaginaryPart(), fftGraph);

        fftGraph.showGraph();
    }

    private static void testDFT() {
        // Show sine wave
        EquidistantDoubleSignal sineWave5Hz = SineWaveGenerator.createSinusWave(5, 5000);


        XYGraph signalGraph = new XYGraph("origSignal", sineWave5Hz);
        signalGraph.showGraph();

        DFT radix2FFT = new DFT(sineWave5Hz);
        double[] dft = radix2FFT.calc();
        double[] frequencies = new double[dft.length];
        double frequencyResolution = (sineWave5Hz.getSamplingRate() / 2) / dft.length;
        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] = i * frequencyResolution;
        }

        XYGraph dftGraph = new XYGraph("DFT", "DFT", frequencies, dft);
        dftGraph.showGraph();
    }

    private static void testDecimate(){
        // Show sine wave
        EquidistantDoubleSignal sineWave5Hz = SineWaveGenerator.createSinusWave(5, 5000);
        XYGraph signalGraph = new XYGraph("origSignal", sineWave5Hz);
        signalGraph.showGraph();
        EquidistantDoubleSignal peakHoldDecimate = Decimate.minMaxDownsample(sineWave5Hz, 20);
        XYGraph decimateGraph = new XYGraph("decimated", peakHoldDecimate);
        decimateGraph.showGraph();

        EquidistantDoubleSignal filtered = RCLowpassFilter.calculateOnePath(peakHoldDecimate, 25);
        XYGraph filteredGraph = new XYGraph("decimated+lp", filtered);
        filteredGraph.showGraph();

    }
}
