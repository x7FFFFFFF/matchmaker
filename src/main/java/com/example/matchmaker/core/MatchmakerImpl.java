package com.example.matchmaker.core;

import com.example.matchmaker.api.Matchmaker;
import com.example.matchmaker.api.PlayerPool;
import com.example.matchmaker.api.PlayerSeeker;
import com.example.matchmaker.model.Match;
import com.example.matchmaker.model.Player;
import org.springframework.beans.factory.annotation.Value;


import java.util.Optional;
import java.util.Set;

public class MatchmakerImpl implements Matchmaker {

    private final PlayerPool pool;
    private final PlayerSeeker playerSeeker;
    private final int groupSize;

    public MatchmakerImpl(PlayerPool pool, PlayerSeeker seeker, int groupSize) {
        this.pool = pool;
        this.playerSeeker = seeker;
        this.groupSize = groupSize;
    }

    @Override
    public Optional<Match> findMatch() {
        Optional<Player> firstPlayer = pool.polFirst();
        if (!firstPlayer.isPresent()) {
            return Optional.empty();
        }
        final Player currentPlayer = firstPlayer.get();
        Set<Player> selectedPlayers = playerSeeker.seek(currentPlayer, pool, groupSize - 1);
        if (selectedPlayers.size() != groupSize - 1) {
            pool.add(currentPlayer);
            return Optional.empty();
        }
        pool.removeAll(selectedPlayers);
        selectedPlayers.add(currentPlayer);
        return Optional.of(new Match(selectedPlayers));
    }

    @Override
    public void add(Player player) {
        pool.add(player);
    }
}
