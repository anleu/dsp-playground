package com.anleu.dsptoolkit.generators;

public class SinusGenerator {

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
}
