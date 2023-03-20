package com.anleu.dsptoolkit.exampleCalculations;

import org.realityforge.vecmath.Vector4d;

public class Exercise2VectorDotProductsMain {


    public static void main(String[] args) {


        Vector4d y = new Vector4d(2,1,1,0);
        Vector4d v0 = new Vector4d(.5, .5, .5, .5);
        Vector4d v1 = new Vector4d(.5, .5, -.5, -.5);
        Vector4d v2 = new Vector4d(.5, -.5, .5, -.5);
        Vector4d v3 = new Vector4d(.5, -.5, -.5, .5);

        double yDotV0 = y.dot(v0);
        double yDotV1 = y.dot(v1);
        double yDotV2 = y.dot(v2);
        double yDotV3 = y.dot(v3);

        System.out.println("yDotV0: " + yDotV0);
        System.out.println("yDotV1: " + yDotV1);
        System.out.println("yDotV2: " + yDotV2);
        System.out.println("yDotV3: " + yDotV3);

    }
}
