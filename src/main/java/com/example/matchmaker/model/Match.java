package com.example.matchmaker.model;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Match {
    private static final AtomicLong counter = new AtomicLong(0);

    private final Long id = counter.incrementAndGet();
    private final long created = System.currentTimeMillis();
    private final Set<Player> players;
    private final MatchStatistic statistic;

    public Match(Set<Player> players) {
        this.players = players;
        statistic = new MatchStatistic(players, created);
    }

    public Set<Player> getPlayers() {
        return players;
    }


    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", created=" + created +
                ", statistic=" + statistic +
                '}';
    }
}
