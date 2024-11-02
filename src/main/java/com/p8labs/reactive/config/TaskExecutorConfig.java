package com.p8labs.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class TaskExecutorConfig {

    private static final int TASK_QUEUE_CAPACITY = 100;
    private static final int KEEP_ALIVE_SECOND = 60;
    private static final String THREAD_NAME_PRFIX = "custom-";


    @Bean
    public Executor customExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 초기 스레드 수
        executor.setMaxPoolSize(50); // 최대 스레드 수
        executor.setQueueCapacity(100); // 대기열 크기
        executor.setThreadNamePrefix("CustomExecutor-");
        executor.initialize();
        return executor;
    }
}

