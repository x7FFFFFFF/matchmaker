package com.example.matchmaker.model;

import lombok.Value;

import java.util.List;
import java.util.function.BiFunction;

@Value
public class MinMaxAvg<T extends Comparable<T>> {
    T min;
    T max;
    T avg;

    static class Builder<T extends Comparable<T>> {
        private T min;
        private T max;
        private T sum;
        private final BiFunction<T, Integer, T> avgFunc;
        private BiFunction<T, T, T> sumFunc;
        int n;

        public Builder(T sum, BiFunction<T, Integer, T> avgFunc, BiFunction<T, T, T> sumFunc) {
            this.sum = sum;
            this.avgFunc = avgFunc;
            this.sumFunc = sumFunc;
        }

        public void add(T el) {
            n++;
            sum = sumFunc.apply(sum, el);
            if (min != null) {
                min = el.compareTo(min) < 0 ? el : min;
            } else {
                min = el;
            }
            if (max != null) {
                max = el.compareTo(max) > 0 ? el : max;
            }
            {
                max = el;
            }
        }

        public MinMaxAvg<T> build() {
            return new MinMaxAvg<>(min, max, avgFunc.apply(sum, n));
        }
    }
}
