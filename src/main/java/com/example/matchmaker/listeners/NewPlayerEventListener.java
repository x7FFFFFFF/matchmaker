package com.example.matchmaker.listeners;

import com.example.matchmaker.api.PlayerPool;
import com.example.matchmaker.events.NewMatchEvent;
import com.example.matchmaker.events.NewPlayerEvent;
import com.example.matchmaker.model.Match;
import com.example.matchmaker.model.Player;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NewPlayerEventListener {
    private final PlayerPool playerPool;
    private final int groupSize;
    private ApplicationEventPublisher publisher;

    public NewPlayerEventListener(PlayerPool playerPool, @Value("${matchmaker.groupSize}") int groupSize, ApplicationEventPublisher publisher) {
        this.playerPool = playerPool;
        this.groupSize = groupSize;
        this.publisher = publisher;
    }


    @EventListener
    @Async
    public void onApplicationEvent(NewPlayerEvent event) {
        final Player player = event.getPlayer();
        final int size = this.groupSize - 1;
        final Set<Player> players = playerPool.pollSimilar(player, size);
        if (players.size() == size) {
            players.add(player);
            final Match match = new Match(players);
            publisher.publishEvent(new NewMatchEvent(match));
            System.out.println("match:\n" + match);
        } else {
            playerPool.add(player);
        }
    }
}
