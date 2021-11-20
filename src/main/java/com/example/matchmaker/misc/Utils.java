package com.example.matchmaker.misc;

public final class Utils {
    private static final double EPSILON = 0.000001;

    private Utils() {
    }

    public static boolean equals(double d1, double d2) {
        return Math.abs(d1 - d2) < EPSILON;
    }

    public static double kahanSum(double... fa) {
        double sum = 0.0;

        // Variable to store the error
        double c = 0.0;

        // Loop to iterate over the array
        for (double f : fa) {

            double y = f - c;
            double t = sum + y;

            // Algebraically, c is always 0
            // when t is replaced by its
            // value from the above expression.
            // But, when there is a loss,
            // the higher-order y is cancelled
            // out by subtracting y from c and
            // all that remains is the
            // lower-order error in c
            c = (t - sum) - y;

            sum = t;
        }

        return sum;
    }


}
