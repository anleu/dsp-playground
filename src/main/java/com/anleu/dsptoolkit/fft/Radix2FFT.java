package com.anleu.dsptoolkit.fft;


import com.anleu.dsptoolkit.core.EquidistantDoubleSignal;

/**
 * Also called Cooley und Tukey FFT
 * Sample length must be a power of 2
 */
public class Radix2FFT {

    // implementation of the Cooley-Tukey FFT algorithm


    private final EquidistantDoubleSignal signal;

    public Radix2FFT(EquidistantDoubleSignal signal) {
        this.signal = signal;
    }

    /**
     * Calculates the FFT by radix2
     */
    public double[] calculateImaginaryPart() {
        double[] yValues = signal.getYValues();

        if (yValues == null || yValues.length % 2 != 0) {
            throw new IllegalArgumentException("yValues must not be null and must have a length of 2^n");
        }

        double phi;
        int n = yValues.length;
        double maxFrequency = signal.getSamplingRate() / 2.0; // Nyquist frequency
        double[] imgValues = new double[(int) Math.ceil(maxFrequency)];

        double divn = 1.0 / (double) n;

        for (int k = 0; k < maxFrequency; k++) {
            imgValues[k] = 0.0;
            for (int i = 0; i < n; i++) {
                phi = Math.PI * divn * (double) k * (double) i;
//                realValues[k] += yValues[i] * Math.cos(phi);
                imgValues[k] += yValues[i] * Math.sin(phi);
            }
//            realValues[k] /= (n / 2.0);
            imgValues[k] /= (n / 2.0);
        }
        return imgValues;
    }
}
