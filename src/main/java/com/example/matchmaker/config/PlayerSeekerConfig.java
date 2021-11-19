package com.example.matchmaker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "player.seeker")
public class PlayerSeekerConfig {
    /**
     * Допустим, есть player1, player2, и skillDiff=0.5
     * Это значит, что player1 считается подходящим для player2,
     * если abs(player1.getSkill() - player2.getSkill()) / player1.getSkill() <=0.5
     */
    private double skillDiff;
    private double latencyDiff;
    private double createdDiff;

    public PlayerSeekerConfig() {
    }

    public PlayerSeekerConfig(double skillDiff, double latencyDiff, double createdDiff) {
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
