package com.example.matchmaker.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MatchStatisticTest {
    @Test
    void test() {
        Set<Player> players = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            players.add(new Player("" + i, i, i));
        }
        final MatchStatistic matchStatistic = new MatchStatistic(players, System.currentTimeMillis());
        assertEquals(.0, matchStatistic.getMinSkill(), 0.0000001);
        assertEquals(9.0, matchStatistic.getMaxSkill(), 0.0000001);
        assertEquals(4.5, matchStatistic.getAvgSkill(), 0.0000001);
    }
}