package com.example.matchmaker.core;

import com.example.matchmaker.model.Match;
import com.example.matchmaker.model.Player;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MatchmakerImplTest {
/*
    @Test
    void findMatch() {
        final MatchmakerImpl matchmaker = getMatchmaker(0.4, 0.4, 0.4, 2);
        matchmaker.add(new Player("Player1", 1.0, 1000));
        assertMatchEmpty(matchmaker);
        matchmaker.add(new Player("Player2", 1.0, 1000));
        {
            assertMatchCount(matchmaker, 2);
            assertMatchEmpty(matchmaker);
        }
        {
            matchmaker.add(new Player("Player1", 1.2, 1300));
            matchmaker.add(new Player("Player2", 1.0, 1000));
            assertMatchCount(matchmaker, 2);
            assertMatchEmpty(matchmaker);
        }

        {
            matchmaker.add(new Player("Player1", 2.2, 1300));
            matchmaker.add(new Player("Player2", 1.0, 1000));
            assertMatchEmpty(matchmaker);
        }

    }

    @Test
    void testZero() {
        final MatchmakerImpl matchmaker = getMatchmaker(0.4, 0.4, 0.4, 2);
        matchmaker.add(new Player("Player1", 0, 0));
        matchmaker.add(new Player("Player2", 0, 0));
        assertMatchCount(matchmaker, 2);
        assertMatchEmpty(matchmaker);
    }

    private MatchmakerImpl getMatchmaker(double skillDiff, double latencyDiff, double createdDiff, int groupSize) {
        *//*return new MatchmakerImpl(new SimpleSyncPool(),
                new NaivePlayerSeeker(new PlayerSeekerConfig(skillDiff, latencyDiff, createdDiff)),
                groupSize);*//*
        return null;
    }


    private void assertMatchEmpty(MatchmakerImpl matchmaker) {
        assertFalse(matchmaker.findMatch().isPresent());
    }

    private void assertMatchCount(MatchmakerImpl matchmaker, int i) {
        final Optional<Match> match = matchmaker.findMatch();
        assertTrue(match.isPresent());
        final Set<Player> players = match.get().getPlayers();
        assertEquals(i, players.size());
    }*/

}