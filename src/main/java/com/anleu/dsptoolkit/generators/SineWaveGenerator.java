package com.anleu.dsptoolkit.generators;

import com.anleu.dsptoolkit.core.EquidistantDoubleSignal;

public class SineWaveGenerator {

    public static EquidistantDoubleSignal createSinusWave(double frequency, int numberOfValues) {
        return createSinusWave(frequency, 1000, numberOfValues, 1);
    }

    public static EquidistantDoubleSignal createSinusWave(double frequency, int sampleRate, int numberOfValues, double amplitude) {
        EquidistantDoubleSignal sineWave = new EquidistantDoubleSignal("sine wave " + frequency + " Hz", null, sampleRate);
        double samplingInterval = sampleRate / frequency;
        for (int i = 0; i < numberOfValues; i++) {
            double angle = (2.0 * Math.PI * i) / samplingInterval;
            sineWave.setValueAt(i, Math.sin(angle) * amplitude);
        }
        return sineWave;
    }
    // ToDo would be interesting to show the creation of the sine wave with the calculation of the angle, the point on the unit circle and the point on the graph

    public static EquidistantDoubleSignal composedSineWave(double[] frequencies, int numberOfValues, double[] amplitudes) {
        return composedSineWave(frequencies, 1000, numberOfValues, amplitudes);
    }

    public static EquidistantDoubleSignal composedSineWave(double[] frequencies, int sampleRate, int numberOfValues, double[] amplitudes) {
        EquidistantDoubleSignal composedSineWave = new EquidistantDoubleSignal("Composed sine wave", null, sampleRate);
        for (int i = 0; i < numberOfValues; i++) {
            composedSineWave.setValueAt(0, 0);
            for (int j = 0; j < frequencies.length; j++) {
                double angle = (2.0 * Math.PI * i * frequencies[j]) / sampleRate;
                double valueForIndex = composedSineWave.getValueForIndex(i);
                composedSineWave.setValueAt(i, valueForIndex + Math.sin(angle) * amplitudes[j]);
            }
        }
        return composedSineWave;
    }
}
