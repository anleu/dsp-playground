package com.anleu.dsptoolkit.filters;


import com.anleu.dsptoolkit.core.EquidistantDoubleSignal;

/**
 * Simple and fast analog like RC low pass filter
 */
public class RCLowpassFilter {

    public static EquidistantDoubleSignal calculateOnePath(EquidistantDoubleSignal signal, double cutoffFrequency) {
        return calculate(signal, cutoffFrequency, false);
    }

    public static EquidistantDoubleSignal calculateTwoPath(EquidistantDoubleSignal signal, double cutoffFrequency) {
        EquidistantDoubleSignal firstPathFilter = calculate(signal, cutoffFrequency, false);
        return calculate(firstPathFilter, cutoffFrequency, true);
    }

    private static EquidistantDoubleSignal calculate(EquidistantDoubleSignal signal, double cutoffFrequency, boolean reverse) {
        double[] yValues = signal.getYValues();
        EquidistantDoubleSignal filteredSignal = new EquidistantDoubleSignal("filtered", null, signal.getSamplingRate());
        double dt = 1.0 / signal.getSamplingRate();
        double RC = 1.0 / (2.0 * Math.PI * cutoffFrequency);

        double firstFilterCoefficient = dt / (RC + dt);
        double secondFilterCoefficient = RC / (RC + dt);

        if (reverse) {
            filteredSignal.setValueAt(yValues.length - 1, yValues[yValues.length - 1]);
            for (int i = yValues.length - 2; i > 0; i--) {
                // new output is first filter coefficient times current input plus second filter coefficient times previous output
                filteredSignal.setValueAt(i, firstFilterCoefficient * yValues[i] + secondFilterCoefficient * filteredSignal.getValueForIndex(i + 1));
            }
        } else {
            filteredSignal.setValueAt(0, yValues[0]);
            for (int i = 1; i < yValues.length; i++) {
                // new output is first filter coefficient times current input plus second filter coefficient times previous output
                filteredSignal.setValueAt(i, firstFilterCoefficient * yValues[i] + secondFilterCoefficient * filteredSignal.getValueForIndex(i - 1));
            }
        }
        return filteredSignal;
    }
}
