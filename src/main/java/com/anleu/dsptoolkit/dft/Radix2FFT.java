package com.anleu.dsptoolkit.dft;


/**
 * Also called Cooley und Tukey FFT
 * Sample length must be a power of 2
 */
public class Radix2FFT {

    // implementation of the Cooley-Tukey FFT algorithm


    private final double[] yValues;
    private final double[] xValues;


    public Radix2FFT(double[] xValues, double[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    /**
     * Calculates the FFT by radix2
     * @param imgValues imaginary values --> sine wave amplitudes
     * @param realValues real values --> cosine wave amplitudes
     */
    public void calculate(double[] imgValues, double[] realValues) {
        if (yValues == null || yValues.length == 1 || yValues.length % 2 != 0) {
            throw new IllegalArgumentException("yValues must not be null and must have a length of 2^n");
        }

        int k;
        int m;
        double divn;
        double phi;

        int n = yValues.length;

        divn = 1.0 / (double) n;

        for (k = 0; k < n; k++) {
            realValues[k] = imgValues[k] = 0.0;
            for (m = 0; m < n; m++) {
                phi = Math.PI * divn * (double) k * (double) m;
                realValues[k] += yValues[m] * Math.cos(phi);
                imgValues[k] += yValues[m] * Math.sin(phi);
            }
        }
    }
}
