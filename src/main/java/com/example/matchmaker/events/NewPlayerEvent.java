package com.example.matchmaker.events;

import com.example.matchmaker.model.Player;
import org.springframework.context.ApplicationEvent;

public class NewPlayerEvent extends ApplicationEvent {
    public NewPlayerEvent(Player player) {
        super(player);
    }

    public Player getPlayer() {
        return (Player) source;
    }
}
