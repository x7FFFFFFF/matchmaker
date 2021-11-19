package com.example.matchmaker.controllers;

import com.example.matchmaker.api.Matchmaker;
import com.example.matchmaker.api.PlayerPool;
import com.example.matchmaker.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoolController {

    private final Matchmaker matchmaker;

    public PoolController(Matchmaker userPool) {
        this.matchmaker = userPool;
    }

    @PostMapping("/users")
    public ResponseEntity<String> newUser(Player user) {
        matchmaker.add(user);
        return ResponseEntity.ok("Ok: userId=" + user.getId());
    }

}
