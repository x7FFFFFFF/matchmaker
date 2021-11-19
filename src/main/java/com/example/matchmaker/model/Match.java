package com.example.matchmaker.model;

import java.util.Set;

public class Match {
    private final Set<Player> players;

    public Match(Set<Player> players) {
        this.players = players;
    }

    public Set<Player> getPlayers() {
        return players;
    }
}
