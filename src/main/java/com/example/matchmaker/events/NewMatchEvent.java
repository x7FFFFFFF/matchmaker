package com.example.matchmaker.events;

import com.example.matchmaker.model.Match;
import org.springframework.context.ApplicationEvent;

public class NewMatchEvent extends ApplicationEvent {

    public NewMatchEvent(Match match) {
        super(match);
    }

    public Match getMatch() {
        return (Match) source;
    }
}
