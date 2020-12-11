package com.mastermind.UI;

public class Layout {
    public static int getRatio(int dimension, double ratio) {
        return (int) (Math.ceil(dimension * ratio));
    }

    public static double calculateRatio(double splitter) {
        return 1 / (splitter + 1);
    }

    public static int ovalCenterPosition(int radius, int position) {
        return position - (radius / 2);
    }
}
