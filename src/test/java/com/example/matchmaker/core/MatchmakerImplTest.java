package com.example.matchmaker.core;

import com.example.matchmaker.config.PlayerSeekerConfig;
import com.example.matchmaker.model.Match;
import com.example.matchmaker.model.Player;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MatchmakerImplTest {

    @Test
    void findMatch() {
        final MatchmakerImpl matchmaker = getMatchmaker(0.4, 0.4, 0.4, 2);
        matchmaker.add(new Player("Player1", 1.0, 1000));
        assertFalse(matchmaker.findMatch().isPresent());
        matchmaker.add(new Player("Player2", 1.0, 1000));
        {
            final Optional<Match> match = matchmaker.findMatch();
            assertTrue(match.isPresent());
            final Set<Player> players = match.get().getPlayers();
            assertEquals(2, players.size());
            assertFalse(matchmaker.findMatch().isPresent());
        }
        {
            matchmaker.add(new Player("Player1", 1.2, 1300));
            matchmaker.add(new Player("Player2", 1.0, 1000));
            final Optional<Match> match = matchmaker.findMatch();
            assertTrue(match.isPresent());
            final Set<Player> players = match.get().getPlayers();
            assertEquals(2, players.size());
            assertFalse(matchmaker.findMatch().isPresent());
        }

        {
            matchmaker.add(new Player("Player1", 2.2, 1300));
            matchmaker.add(new Player("Player2", 1.0, 1000));
            final Optional<Match> match = matchmaker.findMatch();
            assertFalse(match.isPresent());
        }

    }

    private MatchmakerImpl getMatchmaker(double skillDiff, double latencyDiff, double createdDiff, int groupSize) {
        return new MatchmakerImpl(new SimpleSyncPool(),
                new NaivePlayerSeeker(new PlayerSeekerConfig(skillDiff, latencyDiff, createdDiff)),
                groupSize);
    }
}