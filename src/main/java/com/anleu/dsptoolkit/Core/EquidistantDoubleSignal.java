package com.anleu.dsptoolkit.Core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EquidistantDoubleSignal {

    private String name;
    private String unit;
    private String XUnit;
    private double samplingRate; // samples per second

    // it would be better to use a double array instead of an ArrayList but this is easier to use for now
    private List<Double> yValues = new ArrayList<>(10000);

    public EquidistantDoubleSignal(String name, String unit, double samplingRate) {
        this.name = name;
        this.unit = unit;
        this.samplingRate = samplingRate;
        this.XUnit = "s";
    }

    public EquidistantDoubleSignal(double[] yValues, String unit, double samplingRate) {
        Arrays.stream(yValues).forEach(y -> this.yValues.add(y));
        this.unit = unit;
        this.samplingRate = samplingRate;
        this.XUnit = "s";
    }

    public void addValue(double yValue) {
        yValues.add(yValue);
    }

    public void setValueAt(int index, double yValue) {
        int indexGap = index - yValues.size();
        if (indexGap >= 0) {
            for (int i = 0; i < indexGap; i++) {
                yValues.add(null);
            }
            yValues.add(yValue);
        } else {
            yValues.set(index, yValue);
        }
    }

    public double getValueForIndex(int index) {
        return yValues.get(index);
    }

    public int getLength() {
        return yValues.size();
    }

    public String getName() {
        return name;
    }

    public double[] getYValues() {
        return yValues.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public double getSamplingRate() {
        return samplingRate;
    }
}
