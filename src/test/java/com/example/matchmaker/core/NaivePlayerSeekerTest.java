package com.example.matchmaker.core;

import com.example.matchmaker.config.PlayerPoolConfig;
import com.example.matchmaker.model.Player;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NaivePlayerSeekerTest {

    @Test
    void test() {
        final PlayerPoolImpl naivePlayerSeeker = new PlayerPoolImpl(new PlayerPoolConfig(0.4, 0.4, 0.4));
        final Player player1 = new Player(1L, "1", 0.9, 0.9, 9);
        naivePlayerSeeker.add(player1);
        final Player player2 = new Player(2L, "2", 1.1, 1.1, 11);
        naivePlayerSeeker.add(player2);

        naivePlayerSeeker.add(new Player(3L, "3", 10, 1.1, 11));   // skill
        naivePlayerSeeker.add(new Player(4L, "4", 1.1, 10, 11));  // latency
        naivePlayerSeeker.add(new Player(5L, "5", 1.1, 1.1, 20)); // time

        final Set<Player> players = naivePlayerSeeker.pollSimilar(new Player(6L, "3", 1.0, 1.0, 10),
                 2);
        System.out.println("players = " + players);
        assertEquals(2, players.size());
        assertTrue(players.contains(player1));
        assertTrue(players.contains(player2));


    }
}