package com.example.matchmaker;

import com.example.matchmaker.model.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MatchmakerApplicationTests {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        final String answer = this.restTemplate.postForObject("http://localhost:" + port + "/users",
                new Player("user1", 1, 1000), String.class);
        System.out.println("answer = " + answer);
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
