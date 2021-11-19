package com.example.matchmaker.core;

import com.example.matchmaker.api.PlayerPool;
import com.example.matchmaker.model.Player;

import java.util.*;
import java.util.stream.Stream;

public class SimpleSyncPool implements PlayerPool {

    private final Deque<Player> delegate = new ArrayDeque<>();

    @Override
    public synchronized int size() {
        return delegate.size();
    }

    @Override
    public synchronized Optional<Player> polFirst() {
        return Optional.ofNullable(delegate.pollFirst());
    }

    @Override
    public synchronized void add(Player Player) {
        delegate.add(Player);
    }

    @Override
    public synchronized void removeAll(Collection<Player> players) {
        delegate.removeAll(players);
    }

    @Override
    public synchronized boolean contains(Player player) {
        return delegate.contains(player);
    }

    @Override
    public synchronized Iterator<Player> iterator() {
        return delegate.iterator();
    }

    @Override
    public synchronized Stream<Player> stream() {
        return delegate.stream();
    }

    @Override
    public synchronized void clear() {
        delegate.clear();
    }

}
