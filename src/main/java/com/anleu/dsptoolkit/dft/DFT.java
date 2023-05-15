package com.anleu.dsptoolkit.dft;

import com.anleu.dsptoolkit.core.EquidistantDoubleSignal;

public class DFT {

    private final EquidistantDoubleSignal signal;

    public DFT(EquidistantDoubleSignal signal) {
        this.signal = signal;
    }

    public double[] calc() {
        int N = signal.getLength();
        double[] dft = new double[N];
        for (int k = 0; k < N; k++) {
            double dftk = 0;
            for (int n = 0; n < N; n++) {
                double valueForIndex = signal.getValueForIndex(n);
                dftk += valueForIndex * Math.pow(Math.E, -2 * Math.PI * n * k / N);
            }
            dft[k] = dftk;
        }
        return dft;
    }
}
