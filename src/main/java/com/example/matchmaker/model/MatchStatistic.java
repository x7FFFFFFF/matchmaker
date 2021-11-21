package com.example.matchmaker.model;

import com.example.matchmaker.misc.MinMaxAvgDouble;
import com.example.matchmaker.misc.MinMaxAvgLong;

import java.util.Arrays;
import java.util.Set;

public class MatchStatistic {
    private static final int SKILL = 0;
    private static final int LATENCY = 1;
    private final String[] playersNames;
    private final double minSkill;
    private final double maxSkill;
    private final double avgSkill;
    private final double minLatency;
    private final double maxLatency;
    private final double avgLatency;
    private final long minWait;
    private final long maxWait;
    private final double avgWait;


    public MatchStatistic(Set<Player> players, long created) {
                /*players.stream()
                .collect(Collectors.summarizingDouble(Player::getCreated));*/
        playersNames = new String[players.size()];
        MinMaxAvgDouble minMaxAvgDouble = new MinMaxAvgDouble(2, true);
        MinMaxAvgLong minMaxAvgLong = new MinMaxAvgLong();
        int i = 0;
        for (Player player : players) {
            playersNames[i++] = player.getName();
            minMaxAvgDouble.add(SKILL, player.getSkill());
            minMaxAvgDouble.add(LATENCY, player.getLatency());
            minMaxAvgLong.add(created - player.getCreated());
        }
        minSkill = minMaxAvgDouble.getMin(SKILL);
        maxSkill = minMaxAvgDouble.getMax(SKILL);
        avgSkill = minMaxAvgDouble.getAverage(SKILL);

        minLatency = minMaxAvgDouble.getMin(LATENCY);
        maxLatency = minMaxAvgDouble.getMax(LATENCY);
        avgLatency = minMaxAvgDouble.getAverage(LATENCY);

        minWait = minMaxAvgLong.getMin();
        maxWait = minMaxAvgLong.getMax();
        avgWait = minMaxAvgLong.getAverage();

    }

    public double getMinSkill() {
        return minSkill;
    }

    public double getMaxSkill() {
        return maxSkill;
    }

    public double getAvgSkill() {
        return avgSkill;
    }

    public double getMinLatency() {
        return minLatency;
    }

    public double getMaxLatency() {
        return maxLatency;
    }

    public double getAvgLatency() {
        return avgLatency;
    }

    public long getMinWait() {
        return minWait;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public double getAvgWait() {
        return avgWait;
    }

    public String[] getPlayersNames() {
        return playersNames;
    }

    @Override
    public String toString() {
        return "MatchStatistic{" +
                "playersNames=" + Arrays.toString(playersNames) +
                ", minSkill=" + minSkill +
                ", maxSkill=" + maxSkill +
                ", avgSkill=" + avgSkill +
                ", minLatency=" + minLatency +
                ", maxLatency=" + maxLatency +
                ", avgLatency=" + avgLatency +
                ", minWait=" + minWait +
                ", maxWait=" + maxWait +
                ", avgWait=" + avgWait +
                '}';
    }
}
