package com.example.matchmaker.model;

import lombok.ToString;
import lombok.Value;

import java.util.List;
import java.util.function.BiFunction;

public class MinMaxAvg<T extends Comparable<T>> {
    private final T min;
    private final T max;
    private final T avg;

    public MinMaxAvg(T min, T max, T avg) {
        this.min = min;
        this.max = max;
        this.avg = avg;
    }

    static class Builder<T extends Comparable<T>> {
        private T min;
        private T max;
        private T sum;
        private final BiFunction<T, Integer, T> avgFunc;
        private final BiFunction<T, T, T> sumFunc;
        int n;

        public Builder(T sum, BiFunction<T, Integer, T> avgFunc, BiFunction<T, T, T> sumFunc) {
            this.sum = sum;
            this.avgFunc = avgFunc;
            this.sumFunc = sumFunc;
        }

        public void add(T el) {
            n++;
            sum = sumFunc.apply(sum, el);
            if (min == null) {
                min = el;
            }
            if (max == null) {
                max = el;
            }
            min = el.compareTo(min) < 0 ? el : min;
            max = el.compareTo(max) > 0 ? el : max;

        }

        public MinMaxAvg<T> build() {
            return new MinMaxAvg<>(min, max, avgFunc.apply(sum, n));
        }
    }

    @Override
    public String toString() {
        return "min=" + min + "; max=" + max + "; avg=" + avg + ';';
    }
}
