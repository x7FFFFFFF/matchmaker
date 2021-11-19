package com.example.matchmaker.core;


import com.example.matchmaker.api.PlayerPool;
import com.example.matchmaker.api.PlayerSeeker;
import com.example.matchmaker.config.PlayerSeekerConfig;
import com.example.matchmaker.model.Player;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

public class NaivePlayerSeeker implements PlayerSeeker {

    private final PlayerSeekerConfig conf;

    public NaivePlayerSeeker(PlayerSeekerConfig conf) {
        this.conf = conf;
    }

    @Override
    public Set<Player> seek(Player currentPlayer, PlayerPool pool, int groupSize) {
        Set<Player> found = new HashSet<>();
        for (Player player : pool) {
            if (isFitsBySkill(currentPlayer, player) && isFitsByLatency(currentPlayer, player) &&
                    isFitsByCreated(currentPlayer, player)) {
                found.add(player);
            }
            if (found.size() == groupSize) {
                break;
            }
        }
        return found;
    }

    private boolean isFitsBySkill(Player currentPlayer, Player player) {
        final double rating = abs(currentPlayer.getSkill() - player.getSkill()) / currentPlayer.getSkill(); //TODO: Zero?
        return Double.compare(rating, conf.getSkillDiff()) <= 0;
    }

    private boolean isFitsByLatency(Player currentPlayer, Player player) {
        final double rating = abs(currentPlayer.getLatency() - player.getLatency()) / currentPlayer.getLatency(); //TODO: Zero?
        return Double.compare(rating, conf.getLatencyDiff()) <= 0;
    }

    private boolean isFitsByCreated(Player currentPlayer, Player player) {
        final long rating = abs(currentPlayer.getCreated() - player.getCreated()) / currentPlayer.getCreated(); //TODO: Zero?
        return Double.compare(rating, conf.getCreatedDiff()) <= 0;
    }
}
