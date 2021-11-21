package com.example.matchmaker.api;

import com.example.matchmaker.model.Player;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Пул игроков
 */
public interface PlayerPool {

    Set<Player> pollSimilar(Player currentPlayer, int groupSize);

    void add(Player player);
}
