package com.example.matchmaker.core;


import com.example.matchmaker.api.PlayerPool;
import com.example.matchmaker.config.PlayerPoolConfig;
import com.example.matchmaker.model.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class PlayerPoolImpl implements PlayerPool {
    //вообщем-то, при таком подходе можно было бд использовать...
    private final NavigableMap<Double, Set<Player>> skillIndex = new ConcurrentSkipListMap<>();
    private final NavigableMap<Double, Set<Player>> latencyIndex = new ConcurrentSkipListMap<>();
    private final NavigableMap<Long, Set<Player>> timeIndex = new ConcurrentSkipListMap<>();
    //private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    // private final Lock writeLock = lock.writeLock();
    //private final Lock readLock = lock.readLock();
    private final PlayerPoolConfig conf;

    public PlayerPoolImpl(PlayerPoolConfig conf) {
        this.conf = conf;
    }

    @Override
    public Set<Player> pollSimilar(Player currentPlayer, int groupSize) {
        final NavigableMap<Double, Set<Player>> skillSubMap;
        final NavigableMap<Double, Set<Player>> latencySubMap;
        final NavigableMap<Long, Set<Player>> createdSubMap;
        //ry {
        //   readLock.lock();
        skillSubMap = getSkills(currentPlayer);
        latencySubMap = getLatency(currentPlayer);
        createdSubMap = getCreated(currentPlayer);
        //  } finally {
        //    readLock.unlock();
        // }
        final Set<Player> intersection = intersection(groupSize, skillSubMap, latencySubMap, createdSubMap);
        if (intersection.size() != groupSize) {
            return Collections.emptySet();
        }
        delete(skillSubMap, latencySubMap, createdSubMap, intersection);
        return intersection;
    }

    private void delete(NavigableMap<Double, Set<Player>> skillSubMap, NavigableMap<Double, Set<Player>> latencySubMap, NavigableMap<Long, Set<Player>> createdSubMap, Set<Player> intersection) {
        if (!intersection.isEmpty()) {
            // try {
            //  writeLock.lock();
            remove(skillSubMap, intersection);
            remove(latencySubMap, intersection);
            remove(createdSubMap, intersection);

            //TODO if set.isEmpty remove key

            // } finally {
            //    writeLock.unlock();
            //}
        }
    }

    private <T extends Number> void remove(NavigableMap<T, Set<Player>> map, Set<Player> players) {
        //map.values().forEach(s -> s.removeAll(players));
        final List<T> numbers = new ArrayList<>(map.keySet());
        numbers.forEach(n -> map.compute(n, (key, oldValue) -> {
            if (oldValue != null) {
                oldValue.removeAll(players);
                if (!oldValue.isEmpty()) {
                    return oldValue;
                }
            }
            return null;
        }));



/*        numbers.forEach(k->{
            map.compute(k, (key, oldValue)->{


            });
        });*/

    }

    private NavigableMap<Long, Set<Player>> getCreated(Player currentPlayer) {
        final long currentPlayerCreated = currentPlayer.getCreated();
        final double createdDiff = conf.getCreatedDiff();
        return timeIndex.subMap((long) (currentPlayerCreated * (1 - createdDiff)), true,
                (long) (currentPlayerCreated * (1 + createdDiff)), true);
    }

    private NavigableMap<Double, Set<Player>> getLatency(Player currentPlayer) {
        final double currentPlayerLatency = currentPlayer.getLatency();
        final double latencyDiff = conf.getLatencyDiff();
        return latencyIndex.subMap(currentPlayerLatency * (1 - latencyDiff), true,
                currentPlayerLatency * (1 + latencyDiff), true);
    }

    private NavigableMap<Double, Set<Player>> getSkills(Player currentPlayer) {
        final double currentPlayerSkill = currentPlayer.getSkill();
        final double skillDiff = conf.getSkillDiff();
        return skillIndex.subMap(currentPlayerSkill * (1 - skillDiff), true,
                currentPlayerSkill * (1 + skillDiff), true);
    }

    private Set<Player> intersection(int groupSize, NavigableMap<Double, Set<Player>> skillSubMap, NavigableMap<Double, Set<Player>> latencySubMap, NavigableMap<Long, Set<Player>> createdSubMap) {
        final Set<Player> created = createdSubMap.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
        final Set<Player> skills = skillSubMap.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
        final Set<Player> latencies = latencySubMap.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
        return created.stream().filter(player -> skills.contains(player) && latencies.contains(player))
                .limit(groupSize)
                .collect(Collectors.toSet());
    }

    public void add(Player player) {
        //try {
        //writeLock.lock();
        skillIndex.computeIfAbsent(player.getSkill(), k -> ConcurrentHashMap.newKeySet()).add(player);
        latencyIndex.computeIfAbsent(player.getLatency(), k -> ConcurrentHashMap.newKeySet()).add(player);
        timeIndex.computeIfAbsent(player.getCreated(), k -> ConcurrentHashMap.newKeySet()).add(player);
        // } finally {
        //writeLock.unlock();
        //}
    }

    public void delete(Player player) {
        skillIndex.computeIfPresent(player.getSkill(), (key, oldValue) -> {
            oldValue.remove(player);
            return oldValue;
        });
        latencyIndex.computeIfPresent(player.getLatency(), (key, oldValue) -> {
            oldValue.remove(player);
            return oldValue;
        });
        timeIndex.computeIfPresent(player.getCreated(), (key, oldValue) -> {
            oldValue.remove(player);
            return oldValue;
        });
    }


}
