package com.example.battlecity;

public class Utils {

    public static double getDistanceBetweenTwoPoints(double p1x, double p1y, double p2x, double p2y) {
        return distanceBetween(
                (p1x-p2x),
                (p1y-p2y)
        );
    }
    static double distanceBetween(double X, double Y){
        return Math.sqrt(Math.pow(X,2)+Math.pow(Y,2));
    }
}
