package com.mastermind.Utils;

/**
 * Class that provides sizing methods
 * for board drawing
 */
public class Layout {
    /**
     * Applies the provided ratio to the dimension
     *
     * @param dimension Dimension on which we calculate the ratio
     * @param ratio     Ratio to apply
     * @return The calculated ratio on the dimension
     */
    public static int getRatio(int dimension, double ratio) {
        return (int) (Math.ceil(dimension * ratio));
    }

    /**
     * Method to calculate the ratio based on splitter
     *
     * <p>
     * For example if we have some space that we want to divide in 10 equal parts we can
     * calculate the ratio passing 10 as splitter
     * </p>
     *
     * @param splitter Represents the space divisor
     * @return The calculated ratio based on the splitter
     */
    public static double calculateRatio(double splitter) {
        return 1 / (splitter + 1);
    }
}
