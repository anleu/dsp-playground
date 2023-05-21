package com.anleu.dsptoolkit.generators;

public class DiracPulseGenerator {

    /**
     * Creates a dirac pulse with the given number of values and the position for the pulse.
     */
    public static int[] createDiracPulse(int numberOfValues, int positionForPulse) {
        int[] diracPulse = new int[numberOfValues];
        diracPulse[positionForPulse] = 1;
        return diracPulse;
    }

    /**
     * Returns the value for the given position in the dirac pulse with pulse at common position 0.
     */
    public static int getDiracPulseValue(int pos) {
        if (pos == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
