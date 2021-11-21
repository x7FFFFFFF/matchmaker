package com.example.matchmaker;

import com.example.matchmaker.api.PlayerPool;
import com.example.matchmaker.config.PlayerPoolConfig;
import com.example.matchmaker.core.PlayerPoolImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties(PlayerPoolConfig.class)
public class MatchmakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchmakerApplication.class, args);
    }


    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("TaskExecutor-");
        executor.initialize();
        return executor;
    }

    @Bean
    PlayerPool playerPool() {
        return new PlayerPoolImpl(playerPoolConfig());
    }

  /*  @Bean
    public PlayerPool playerPool() {
        return new SimpleSyncPool();
    }*/

  /*  @Bean
    public PlayerSeeker playerSeeker() {
        return new NaivePlayerSeeker(playerSeekerConfig());
    }*/

  /*  @Bean
    public Matchmaker matchmaker(PlayerPool pool, PlayerSeeker seeker, @Value("${matchmaker.groupSize}") int groupSize) {
        return new MatchmakerImpl(pool, seeker, groupSize);
    }*/

    @Bean
    public PlayerPoolConfig playerPoolConfig() {
        return new PlayerPoolConfig();
    }
}
