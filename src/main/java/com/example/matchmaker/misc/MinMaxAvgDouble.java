package com.example.matchmaker.misc;

import java.util.Arrays;

public class MinMaxAvgDouble {

    private final long[] count;
    private final double[] sum;
    private final double[] sumCompensation;
    private final double[] simpleSum;
    private final double[] min;
    private final double[] max;
    private final boolean useKahanAlgo;

    public MinMaxAvgDouble(int count, boolean useKahanAlgo) {
        this.count = new long[count];
        sum = useKahanAlgo ? new double[count] : null;
        sumCompensation = useKahanAlgo ? new double[count] : null;
        simpleSum = new double[count];
        min = init(count, Double.POSITIVE_INFINITY);
        max = init(count, Double.NEGATIVE_INFINITY);
        this.useKahanAlgo = useKahanAlgo;
    }

    private double[] init(int count, double initValue) {
        final double[] doubles = new double[count];
        Arrays.fill(doubles, initValue);
        return doubles;
    }

    public void add(int index, double value) {
        ++count[index];
        simpleSum[index] += value;
        if (useKahanAlgo) {
            sumWithCompensation(index, value);
        }
        min[index] = Math.min(min[index], value);
        max[index] = Math.max(max[index], value);
    }

    private void sumWithCompensation(int index, double value) {
        double tmp = value - sumCompensation[index];
        double velvel = sum[index] + tmp;
        sumCompensation[index] = (velvel - sum[index]) - tmp;
        sum[index] = velvel;
    }

    public final double getAverage(int index) {
        final double count = getCount(index);
        return count > 0 ? getSum(index) / count : 0.0d;
    }

    public final double getMax(int index) {
        return max[index];
    }

    public final double getMin(int index) {
        return min[index];
    }

    private double getCount(int index) {
        return count[index];
    }

    public final double getSum(int index) {
        if (!useKahanAlgo) {
            return simpleSum[index];
        }
        double tmp = sum[index] + sumCompensation[index];
        if (Double.isNaN(tmp) && Double.isInfinite(simpleSum[index])) {
            return simpleSum[index];
        } else {
            return tmp;
        }
    }


}
