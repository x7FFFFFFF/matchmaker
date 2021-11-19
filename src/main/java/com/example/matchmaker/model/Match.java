package com.example.matchmaker.model;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Match {
    private static final AtomicLong counter = new AtomicLong(0);
    private final Long id = counter.incrementAndGet();
    private final long created = System.currentTimeMillis();
    private final Set<Player> players;

    public Match(Set<Player> players) {
        this.players = players;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    /**
     * При формировании очередной группы матчмейкер должен вывести на std out следующую информацию:
     * • последовательный номер группы
     * • min/max/avg skill in group
     * • min/max/avg latency in group
     * • min/max/avg time spent in queue
     * • список имен игроков
     */
    private String description() {
        final StringBuilder builder = new StringBuilder("#").append(id).append("\n")
                .append("Players: ");
        final MinMaxAvg.Builder<Double> skill = new MinMaxAvg.Builder<>(.0, (s, n) -> s / n, Double::sum);
        final MinMaxAvg.Builder<Double> latency = new MinMaxAvg.Builder<>(.0, (s, n) -> s / n, Double::sum);
        final MinMaxAvg.Builder<Long> time = new MinMaxAvg.Builder<>(0L, (s, n) -> s / n, (sum, el) -> sum + (created - el));
        players.forEach(player -> {
            builder.append(player.getName()).append("; ");
            skill.add(player.getSkill());
            latency.add(player.getLatency());
            time.add(player.getCreated());
        });
        builder.append("\nskill: ").append(skill.build()).append("\n")
                .append("latency: ").append(latency.build()).append("\n")
                .append(" time spent in queue: ").append(time.build()).append("\n");
        return builder.toString();
    }


    @Override
    public String toString() {
        return description();
    }
}
