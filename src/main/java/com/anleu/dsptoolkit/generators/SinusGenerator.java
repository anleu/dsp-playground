package com.anleu.dsptoolkit.generators;

public class SinusGenerator {

    public static double[] createSinusWave(double frequency, int numberOfValues) {
        return createSinusWave(frequency, numberOfValues, 1);
    }

    public static double[] createSinusWave(double frequency, int numberOfValues, double amplitude) {
        double[] sinusWave = new double[numberOfValues];
        for (int i = 0; i < numberOfValues; i++) {
            sinusWave[i] = Math.sin(2 * Math.PI * frequency * i) * amplitude;
        }
        return sinusWave;
    }
}
