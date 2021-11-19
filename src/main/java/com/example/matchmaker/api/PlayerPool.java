package com.example.matchmaker.api;

import com.example.matchmaker.model.Player;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Пул игроков
 */
public interface PlayerPool extends Iterable<Player> {
    /**
     * Размер пула
     */
    int size();

    /**
     * Вытащить из пула первого игрока
     */
    Optional<Player> polFirst();

    /**
     * Добавить игрока
     */
    void add(Player Player);

    /**
     * Удалить всех players
     *
     * @param players Игроки, которых нужно удалить
     */
    void removeAll(Collection<Player> players);

    /**
     * Проверить есть ли player в пуле
     * @return true если игрок в пуле
     */
    boolean contains(Player player);


    default Stream<Player> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    void clear();
}
