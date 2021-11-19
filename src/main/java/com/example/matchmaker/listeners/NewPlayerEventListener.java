package com.example.matchmaker.listeners;

import com.example.matchmaker.api.Matchmaker;
import com.example.matchmaker.events.NewPlayerEvent;
import org.springframework.stereotype.Component;

@Component
public class NewPlayerEventListener {
    private final Matchmaker matchmaker;

    public NewPlayerEventListener(Matchmaker matchmaker) {
        this.matchmaker = matchmaker;
    }

    public void onApplicationEvent(NewPlayerEvent event) {
        matchmaker.findMatch().ifPresent(System.out::println);
    }
}
