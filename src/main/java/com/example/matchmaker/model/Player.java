package com.example.matchmaker.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Player {
    private static final AtomicLong counter = new AtomicLong(0);
    private final Long id;
    private final int hash;
    private final String name;
    private final double skill; //Zero?
    private final double latency; //ms  //Zero?
    private final long created;

    public Player(String name, double skill, double latency) {
        this(counter.incrementAndGet(), name, skill, latency, System.currentTimeMillis());
    }

    public Player(Long id, String name, double skill, double latency, long created) {
        this.id = id;
        this.hash = Objects.hash(id);
        this.name = name;
        this.skill = skill;
        this.latency = latency;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSkill() {
        return skill;
    }

    public double getLatency() {
        return latency;
    }

    public long getCreated() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
