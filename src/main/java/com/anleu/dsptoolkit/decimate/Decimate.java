package com.anleu.dsptoolkit.decimate;

import com.anleu.dsptoolkit.core.EquidistantDoubleSignal;

public class Decimate {

    public static EquidistantDoubleSignal peakHoldDecimate(EquidistantDoubleSignal signal, int downsampleFactor) {
        int newSize = signal.getLength() / downsampleFactor;
        EquidistantDoubleSignal decimatedSignal = new EquidistantDoubleSignal(signal.getName(), signal.getUnit(), newSize);
        int downsampledIndex = 0;
        for (int i = 0; i < signal.getLength(); i += downsampleFactor) {
            double valueForIndex = signal.getValueForIndex(i);
            double max = valueForIndex;
            double min = valueForIndex;
            for (int j = i; j < i + downsampleFactor && j < signal.getLength(); j++) {
                if (signal.getValueForIndex(j) > max) {
                    max = signal.getValueForIndex(j);
                }
                if (signal.getValueForIndex(j) < min) {
                    min = signal.getValueForIndex(j);
                }
            }
            decimatedSignal.setValueAt(downsampledIndex++, max);
            decimatedSignal.setValueAt(downsampledIndex++, min);
        }
        return decimatedSignal;
    }

    public static EquidistantDoubleSignal minMaxDownsample(EquidistantDoubleSignal signal, int downsampleFactor) {
        int newSize = (signal.getLength() / downsampleFactor);
        EquidistantDoubleSignal decimatedSignal = new EquidistantDoubleSignal(signal.getName(), signal.getUnit(), newSize);
        int downsampledIndex = 0;
        for (int i = 0; i < signal.getLength(); i += downsampleFactor) {
            double valueForIndex = signal.getValueForIndex(i);
            double min = valueForIndex;
            double max = valueForIndex;
            for (int j = i; j < i + downsampleFactor && j < signal.getLength(); j++) {
                double valueForIndex1 = signal.getValueForIndex(j);
                if (valueForIndex1 > max) {
                    max = valueForIndex1;
                }
                if (valueForIndex1 < min) {
                    min = valueForIndex1;
                }
            }
            decimatedSignal.setValueAt(downsampledIndex++, min);
            decimatedSignal.setValueAt(downsampledIndex++, max);
        }

        return decimatedSignal;
    }
}
