package com.example.matchmaker.api;

import com.example.matchmaker.model.Match;
import com.example.matchmaker.model.Player;

import java.util.Optional;

public interface Matchmaker {

    /**
     * Найти матч для заданного кол-ва игроков.
     *
     * @return Группу игроков
     */
    Optional<Match> findMatch();

    /**
     * Добавить игрока
     *
     * @param player Игрок
     */
    void add(Player player);
}
