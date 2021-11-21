package com.example.matchmaker;

import com.example.matchmaker.events.NewMatchEvent;
import com.example.matchmaker.model.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RecordApplicationEvents
class MatchmakerApplicationTests {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ApplicationEvents applicationEvents;

    @Test
    void contextLoads() throws InterruptedException {
        final String url = "http://localhost:" + port + "/users";

        final int nThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(nThreads);
        CountDownLatch latch = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            int finalI = i;
            service.submit(() -> {

                for (int j = 0; j < 10; j++) {
                    this.restTemplate.postForObject(url,
                            new Player("user_"+ finalI +"_" + j, 1, 1000), String.class);
                }
                latch.countDown();
            });
        }
        latch.await();
        /*this.restTemplate.postForObject(url,
                new Player("user1", 1, 1000), String.class);
        this.restTemplate.postForObject(url,
                new Player("user2", 1, 1000), String.class);*/
        final long count = applicationEvents.stream(NewMatchEvent.class).count();
        System.out.println("count = " + count);
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
