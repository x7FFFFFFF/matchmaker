package com.example.matchmaker;

import com.example.matchmaker.api.Matchmaker;
import com.example.matchmaker.api.PlayerPool;
import com.example.matchmaker.api.PlayerSeeker;
import com.example.matchmaker.config.PlayerSeekerConfig;
import com.example.matchmaker.core.MatchmakerImpl;
import com.example.matchmaker.core.NaivePlayerSeeker;
import com.example.matchmaker.core.SimpleSyncPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties(PlayerSeekerConfig.class)
public class MatchmakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchmakerApplication.class, args);
    }


    @Bean
    public PlayerPool playerPool() {
        return new SimpleSyncPool();
    }

    @Bean
    public PlayerSeeker playerSeeker() {
        return new NaivePlayerSeeker(playerSeekerConfig());
    }

    @Bean
    public Matchmaker matchmaker(PlayerPool pool, PlayerSeeker seeker, @Value("${matchmaker.groupSize}") int groupSize) {
        return new MatchmakerImpl(pool, seeker, groupSize);
    }

    @Bean

    public PlayerSeekerConfig playerSeekerConfig() {
        return new PlayerSeekerConfig();
    }
}
