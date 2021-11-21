package com.example.matchmaker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "player.pool")
public class PlayerPoolConfig {

    private double skillDiff;
    private double latencyDiff;
    private double createdDiff;

    public PlayerPoolConfig() {
    }

    public PlayerPoolConfig(double skillDiff, double latencyDiff, double createdDiff) {
        this.skillDiff = skillDiff;
        this.latencyDiff = latencyDiff;
        this.createdDiff = createdDiff;
    }

    public double getSkillDiff() {
        return skillDiff;
    }

    public void setSkillDiff(double skillDiff) {
        this.skillDiff = skillDiff;
    }

    public double getLatencyDiff() {
        return latencyDiff;
    }

    public void setLatencyDiff(double latencyDiff) {
        this.latencyDiff = latencyDiff;
    }

    public double getCreatedDiff() {
        return createdDiff;
    }

    public void setCreatedDiff(double createdDiff) {
        this.createdDiff = createdDiff;
    }
}
