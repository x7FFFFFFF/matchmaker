package com.example.matchmaker.api;

import com.example.matchmaker.model.Player;

import java.util.List;
import java.util.Set;

public interface PlayerSeeker {

    /**
     * Ищем для currentPlayer подходящих игроков (кол-во = playersCount)
     *
     * @param currentPlayer текущий игрок
     * @param pool          Пул игроков
     * @param playersCount  Количество игроков
     * @return список подходящих игроков
     */
    Set<Player> seek(Player currentPlayer, PlayerPool pool, int playersCount);
}
