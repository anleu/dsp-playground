package com.anleu.dsptoolkit.generators;

public class SineWaveGenerator {

    public static double[] createSinusWave(double frequency, int numberOfValues) {
        return createSinusWave(frequency, 1000, numberOfValues, 1);
    }

    public static double[] createSinusWave(double frequency, int sampleRate, int numberOfValues, double amplitude) {
        double[] sinusWave = new double[numberOfValues];
        double samplingInterval = sampleRate / frequency;
        for (int i = 0; i < numberOfValues; i++) {
            double angle = (2.0 * Math.PI * i) / samplingInterval;
            sinusWave[i] = Math.sin(angle) * amplitude;
        }
        return sinusWave;
    }
    // ToDo would be interesting to show the creation of the sine wave with the calculation of the angle, the point on the unit circle and the point on the graph

    public static double[] composedSineWave(double[] frequencies, int numberOfValues, double[] amplitudes) {
        return composedSineWave(frequencies, 1000, numberOfValues, amplitudes);
    }

    public static double[] composedSineWave(double[] frequencies, int sampleRate, int numberOfValues, double[] amplitudes) {
        double[] composedSineWave = new double[numberOfValues];
        for (int i = 0; i < numberOfValues; i++) {
            composedSineWave[i] = 0;
            for (int j = 0; j < frequencies.length; j++) {
                double angle = (2.0 * Math.PI * i * frequencies[j]) / sampleRate;
                composedSineWave[i] += Math.sin(angle) * amplitudes[j];
            }
        }
        return composedSineWave;
    }

}
