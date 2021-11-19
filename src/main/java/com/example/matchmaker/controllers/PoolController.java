package com.example.matchmaker.controllers;

import com.example.matchmaker.api.Matchmaker;
import com.example.matchmaker.events.NewPlayerEvent;
import com.example.matchmaker.model.Player;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoolController {

    private final Matchmaker matchmaker;
    private final ApplicationEventPublisher publisher;

    public PoolController(Matchmaker userPool, ApplicationEventPublisher publisher) {
        this.matchmaker = userPool;
        this.publisher = publisher;
    }

    @PostMapping("/users")
    public ResponseEntity<String> newUser(Player player) {
        matchmaker.add(player);
        publisher.publishEvent(new NewPlayerEvent(player));
        return ResponseEntity.ok("Ok: userId=" + player.getId());
    }

}
