package com.example.matchmaker;

import com.example.matchmaker.api.Matchmaker;
import com.example.matchmaker.api.PlayerPool;
import com.example.matchmaker.api.PlayerSeeker;
import com.example.matchmaker.config.PlayerSeekerConfig;
import com.example.matchmaker.core.MatchmakerImpl;
import com.example.matchmaker.core.NaivePlayerSeeker;
import com.example.matchmaker.core.SimpleSyncPool;
import com.example.matchmaker.model.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MatchmakerApplicationTests {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Disabled
    void contextLoads() {
        final String answer = this.restTemplate.postForObject("http://localhost:" + port + "/users",
                new Player("user1", 1, 1000), String.class);
        System.out.println("answer = " + answer);
    }

    @Test
    void test() {
        final SimpleSyncPool players = new SimpleSyncPool();
    }


    /*    @Configuration
    static class Conf {
        @Bean
        public PlayerPool playerPool() {
            return new SimpleSyncPool();
        }

        @Bean
        public PlayerSeeker playerSeeker() {
            return new NaivePlayerSeeker(playerSeekerConfig());
        }

        @Bean
        public Matchmaker matchmaker(PlayerPool pool, PlayerSeeker seeker) {
            return new MatchmakerImpl(pool, seeker, 2);
        }

        @Bean

        public PlayerSeekerConfig playerSeekerConfig() {
            return new PlayerSeekerConfig(0.4, 0.4, 0.4);
        }
    }*/

}
